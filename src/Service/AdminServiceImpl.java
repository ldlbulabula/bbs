package Service;

import Bean.Admin;
import Bean.Page;
import Bean.User;
import DAO.AdminDAO;
import DAO.AdminDAOImpl;


import java.util.List;

public class AdminServiceImpl implements AdminService{
    AdminDAO dao=new AdminDAOImpl();

    @Override
    public Admin loginAdmin(Admin admin) {
        return dao.queryAdminByEmailAndPassword(admin.getAdminEmail(),admin.getAdminPassword());
    }

    @Override
    public Page<User> page(int pageNo, int pageSize) {
        Page<User> page = new Page<>();

        //页面显示的条数
        page.setPageSize(pageSize);

        //当前是第几页
        page.setPageNo(pageNo);

        //总记录数
        int totalCount=dao.queryForPageTotalCountOfUsers();
        page.setTotalCount(totalCount);

        //总页数
        int pageTotal= (totalCount%pageSize)!=0? ( (totalCount/pageSize)+1 ) : (totalCount/pageSize);
        page.setPageTotal(pageTotal);

        //当前显示页
        List<User> items=dao.queryForPageItemsOfUsers( (pageNo-1)*pageSize ,pageSize);
        page.setItems(items);
        items.forEach(System.out::println);
        return page;
    }

    @Override
    public void deleteUserByID(int id) {
        dao.deleteUserByID(id);
    }

    @Override
    public void setAsAdmin(int id) {
       dao.setIsAdminAsOne(id);
       User user =dao.selectUserByID(id);
       dao.setUserAsAdmin(user);
    }
}
