package control;

import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DialogUtil;
import utils.StringUtil;
import utils.UserDBUtil;


public class SignupControl {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField account;

    @FXML
    private TextField phone;
    @FXML
    private TextField postbox;
    @FXML
    private PasswordField password;

    public void signup() {

        String ac = account.getText();
        String pw = password.getText();
        String ph = phone.getText();
        String pb = postbox.getText();

        if(!StringUtil.judgeIfAllAreNotEmpty(ac, pw, ph, pb)) return;
        if(UserDBUtil.judgeIfExits(ac)){
            DialogUtil.showDialog("WARNING", "该账号已被注册！！！");
            return;
        }

        User user = new User(ac, pw, ph, pb);
        UserDBUtil.addUser(user);

        DialogUtil.showDialog("INFORMATION", "注册成功!!!");
        ((Stage) root.getScene().getWindow()).close();
    }

    public void clear() {
        account.setText("");
        phone.setText("");
        postbox.setText("");
        password.setText("");
    }
}
