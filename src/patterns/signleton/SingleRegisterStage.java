package patterns.signleton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SingleRegisterStage {
    private static Stage stage;

    public static Stage getStage(){
        if(stage == null){
            FXMLLoader loader = new FXMLLoader(SingleRegisterStage.class.getResource("/view/Signup.fxml"));
            System.out.println(SingleRegisterStage.class.getResource("/view/Signup.fxml"));
            Parent parent = null;

            try{
                parent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.setTitle("注册页面");
            stage.setScene(new Scene(parent, 350, 500));
            stage.setResizable(false);
        }
        return stage;
    }

}
