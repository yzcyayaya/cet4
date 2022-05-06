package com.cet4.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @TableField(value = "NAME")
    private  String name;
    /**
     * 笔试学校
     */
    @TableField(value = "WRITTEN_SCHOOL")
    private  String writtenSchool;
    /**
     * 笔试学校校区
     */
    @TableField(value = "SCHOOL_CAMPUS")
    private  String schoolCampus;
    /**
     * 是否借考;1是0否
     */
    @TableField(value = "IS_LEND_EXAM")
    private  String isLendExam;
    /**
     * 学籍所在校
     */
    @TableField(value = "SCHOOL_REGISTER")
    private  String schoolRegister;
    /**
     * 性别
     */
    @TableField(value = "GENDER")
    private  String gender;
    /**
     * 证件类型
     */
    @TableField(value = "PAPERS_TYPE")
    private String  papersType;
    /**
     * 学历
     */
    @TableField(value = "EDUCATION_BACK")
    private  String educationBack;
    /**
     * 学制
     */
    @TableField(value = "SCHOOLING_LENGTH")
    private  String schoolingLength;
    /**
     * 入学年份
     */
    @TableField(value = "ENROLLMENT_YEAR")
    private  String enrollmentYear;
    /**
     * 年级
     */
    @TableField(value = "GRADE")
    private  String grade;
    /**
     * 院系
     */
    @TableField(value = "DEPARTMENT")
    private  String department;
    /**
     * 专业
     */
    @TableField(value = "PROFESSION")
    private  String profession;
    /**
     * 班级
     */
    @TableField(value = "CLASS_NAME")
    private  String className;
    /**
     * 学号
     */
    @TableField(value = "STUDENT_NUMBER")
    private  String studentNumber;
    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private  String remark;
    /**
     * 四级成绩单/信息
     */
    @TableField(value = "CET4_GRADE_INFO")
    private  String cet4GradeInfo;
}
