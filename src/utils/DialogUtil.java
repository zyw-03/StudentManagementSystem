package utils;

import javafx.scene.control.Alert;

public class DialogUtil{    //显示各种类型dialog的工具
    public static void showDialog(String type, String info){   //显示type类型的dialog，并显示info信息
        Alert alert = null;
        if (type.equals("INFORMATION")){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("INFORMATION");
        }
        else if (type.equals("ERROR")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
        }
        else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("WARNING");
        }
        alert.setContentText(info);
        alert.show();
    }

}
