package utils;

public class StringUtil {
    public static Boolean isEmpty(String text){
        return text == null || text.trim().equals("");
    }

    public static Boolean judgeIfAllAreNotEmpty(String text1, String text2, String text3, String text4){
        if(!isEmpty(text1) && !isEmpty(text2) && !isEmpty(text3) && !isEmpty(text4)){
            return true;
        }

        DialogUtil.showDialog("WARNING", "存在空项！！！");
        return false;
    }
}
