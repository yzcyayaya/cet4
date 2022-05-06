package com.cet4.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.cet4.dao.PersonalInfoDao;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.cet4.pojo.po.PersonalInfoPo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
@Slf4j
@NoArgsConstructor
public class ExcelPersonalInfoListener implements ReadListener<PersonalInfoBo> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<PersonalInfoBo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    @Autowired
    private PersonalInfoDao personalInfoDao;

    /**
     * 如果使用了spring,每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param personalInfoDao
     */
    public ExcelPersonalInfoListener(PersonalInfoDao personalInfoDao) {
        this.personalInfoDao = personalInfoDao;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param personalInfoBo    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(PersonalInfoBo personalInfoBo, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(personalInfoBo));
        cachedDataList.add(personalInfoBo);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        List<PersonalInfoPo> list = new ArrayList<>();
        for (PersonalInfoBo personalInfoBo : cachedDataList) {
            list.add(new PersonalInfoPo(personalInfoBo));
        }
        personalInfoDao.save(list);
        log.info("存储数据库成功！");
    }
}
