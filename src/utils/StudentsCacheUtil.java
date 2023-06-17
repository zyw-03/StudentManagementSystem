package utils;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsCacheUtil {
    private static List<Student> studentList = new ArrayList<>();

    public static void addStudent(Student student){
        studentList.add(student);
    }

    public static void deleteStudent(Student student){
        studentList.remove(student);
    }

    public static List<Student> getStudentList(){
        return StudentsCacheUtil.studentList;
    }

}
