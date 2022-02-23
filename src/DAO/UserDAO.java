package DAO;


import Bean.PersonalPage;
import Bean.User;

import java.util.List;

public interface UserDAO {

    //注册时判断用户名是否重复
    User queryUserByUsername(String username);

    //保存用户数据
    int saveUser(User user);

    //登录时判断邮箱和密码
    //返回null则用户名和密码错误
    User queryUserByEmailAndPassword(String email, String password);
    User queryUserByEmail(String email);

    List<PersonalPage> showPersonalPage(String username);

}
