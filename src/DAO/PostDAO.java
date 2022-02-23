package DAO;

import Bean.Post;

import java.util.List;

public interface PostDAO {
    List<Post> selectPostsByTag(String tag);

    Post showPostDetail(int post_id);

    void savePost(Post post);

    Integer queryForPageTotalCountByTag(String tag);

    List<Post> queryForPageItemsByTag(int begin, int pageSize,String tag);

    Integer queryForPageTotalCountBySearch(String search);

    List<Post> queryForPageItemsBySearch(int begin, int pageSize,String search);

    Post selectPostsByPostID(String post_id);

    void updatePost(Post post);

    void deletePost(Integer post_id);
}
