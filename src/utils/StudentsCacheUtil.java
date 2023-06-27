package utils;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsCacheUtil {    //用于暂存学生成绩信息等的工具
    private static List<Student> studentList = new ArrayList<>();   //用于暂时存放学生的列表

    public static void addStudent(Student student){
        studentList.add(student);
    }   //往暂存区添加学生

    public static void deleteStudent(Student student){
        studentList.remove(student);
    }   //从暂存区中删除学生

    public static List<Student> getStudentList(){
        return StudentsCacheUtil.studentList;
    }   //返回学生列表

}
