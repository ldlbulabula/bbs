package Service;

import Bean.Page;
import Bean.Post;

import java.util.List;

public interface PostService {
    List<Post> selectPostsByTag (String tag);

    Post showPostDetail(int post_id);

    void savePost(Post post);

    Page<Post> page(int pageNo, int pageSize,String tag);

    Page<Post> searchPostsAndPage(int pageNo, int pageSize,String search);

    Post selectPostsByPostID(String post_id);

    void updatePost(Post post);

    void deletePost(Integer post_id);

}
