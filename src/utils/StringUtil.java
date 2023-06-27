package utils;

public class StringUtil {    //处理字符串格式的工具
    public static Boolean isEmpty(String text){
        return text == null || text.trim().equals("");
    }   //判断text是否为空

    public static Boolean judgeIfAllAreNotEmpty(String text1, String text2, String text3, String text4){ //判断text1-4是否全部不为空
        if(!isEmpty(text1) && !isEmpty(text2) && !isEmpty(text3) && !isEmpty(text4)){
            return true;
        }

        DialogUtil.showDialog("WARNING", "存在空项！！！");
        return false;
    }
}
