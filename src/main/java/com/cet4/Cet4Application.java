package com.cet4;

import cn.hutool.extra.spring.SpringUtil;
import com.cet4.migration.AutoMigration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.sql.SQLSyntaxErrorException;

@Log4j2
@SpringBootApplication
public class Cet4Application {

    public static void main(String[] args) {
        SpringApplication.run(Cet4Application.class, args);
        autoMigration();
    }
    //自动迁移
    public static void autoMigration(){
        //自动迁移
        ApplicationContext context = SpringUtil.getApplicationContext();
        AutoMigration autoMigration = context.getBean(AutoMigration.class); //自动迁移类
//        autoMigration.dropTableName("user");  如果存在则删除
        try{
            //建表
            autoMigration.createUserTable(); //创建user表结构
            autoMigration.createPersonalInfoTable(); //创建personal_info表
            autoMigration.createRoleTable();
            autoMigration.createUserRoleTable();

            //插入数据
            autoMigration.insertAdministration();
            autoMigration.insertRole();
            autoMigration.insertAdminRole();
        }catch (Exception e){
            //堆栈信息
//            e.printStackTrace();
            log.warn("数据已经存在，不需要再创建了！");
        }
    }


}
