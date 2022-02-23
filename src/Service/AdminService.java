package Service;

import Bean.Admin;
import Bean.Page;
import Bean.User;

public interface AdminService {
    Admin loginAdmin(Admin admin);

    Page<User> page(int pageNo, int pageSize);

    void deleteUserByID(int id);

    void setAsAdmin(int id);

}
