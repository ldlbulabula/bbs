package Service;


import Bean.PersonalPage;
import Bean.User;

import java.util.List;

public interface UserService {

    void registUser(User user);
    User loginUser(User user);

    //如果存在则返回true,不存在则返回false
    boolean existsEmail(String email);
    boolean existsUsername(String username);

    List<PersonalPage> showPersonalPage(String username);
}
