package web;

import Bean.Admin;
import Bean.Page;
import Bean.Post;
import Bean.User;
import Service.AdminService;
import Service.AdminServiceImpl;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class AdminServlet extends baseServlet{
    AdminService service=new AdminServiceImpl();
    protected void AdminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Object loginAdmin1 = request.getSession().getAttribute("loginAdmin");
        System.out.println(loginAdmin1);
        if(loginAdmin1!=null){
            //不等于null,说明已经登录
            request.setAttribute("loginMessage","当前已在登录状态");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return;
        }
        System.out.println("loginAdmin来了");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String codeImg = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        Admin loginAdmin = service.loginAdmin(new Admin(null, null, password, email));

        if (code != null && code.equalsIgnoreCase(codeImg)) {
            if (loginAdmin == null) {
                // 把错误信息，和回显的表单项信息，保存到Request域中
                request.setAttribute("loginMessage", "账号或密码错误！");
                request.setAttribute("email", email);
                //   跳回登录页面
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            } else {
                // 登录 成功
                // 保存管理员登录的信息到Session域中
                System.out.println(loginAdmin);
                request.getSession().setAttribute("loginAdmin", loginAdmin);
                //跳到成功页面login_success.html
                response.sendRedirect("index.jsp");
//                request.getRequestDispatcher("/login_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("loginMessage", "验证码错误！");
            request.setAttribute("email",email);
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        }
    }

    protected void manageUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int pageNo = MyUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = MyUtils.parseInt(request.getParameter("pageSize"), 5);
        Page<User> userPage = service.page(pageNo, pageSize);
        request.setAttribute("userPage", userPage);
        request.getRequestDispatcher("/admin/manageUsers.jsp?pageNo="+pageNo).forward(request, response);
    }

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int pageNo = MyUtils.parseInt(request.getParameter("pageNo"), 1);
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUserByID(id);
        response.sendRedirect("adminServlet?action=manageUser&pageNo="+pageNo);
    }

    protected void setAsAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int pageNo = MyUtils.parseInt(request.getParameter("pageNo"), 1);
        int id = Integer.parseInt(request.getParameter("id"));
        service.setAsAdmin(id);
        response.sendRedirect("adminServlet?action=manageUser&pageNo="+pageNo);
    }
}
