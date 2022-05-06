package com.cet4.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "personal_info")
public class PersonalInfoPo {
    /**
     * 用户号
     */
    @TableField(value = "USER_ID")
    private  String userId;
    /**
     * 乐观锁
     */
    @TableField(value = "REVISION")
    private  String revision;
    /**
     * 创建人
     */
    @TableField(value = "CREATED_BY")
    private  String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "CREATED_TIME")
    private  Date createdTime;
    /**
     * 更新人
     */
    @TableField(value = "UPDATED_BY")
    private  String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "UPDATED_TIME")
    private Date updatedTime;
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

    public PersonalInfoPo(PersonalInfoBo personalInfoBo){
        this.name = personalInfoBo.getName();
        this.writtenSchool = personalInfoBo.getWrittenSchool();
        this.schoolCampus = personalInfoBo.getSchoolCampus();
        this.isLendExam = personalInfoBo.getIsLendExam();
        this.schoolRegister = personalInfoBo.getSchoolRegister();
        this.gender = personalInfoBo.getGender();
        this.papersType = personalInfoBo.getPapersType();
        this.educationBack = personalInfoBo.getEducationBack();
        this.schoolingLength = personalInfoBo.getSchoolingLength();
        this.enrollmentYear = personalInfoBo.getEnrollmentYear();
        this.grade = personalInfoBo.getGrade();
        this.department = personalInfoBo.getDepartment();
        this.profession = personalInfoBo.getProfession();
        this.className = personalInfoBo.getClassName();
        this.studentNumber = personalInfoBo.getStudentNumber();
        this.remark = personalInfoBo.getRemark();
        this.cet4GradeInfo = personalInfoBo.getCet4GradeInfo();
    }
}
