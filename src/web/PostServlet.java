package web;

import Bean.Page;
import Bean.Post;
import Service.PostService;
import Service.PostServiceImpl;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

public class PostServlet extends baseServlet {
    PostService service = new PostServiceImpl();

    protected void showPosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String tag = request.getParameter("tag");
        String attrTag = "";
        if ("qianduan".equals(tag)) {
            attrTag = "前端分区";
        } else if ("houduan".equals(tag)) {
            attrTag = "后端分区";
        } else if ("dashuju".equals(tag)) {
            attrTag = "大数据分区";
        } else if ("shenduxuexi".equals(tag)) {
            attrTag = "深度学习分区";
        }
        System.out.println("tag: " + tag);
        System.out.println("attrTag: " + attrTag);
        List<Post> posts = service.selectPostsByTag(tag);
        request.getSession().setAttribute("posts", posts);
        request.getSession().setAttribute("attrTag", attrTag);

        request.getRequestDispatcher("partition.jsp").forward(request, response);
    }

    protected void showPostDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String postId_str = request.getParameter("post_id");
        int post_id = Integer.parseInt(postId_str);
        Post post = service.showPostDetail(post_id);
        request.setAttribute("post", post);
        request.getRequestDispatcher("showPost.jsp").forward(request, response);
    }



    protected void uploadText(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String reqUUID = request.getParameter("uuid");
        HttpSession session = request.getSession();
        String sessUUID = (String) session.getAttribute("uuid");
        session.removeAttribute("uuid");
        String tag = request.getParameter("tag");
        if (reqUUID.equals(sessUUID)) {
            //表单不是重复提交
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int author_id = Integer.parseInt(request.getParameter("author_id"));
            String author = request.getParameter("author");
            if ("0".equals(tag)) {
                request.setAttribute("content", content);
                request.setAttribute("title", title);
                request.setAttribute("msg", "分区不能为空");
                request.getRequestDispatcher("sendPost.jsp").forward(request, response);
            } else if (content == null || "".equals(content)) {
                request.setAttribute("title", title);
                request.setAttribute("msg", "内容不能为空");
                request.getRequestDispatcher("sendPost.jsp").forward(request, response);
            } else if (title == null || "".equals(title)) {
                request.setAttribute("content", content);
                request.setAttribute("msg", "标题不能为空");
                request.getRequestDispatcher("sendPost.jsp").forward(request, response);
            } else {
                if ("1".equals(tag)) {
                    tag = "qianduan";
                }
                if ("2".equals(tag)) {
                    tag = "houduan";
                }
                if ("3".equals(tag)) {
                    tag = "dashuju";
                }
                if ("4".equals(tag)) {
                    tag = "shenduxuexi";
                }
                Post post = new Post(null, title, content, tag, author_id, MyUtils.getDate(), author);
                service.savePost(post);
                response.sendRedirect("postServlet?action=selectByPage&tag=" + tag);
            }


        }else {
            if ("1".equals(tag)) {
                tag = "qianduan";
            }
            if ("2".equals(tag)) {
                tag = "houduan";
            }
            if ("3".equals(tag)) {
                tag = "dashuju";
            }
            if ("4".equals(tag)) {
                tag = "shenduxuexi";
            }
            response.sendRedirect("postServlet?action=selectByPage&tag=" + tag);
        }

    }

    protected void selectByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("selectByPage来了");
        String tag = request.getParameter("tag");
        System.out.println(tag);
        String attrTag = "";
        if ("qianduan".equals(tag)) {
            attrTag = "前端分区";
        } else if ("houduan".equals(tag)) {
            attrTag = "后端分区";
        } else if ("dashuju".equals(tag)) {
            attrTag = "大数据分区";
        } else if ("shenduxuexi".equals(tag)) {
            attrTag = "深度学习分区";
        }
        System.out.println(attrTag);
        int pageNo = MyUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = MyUtils.parseInt(request.getParameter("pageSize"), 2);
        Page<Post> page = service.page(pageNo, pageSize, tag);
        System.out.println(page);
        request.setAttribute("page", page);
        request.setAttribute("attrTag", attrTag);
        request.setAttribute("tag", tag);
        request.getRequestDispatcher("partition.jsp?pageNo=" + pageNo).forward(request, response);
    }

    protected void searchPosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("searchPosts来了");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("search");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < search.length(); i++) {
            sb.append("%");
            sb.append(search.charAt(i));
        }
        sb.append("%");
        String regex = sb.toString();

        System.out.println(search);
        System.out.println(regex);
        int pageNo = MyUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = MyUtils.parseInt(request.getParameter("pageSize"), 2);
        Page<Post> page = service.searchPostsAndPage(pageNo, pageSize, regex);
        request.setAttribute("page", page);
        request.setAttribute("attrTag", "搜索结果");
        request.setAttribute("search",search);
        request.getRequestDispatcher("search.jsp?pageNo=" + pageNo + "&search=" + search).forward(request, response);
    }

    protected void updatePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String post_id = request.getParameter("post_id");
        System.out.println("updatePost的post_id:"+post_id);
        Post post = service.selectPostsByPostID(post_id);
        request.setAttribute("title", post.getTitle());
        request.setAttribute("content", post.getContent());
        request.setAttribute("post_id", post_id);
        System.out.println("updatePost里的post_id"+":"+post_id);
        request.getRequestDispatcher("updatePost.jsp").forward(request, response);
    }

    protected void updateComplete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String reqUUID = request.getParameter("uuid");
        HttpSession session = request.getSession();
        String sessUUID = (String) session.getAttribute("uuid");
        session.removeAttribute("uuid");
        String author = request.getParameter("author");
        if (reqUUID.equals(sessUUID)) {
            //表单不是重复提交
            String tag = request.getParameter("tag");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Integer post_id = Integer.parseInt(request.getParameter("post_id"));
            System.out.println("updateComplete里的post_id"+":"+request.getParameter("post_id"));
            if ("0".equals(tag)) {
                request.setAttribute("content", content);
                request.setAttribute("title", title);
                request.setAttribute("msg", "分区不能为空");
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
            } else if (content == null || "".equals(content)) {
                request.setAttribute("title", title);
                request.setAttribute("msg", "内容不能为空");
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
            } else if (title == null || "".equals(title)) {
                request.setAttribute("content", content);
                request.setAttribute("msg", "标题不能为空");
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
            } else {
                if ("1".equals(tag)) {
                    tag = "qianduan";
                }
                if ("2".equals(tag)) {
                    tag = "houduan";
                }
                if ("3".equals(tag)) {
                    tag = "dashuju";
                }
                if ("4".equals(tag)) {
                    tag = "shenduxuexi";
                }
                Post post = new Post(post_id, title, content, tag, null, null, author);
                service.updatePost(post);
                response.sendRedirect("userServlet?action=showPersonalPage&username="+author);
            }



        }else {

            response.sendRedirect("userServlet?action=showPersonalPage&username="+author);
        }


    }

    protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Integer post_id = Integer.parseInt(request.getParameter("post_id"));
        String author = request.getParameter("author");
        String tag = request.getParameter("tag");
        String search = request.getParameter("search");
        String pageNo = request.getParameter("pageNo");
        service.deletePost(post_id);
        if("".equals(author) || author==null){
            //author为空，即 帖子是管理员在分区或搜索页面删除的
            if("".equals(tag) || tag==null){
                //tag为空，说明是在搜索页面删除的
                response.sendRedirect("postServlet?action=searchPosts&pageNo=" + pageNo + "&search=" + search);
            }else {
                //跳转到分区
                response.sendRedirect("postServlet?action=selectByPage&tag="+tag);
            }

        }else {
            //author不为空，即 帖子是在个人主页删除的
            //跳转到个人主页
            response.sendRedirect("userServlet?action=showPersonalPage&username="+author);
        }

    }
}
