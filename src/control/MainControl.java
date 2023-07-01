package control;

import entity.Student;
import entity.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import patterns.observer.MyObserver;
import patterns.observer.MySubject;
import patterns.observer.Observer;
import patterns.observer.Subject;
import utils.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import patterns.*;


public class MainControl{
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> courseColumn;

    @FXML
    private TableColumn<Student, String> scoreColumn;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField course;
    @FXML
    private TextField score;
    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private TextField postbox;
    @FXML
    private Button confirm;

    private ObservableList<Student> data;



    @FXML
    public void initialize(){   //主页面的吃初始化
        setData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        data = FXCollections.observableList(StudentsCacheUtil.getStudentList());
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void setData(){    //实现"基本页面"的一些参数的写入
        User user = UserDBUtil.getNowUser();
        account.setText(user.getAccount());
        password.setText(user.getPassword());
        phone.setText(user.getPhone());
        postbox.setText(user.getPostbox());
    }

    @FXML
    public void insert() {     //实现点击"录入"后的逻辑
        Subject subject = new MySubject();
        Observer observer;
        String studentID = id.getText();
        String studentName = name.getText();
        String schoolCourse = course.getText();
        String studentScore = score.getText();

        if (StringUtil.isEmpty(studentID)){
            DialogUtil.showDialog("WARNING", "请输入学生学号！！！");
            return;
        }
        else if (StringUtil.isEmpty(studentName)){
            DialogUtil.showDialog("WARNING", "请输入学生姓名！！！");
            return;
        }
        else if (StringUtil.isEmpty(schoolCourse)){
            DialogUtil.showDialog("WARNING", "请输入科目！！！");
            return;
        }
        else if (StringUtil.isEmpty(studentScore)){
            DialogUtil.showDialog("WARNING", "请输入学生成绩！！！");
            return;
        }

        Student student = new Student(studentID, studentName, schoolCourse, studentScore);
        clear();
        observer = new MyObserver(student);
        subject.attach(observer);
        subject.operate();
        subject.detach(observer);
        refresh();
    }

    public void clear() {    //实现清空要录入的信息
        id.setText("");
        name.setText("");
        course.setText("");
        score.setText("");
    }



    public void delete() {    //实现在tableView中选择一行进行删除的功能
        Student selectedStudent =  tableView.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            StudentsCacheUtil.deleteStudent(selectedStudent);
            refresh();
        }
        else    DialogUtil.showDialog("WARNING", "请选择要删除的学生！！！");
    }

    public void save() {   //将当前存放的数据存入到数据库的功能的实现
        if (StudentsCacheUtil.getStudentList().isEmpty()){
            DialogUtil.showDialog("WARNING", "当前表中的数据为空！！！");
            return;
        }
        StudentDBUtil.addStudents(StudentsCacheUtil.getStudentList());
        StudentsCacheUtil.getStudentList().clear();
        DialogUtil.showDialog("INFORMATION", "提交成功！！！");
        refresh();
    }

    public void update() {    //在tableView中选择一行进行数据修改的功能的实现
        Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            UpdateDataControl.setStudent(selectedStudent);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateData.fxml"));
            try{
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("修改页面");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        else DialogUtil.showDialog("WARNING", "请选择要修改的学生！！！");


    }

    public void refresh() {
        tableView.refresh();
    }   //tableView刷新

    public void modify() {     //实现在"基本信息"中点击"修改"后的一些逻辑
        if(confirm.isDisabled()){
            confirm.setDisable(false);
            password.setDisable(false);
            postbox.setDisable(false);
            phone.setDisable(false);
        }
        else{
            confirm.setDisable(true);
            password.setDisable(true);
            postbox.setDisable(true);
            phone.setDisable(true);
        }
    }

    public void check() {    //实现信息修改的逻辑
        String newPassword = password.getText();
        String newPhone = phone.getText();
        String newPostbox = postbox.getText();

        if(!StringUtil.judgeIfAllAreNotEmpty(UserDBUtil.getNowUser().getAccount(), newPassword, newPhone, newPostbox))   return;

        User user = UserDBUtil.getNowUser();
        user.setPassword(newPassword);
        user.setPhone(newPhone);
        user.setPostbox(newPostbox);
        UserDBUtil.updateUser();
        initialize();
        DialogUtil.showDialog("INFORMATION", "修改成功！！！");

    }


}
