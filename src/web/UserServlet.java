package web;

import Bean.PersonalPage;
import Bean.User;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.Gson;
import utils.CheckUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends baseServlet {
    UserService service = new UserServiceImpl();
    CheckUtils checkUtils=new CheckUtils();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Object loginUser1 = request.getSession().getAttribute("loginUser");
        System.out.println(loginUser1);
        if(loginUser1!=null){
            //不等于null,说明已经登录
            request.setAttribute("loginMessage","当前已在登录状态");
            request.getRequestDispatcher("/User/login.jsp").forward(request, response);
            return;
        }
        System.out.println("login来了");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String codeImg = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        User loginUser = service.loginUser(new User(null, null, password, email,null,null));

        if (code != null && code.equalsIgnoreCase(codeImg)) {
            if (loginUser == null) {
                // 把错误信息，和回显的表单项信息，保存到Request域中
                request.setAttribute("loginMessage", "账号或密码错误！");
                request.setAttribute("email", email);
                //   跳回登录页面
                request.getRequestDispatcher("/User/login.jsp").forward(request, response);
            } else {
                // 登录 成功
                // 保存用户登录的信息到Session域中
                System.out.println(loginUser);
                request.getSession().setAttribute("loginUser", loginUser);
                //跳到成功页面login_success.html
                response.sendRedirect("index.jsp");
//                request.getRequestDispatcher("/login_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("loginMessage", "验证码错误！");
            request.setAttribute("email",email);
            request.getRequestDispatcher("/User/login.jsp").forward(request, response);
        }

    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String repwd = request.getParameter("repwd");
        String checkCode = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        User user = new User(null, username, password, email,null,0);

        if (checkCode.equalsIgnoreCase(code)) {
            if(username.length()>20){
                request.setAttribute("msg","昵称长度不得大于20个字符");
                request.setAttribute("registEmail", email);
                request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                return;
            }
            //先判断昵称是否存在或为空
            Map<String, Object> nameMap =checkUtils.CheckUserNameMap(username);
            boolean nameIsNull =(boolean) nameMap.get("isNull");
            boolean existsUsername =(boolean) nameMap.get("existName");
            String nameMsg = (String) nameMap.get("nameMsg");
            if(nameIsNull){
                request.setAttribute("msg",nameMsg);
                request.setAttribute("registEmail", email);
                request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
            }else {
                if (existsUsername) {
                    request.setAttribute("msg",nameMsg);
                    request.setAttribute("registEmail", email);
                    request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                }else {
                    //再判断密码是否规范
                    Map<String, Object> passwordMap = checkUtils.CheckPasswordMap(password);
                    boolean passwordIsNull = (boolean) passwordMap.get("isNull");
                    boolean passwordIsOK = (boolean) passwordMap.get("isOK");
                    String passwordMsg = (String) passwordMap.get("passwordMsg");
                    if(passwordIsNull){
                        request.setAttribute("msg", passwordMsg);
                        request.setAttribute("registEmail", email);
                        request.setAttribute("registUsername", username);
                        request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                    }else {
                        if(passwordIsOK){
                            //判断确认的密码是否相同
                            if(password.equals(repwd)){
                                //判断邮箱是否规范且可用
                                Map<String, Object> emailMap = checkUtils.CheckEmailMap(email);
                                boolean emailIsNull = (boolean) emailMap.get("isNull");
                                boolean emailIsOK = (boolean) emailMap.get("isOK");
                                String emailMsg = (String) emailMap.get("emailMsg");
                                if(emailIsNull){
                                    request.setAttribute("msg",emailMsg);
                                    request.setAttribute("registUsername", username);
                                    request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                                }else {
                                    if(emailIsOK){
                                        //信息全部无误后注册成功
                                        service.registUser(user);
                                        response.sendRedirect(request.getContextPath()+"/User/regist_success.jsp");
                                    }else {
                                        request.setAttribute("msg",emailMsg);
                                        request.setAttribute("registUsername", username);
                                        request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                                    }
                                }

                            }else {
                                request.setAttribute("msg", "两次输入的密码不一致");
                                request.setAttribute("registEmail", email);
                                request.setAttribute("registUsername", username);
                                request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                            }
                        }else {
                            request.setAttribute("msg", passwordMsg);
                            request.setAttribute("registEmail", email);
                            request.setAttribute("registUsername", username);
                            request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
                        }
                    }





                }

            }

        } else {
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("registUsername", username);
            request.setAttribute("registEmail", email);
            request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
        }


    }

    protected void ajaxExistName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        Map<String, Object> map =checkUtils.CheckUserNameMap(username);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        response.getWriter().write(s);
    }
    protected void ajaxCheckEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        Gson gson = new Gson();
        Map<String, Object> map = checkUtils.CheckEmailMap(email);
        String s = gson.toJson(map);
        response.getWriter().write(s);
    }


    protected void ajaxCheckPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String password = request.getParameter("password");
        Map<String, Object> map = checkUtils.CheckPasswordMap(password);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        System.out.println(s);
        response.getWriter().write(s);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String referer = request.getHeader("referer");
        request.getSession().invalidate();
        response.sendRedirect(referer);
    }
    protected void showPersonalPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        List<PersonalPage> personalPage=service.showPersonalPage(username);
        request.setAttribute("personalPage",personalPage);
        request.getRequestDispatcher("personalPage.jsp").forward(request,response);
    }
}
