package control;

import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.signleton.SingleRegisterStage;
import utils.StringUtil;
import utils.*;

import java.io.IOException;


public class LoginControl {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField accountNumber;
    @FXML
    public PasswordField password;


    @FXML
    public void login(){    //实现登录页面的逻辑 即账号或密码错误等

        String account = accountNumber.getText();
        String ps = password.getText();

        if (StringUtil.isEmpty(account))    DialogUtil.showDialog("WARNING", "请输入账号!!!");
        else if (StringUtil.isEmpty(ps))    DialogUtil.showDialog("WARNING", "请输入密码!!!");
        else if (UserDBUtil.judgeRight(account, ps)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
            try {
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("学生成绩管理系统");
                stage.setScene(new Scene(parent, 630, 490));
                stage.setResizable(false);
                stage.show();

                ((Stage)root.getScene().getWindow()).close();

            } catch (IOException e) {
                e.printStackTrace();
                DialogUtil.showDialog("ERROR", "发生未知错误！！！");
            }


        }
        else    DialogUtil.showDialog("WARNING", "账号或密码错误!!!");


    }

    public void signup() {
        SingleRegisterStage.getStage().show();
    }    //点击"注册"，弹出注册页面
}
