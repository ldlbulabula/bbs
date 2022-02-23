package test;

import Bean.Post;
import DAO.PostDAOImpl;
import org.junit.Test;

import java.util.List;


public class PostDAOTest {
    PostDAOImpl dao= new PostDAOImpl();
    @Test
    public void selectPostsByTag() {
        List<Post> houduan = dao.selectPostsByTag("shenduxuexi");
        System.out.println(houduan);
    }

    @Test
    public void showPostDetail() {
    }

    @Test
    public void savePost() {
    }
}