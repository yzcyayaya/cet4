package com.cet4.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 对应excel
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class PersonalInfoBo {
    /**
     * 姓名
     */
    private  String name;
    /**
     * 笔试学校
     */
    private  String writtenSchool;
    /**
     * 笔试学校校区
     */
    private  String schoolCampus;
    /**
     * 是否借考;1是0否
     */
    private  String isLendExam;
    /**
     * 学籍所在校
     */
    private  String schoolRegister;
    /**
     * 性别
     */
    private  String gender;
    /**
     * 证件类型
     */
    private String  papersType;
    /**
     * 学历
     */
    private  String educationBack;
    /**
     * 学制
     */
    private  String schoolingLength;
    /**
     * 入学年份
     */
    private  String enrollmentYear;
    /**
     * 年级
     */
    private  String grade;
    /**
     * 院系
     */
    private  String department;
    /**
     * 专业
     */
    private  String profession;
    /**
     * 班级
     */
    private  String className;
    /**
     * 学号
     */
    private  String studentNumber;
    /**
     * 备注
     */
    private  String remark;
    /**
     * 四级成绩单/信息
     */
    private  String cet4GradeInfo;
    /**
     * 电话号码  绑定user表用的
     */
    private String phone;
}
