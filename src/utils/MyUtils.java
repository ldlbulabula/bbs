package utils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
   public static String getDate(){
       Date date = new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return   simpleDateFormat.format(date);
   }
   public static int parseInt(String strInt,int defaultValue) {
           try {

               int i = Integer.parseInt(strInt);

               if(i<0){
                   i=defaultValue;
               }

               return i;
           } catch (Exception e) {
               return defaultValue;
           }
       }
    public static boolean checkRepeatSubmit(HttpServletRequest req) {
        Object sessionTokenObj = req.getSession().getAttribute("token");
        if(sessionTokenObj == null) {
            // 表单重复提交
            System.out.println("Session token is NULL!");
            return true;
        }

        String paramToken = req.getParameter("token");
        if(paramToken == null) {
            // 非法请求
            System.out.println("Parameter token is NULL!");
            return true;
        }

        if(!paramToken.equals(sessionTokenObj.toString())) {
            // 非法请求
            System.out.println("Token not same");
            return true;
        }
        return false;
    }
}
