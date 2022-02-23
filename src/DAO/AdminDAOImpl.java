package DAO;

import Bean.Admin;
import Bean.User;

import java.util.List;

public class AdminDAOImpl extends baseDAO implements AdminDAO{
    @Override
    public Admin queryAdminByEmailAndPassword(String email, String password) {
        String sql="select admin_id,adminName,adminPassword,adminEmail from `admin` where adminEmail=? and adminPassword=?";
        Admin admin = queryForOne(Admin.class, sql, email,password);
        return admin;
    }

    @Override
    public Integer queryForPageTotalCountOfUsers() {
        String sql="select count(*) from user";
        Number totalCount = (Number) queryForSingleValue(sql);
        return totalCount.intValue();
    }

    @Override
    public List<User> queryForPageItemsOfUsers(int begin, int pageSize) {
        String sql="SELECT id,username,password,email,head_photo,isAdmin FROM user ORDER BY isAdmin DESC  limit ?,?";
        List<User> users = queryForList(User.class, sql,begin, pageSize);
        System.out.println(users);
        return users;
    }

    @Override
    public void deleteUserByID(int id) {
        String sql="DELETE FROM user WHERE id=?";
        int update = update(sql,id);
        System.out.println(update);
    }

    @Override
    public User selectUserByID(int id) {
        String sql="SELECT id,username,password,email,head_photo,isAdmin FROM user where id=?";
        User user = queryForOne(User.class, sql, id);
        return user;
    }

    @Override
    public void setUserAsAdmin(User user) {
        String sql="INSERT INTO `admin`(admin_id,adminName,adminPassword,adminEmail)VALUES(?,?,?,?)";
        int update = update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
        System.out.println("setUserAsAdmin:"+update);
    }

    @Override
    public void setIsAdminAsOne(int id) {
        String sql="UPDATE `user` SET isAdmin=1 WHERE id=?";
        int update = update(sql, id);
        System.out.println("setIsAdminAsOne:"+update);
    }
}
