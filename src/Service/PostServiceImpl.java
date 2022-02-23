package Service;

import Bean.Page;
import Bean.Post;
import DAO.PostDAO;
import DAO.PostDAOImpl;

import java.util.List;

public class PostServiceImpl implements PostService{
     PostDAO dao=new PostDAOImpl();
    @Override
    public List<Post> selectPostsByTag(String tag) {
        List<Post> posts = dao.selectPostsByTag(tag);
        return posts;
    }

    @Override
    public Post showPostDetail(int post_id) {
        Post post= dao.showPostDetail(post_id);
        return post;
    }

    @Override
    public void savePost(Post post) {
        dao.savePost(post);
    }

    @Override
    public Page<Post> page(int pageNo, int pageSize,String tag) {
            Page<Post> page = new Page<>();

            //页面显示的条数
            page.setPageSize(pageSize);

            //当前是第几页
            page.setPageNo(pageNo);

            //总记录数
            int totalCount=dao.queryForPageTotalCountByTag(tag);
            page.setTotalCount(totalCount);

            //总页数
            int pageTotal= (totalCount%pageSize)!=0? ( (totalCount/pageSize)+1 ) : (totalCount/pageSize);
            page.setPageTotal(pageTotal);

            //当前显示页
            List<Post> items=dao.queryForPageItemsByTag( (pageNo-1)*pageSize ,pageSize,tag );
            page.setItems(items);
            return page;
        }

    @Override
    public Page<Post> searchPostsAndPage(int pageNo, int pageSize, String search) {
        Page<Post> page = new Page<>();

        //页面显示的条数
        page.setPageSize(pageSize);

        //当前是第几页
        page.setPageNo(pageNo);

        //总记录数
        int totalCount=dao.queryForPageTotalCountBySearch(search);
        page.setTotalCount(totalCount);

        //总页数
        int pageTotal= (totalCount%pageSize)!=0? ( (totalCount/pageSize)+1 ) : (totalCount/pageSize);
        page.setPageTotal(pageTotal);

        //当前显示页
        List<Post> items=dao.queryForPageItemsBySearch( (pageNo-1)*pageSize ,pageSize,search );
        page.setItems(items);
        items.forEach(System.out::println);
        return page;
    }

    @Override
    public Post selectPostsByPostID(String post_id) {
       Post post= dao.selectPostsByPostID(post_id);
        return post;
    }

    @Override
    public void updatePost(Post post) {
        dao.updatePost(post);
    }

    @Override
    public void deletePost(Integer post_id) {
        dao.deletePost(post_id);
    }

}

