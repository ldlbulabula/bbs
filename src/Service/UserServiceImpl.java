package Service;


import Bean.PersonalPage;
import Bean.User;
import DAO.UserDAO;
import DAO.UserDAOimpl;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDAO dao=new UserDAOimpl();
    @Override
    public void registUser(User user) {
        int i = dao.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        User user1 = dao.queryUserByEmailAndPassword(user.getEmail(), user.getPassword());
        return user1;
    }

    @Override
    public boolean existsEmail(String email) {
        User user = dao.queryUserByEmail(email);
        if(user==null){
            //不存在
            //表示可用
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean existsUsername(String username) {
        User user = dao.queryUserByUsername(username);
        if(user==null){
            //不存在
            //表示可用
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<PersonalPage> showPersonalPage(String username) {
        List<PersonalPage> personalPage= dao.showPersonalPage(username);
        return personalPage;
    }

}
