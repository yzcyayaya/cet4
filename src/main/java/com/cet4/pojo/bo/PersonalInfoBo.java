package com.cet4.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty("姓名")
    private  String name;
    /**
     * 笔试学校
     */
    @ExcelProperty("笔试学校")
    private  String writtenSchool;
    /**
     * 笔试学校校区
     */
    @ExcelProperty("笔试学校校区")
    private  String schoolCampus;
    /**
     * 是否借考;1是0否
     */
    @ExcelProperty("是否借考;1是0否")
    private  String isLendExam;
    /**
     * 学籍所在校
     */
    @ExcelProperty("学籍所在校")
    private  String schoolRegister;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private  String gender;
    /**
     * 证件类型
     */
    @ExcelProperty("证件类型")
    private String  papersType;
    /**
     * 学历
     */
    @ExcelProperty("学历")
    private  String educationBack;
    /**
     * 学制
     */
    @ExcelProperty("学制")
    private  String schoolingLength;
    /**
     * 入学年份
     */
    @ExcelProperty("入学年份")
    private  String enrollmentYear;
    /**
     * 年级
     */
    @ExcelProperty("年级")
    private  String grade;
    /**
     * 院系
     */
    @ExcelProperty("院系")
    private  String department;
    /**
     * 专业
     */
    @ExcelProperty("专业")
    private  String profession;
    /**
     * 班级
     */
    @ExcelProperty("班级")
    private  String className;
    /**
     * 学号
     */
    @ExcelProperty("学号")
    private  String studentNumber;
    /**
     * 备注
     */
    @ExcelProperty("备注")
    private  String remark;
    /**
     * 四级成绩单/信息
     */
    @ExcelProperty("四级成绩单/信息")
    private  String cet4GradeInfo;
    /**
     * 电话号码  绑定user表用的
     */
    @ExcelProperty("电话号码")
    private String phone;
    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;
}
