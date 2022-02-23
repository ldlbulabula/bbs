package utils;

import Service.UserService;
import Service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CheckUtils {
    UserService service = new UserServiceImpl();
    public boolean testEmail(String email){
        String regex="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        //是否符合格式
        boolean matches = email.matches(regex);
        return matches;
    }

    public Map<String, Object> CheckEmailMap(String email){
        boolean testEmail = testEmail(email);
        boolean existEmail =service.existsEmail(email);
        Map<String, Object> map = new HashMap<>();
        if(email==null || "".equals(email)){
            map.put("isNull",true);
            map.put("isOK",false);
            map.put("emailMsg","邮箱不能为空");
            map.put("existsEmail",false);
        }else {
            map.put("isNull",false);
            if(testEmail){
                if (existEmail) {
                    map.put("isOK",false);
                    map.put("existsEmail",true);
                    map.put("emailMsg","邮箱已存在");
                }else {
                    map.put("isOK",true);
                    map.put("existsEmail",false);
                    map.put("emailMsg","邮箱可用");
                }
            }else {
                map.put("isOK",false);
                map.put("existsEmail",false);
                map.put("emailMsg","邮箱格式不符合规范");
            }
        }

        return map;
    }
    public Map<String, Object> CheckPasswordMap(String password){
        String regex1 = ".*[a-zA-Z].*";
        String regex2 = ".*[0-9].*";
        Map<String, Object> map = new HashMap<>();
        if(password==null || "".equals(password)){
            map.put("isNull",true);
            map.put("passwordMsg","密码不能为空");
            map.put("isOK",false);
        }else {
            if (password.length()>=8&&password.length()<=10) {
                if (password.matches(regex1) && password.matches(regex2)) {
                    map.put("isNull",false);
                    map.put("passwordMsg","密码符合规范");
                    map.put("isOK",true);
                }else {
                    map.put("isNull",false);
                    map.put("passwordMsg", "密码必须包括英文字母与数字");
                    map.put("isOK",false);
                }
            }else {
                map.put("isNull",false);
                map.put("passwordMsg", "密码位数不少于8位,不大于10位");
                map.put("isOK",false);
            }
        }
        return map;
    }

    public Map<String, Object> CheckUserNameMap(String username){
        Map<String, Object> map = new HashMap<>();
        boolean existName = service.existsUsername(username);
        if(username==null || "".equals(username)){
            map.put("isNull",true);
            map.put("existName", false);
            map.put("nameMsg", "昵称不能为空");
        }else {
            map.put("existName",existName);
            map.put("isNull",false);
            if(existName){
                map.put("nameMsg", "昵称已存在");
            }else {
                map.put("nameMsg", "昵称可用");
            }
        }


        return map;
    }
}
