package patterns.observer;

import entity.Student;
import utils.StudentsCacheUtil;

public class MyObserver implements Observer{
   private Student student;

   public MyObserver(Student student){
       this.student = student;

   }

    @Override
    public void updateTableView() {
        StudentsCacheUtil.addStudent(student);
    }
}
