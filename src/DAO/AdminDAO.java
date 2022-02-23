package DAO;

import Bean.Admin;
import Bean.User;

import java.util.List;

public interface AdminDAO {
    Admin queryAdminByEmailAndPassword(String email, String password);

    Integer queryForPageTotalCountOfUsers();

    List<User> queryForPageItemsOfUsers(int begin, int pageSize);

    void deleteUserByID(int user_id);

    User selectUserByID(int id);

    void setUserAsAdmin(User user);

    void setIsAdminAsOne(int id);
}
