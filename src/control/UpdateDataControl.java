package control;

import entity.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DialogUtil;
import utils.StringUtil;

public class UpdateDataControl {

    public static Student student;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField id;
    @FXML
    private TextField name ;
    @FXML
    private TextField course;
    @FXML
    private TextField score;

    public static void setStudent(Student selectedStudent) {
        UpdateDataControl.student = selectedStudent;
    }   //设置要修改的学生

    public void initialize(){  //修改页面的吃初始化
        id.setText(student.getId());
        name.setText(student.getName());
        course.setText(student.getCourse());
        score.setText(student.getScore());
    }

    public void update() {   //实现修改功能的逻辑
        String newID = id.getText();
        String newName = name.getText();
        String newCourse = course.getText();
        String newScore = score.getText();

       if (!StringUtil.judgeIfAllAreNotEmpty(newID, newName, newCourse, newScore))   return;
        student.setId(newID);
        student.setName(newName);
        student.setCourse(newCourse);
        student.setScore(newScore);

        DialogUtil.showDialog("INFORMATION", "修改成功");
        ((Stage)root.getScene().getWindow()).close();

    }

    public void restore() {
        initialize();
    }
}
