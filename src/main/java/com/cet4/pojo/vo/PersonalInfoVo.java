package com.cet4.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cet4.pojo.po.PersonalInfoPo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInfoVo {
    /**
     * 用户号
     */

    private String userId;
    /**
     * 乐观锁
     */

    private String revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 更新人
     */

    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    private Date updatedTime;
    /**
     * 姓名
     */

    private String name;
    /**
     * 笔试学校
     */

    private String writtenSchool;
    /**
     * 笔试学校校区
     */

    private String schoolCampus;
    /**
     * 是否借考;1是0否
     */

    private String isLendExam;
    /**
     * 学籍所在校
     */

    private String schoolRegister;
    /**
     * 性别
     */

    private String gender;
    /**
     * 证件类型
     */

    private String papersType;
    /**
     * 学历
     */

    private String educationBack;
    /**
     * 学制
     */

    private String schoolingLength;
    /**
     * 入学年份
     */

    private String enrollmentYear;
    /**
     * 年级
     */

    private String grade;
    /**
     * 院系
     */

    private String department;
    /**
     * 专业
     */

    private String profession;
    /**
     * 班级
     */

    private String className;
    /**
     * 学号
     */

    private String studentNumber;
    /**
     * 备注
     */

    private String remark;
    /**
     * 四级成绩单/信息
     */

    private String cet4GradeInfo;

    public PersonalInfoVo(PersonalInfoPo personalInfoPo) {
        userId = personalInfoPo.getUserId();
        createdBy = personalInfoPo.getCreatedBy();
        createdTime = personalInfoPo.getCreatedTime();
        updatedBy = personalInfoPo.getUpdatedBy();
        updatedTime = personalInfoPo.getUpdatedTime();
        name = personalInfoPo.getName();
        writtenSchool = personalInfoPo.getWrittenSchool();
        schoolCampus = personalInfoPo.getSchoolCampus();
        isLendExam = personalInfoPo.getIsLendExam();
        schoolRegister = personalInfoPo.getSchoolRegister();
        gender = personalInfoPo.getGender();
        papersType = personalInfoPo.getPapersType();
        educationBack = personalInfoPo.getEducationBack();
        schoolingLength = personalInfoPo.getSchoolingLength();
        enrollmentYear = personalInfoPo.getEnrollmentYear();
        grade = personalInfoPo.getGrade();
        department = personalInfoPo.getDepartment();
        profession = personalInfoPo.getProfession();
        className = personalInfoPo.getClassName();
        studentNumber = personalInfoPo.getStudentNumber();
        remark = personalInfoPo.getRemark();
        cet4GradeInfo = personalInfoPo.getCet4GradeInfo();
    }
}
