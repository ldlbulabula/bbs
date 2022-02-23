package DAO;


import Bean.PersonalPage;
import Bean.User;

import java.util.List;

public class UserDAOimpl extends baseDAO implements UserDAO {

    @Override
    public User queryUserByUsername(String username) {
        String sql="select id,username,password,email,head_photo from user where username=?";
        User user = queryForOne(User.class, sql, username);
        return user;
    }


    @Override
    public int saveUser(User user) {
        String sql="insert into user(username,password,email)values(?,?,?)";
        int update=update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        return update;
    }

    @Override
    public User queryUserByEmailAndPassword(String email, String password) {
        String sql="select id,username,password,email,head_photo from user where email=? and password=?";
        User user = queryForOne(User.class, sql, email,password);
        return user;
    }

    @Override
    public User queryUserByEmail(String email) {
        String sql="select id,username,password,email,head_photo from user where email=?";
        User user = queryForOne(User.class, sql, email);
        return user;
    }

    @Override
    public List<PersonalPage> showPersonalPage(String username) {
        String sql="SELECT username,head_photo,title,tag,DATE,post_id \n" +
                "FROM `post`,`user` \n" +
                "WHERE `post`.`author`=`user`.`username` \n" +
                "AND `post`.`author`=?";
        List<PersonalPage> personalPage = queryForList(PersonalPage.class, sql, username);
        if(personalPage.size()==0){
            String sql2="SELECT username,head_photo FROM `user` WHERE username=?";
            personalPage = queryForList(PersonalPage.class, sql2, username);
        }
        return personalPage;
    }


}
