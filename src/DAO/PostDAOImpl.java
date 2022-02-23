package DAO;

import Bean.Post;

import java.util.List;

public class PostDAOImpl extends baseDAO implements PostDAO{
    @Override
    public List<Post> selectPostsByTag(String tag) {
        String sql="SELECT post_id,title,content,author_id,tag,DATE,author FROM post WHERE tag=? ORDER BY DATE DESC";
        List<Post> posts = queryForList(Post.class,sql,tag);
        posts.forEach(System.out::println);
        return posts;
    }

    @Override
    public Post showPostDetail(int post_id) {
        String sql="select post_id,title,content,author_id,tag,date,author from post where post_id=?";
        Post post = queryForOne(Post.class, sql, post_id);
        return post;
    }
    @Override
    public void savePost(Post post) {
        String sql="insert into post(post_id,title,content,author_id,tag,date,author) values(?,?,?,?,?,?,?)";
        int update = update(sql, post.getPost_id(), post.getTitle(), post.getContent(), post.getAuthor_id(), post.getTag(),post.getDate(), post.getAuthor());
        System.out.println(update);
    }

    @Override
    public Integer queryForPageTotalCountByTag(String tag) {
        String sql="select count(*) from post where tag=?";
        Number totalCount = (Number) queryForSingleValue(sql,tag);
        return totalCount.intValue();
    }

    @Override
    public List<Post> queryForPageItemsByTag(int begin, int pageSize,String tag) {
        String sql="SELECT post_id,title,content,author_id,tag,date,author FROM post where tag=? ORDER BY DATE DESC limit ?,?";
        List<Post> posts = queryForList(Post.class, sql,tag,begin, pageSize);
        return posts;
    }

    @Override
    public Integer queryForPageTotalCountBySearch(String search) {
        String sql="select count(*) from post where title like ? or author LIKE ?";
        Number totalCount = (Number) queryForSingleValue(sql,search,search);
        return totalCount.intValue();
    }

    @Override
    public List<Post> queryForPageItemsBySearch(int begin, int pageSize, String search) {
        String sql="SELECT post_id,title,content,author_id,tag,date,author FROM post where title like ? or author LIKE ? ORDER BY DATE DESC limit ?,?";
        List<Post> posts = queryForList(Post.class, sql,search,search,begin, pageSize);
        System.out.println(posts);
        return posts;
    }

    @Override
    public Post selectPostsByPostID(String post_id) {
        String sql="SELECT post_id,title,content,author_id,tag,date,author FROM post where post_id=?";
        Post post = queryForOne(Post.class, sql, post_id);
        return post;
    }

    @Override
    public void updatePost(Post post) {
        String sql="update post SET title=?,content=?,tag=? WHERE post_id=?";
        int update = update(sql, post.getTitle(), post.getContent(), post.getTag(), post.getPost_id());
        System.out.println(update);
    }

    @Override
    public void deletePost(Integer post_id) {
        String sql="DELETE FROM post WHERE post_id=?";
        int update = update(sql, post_id);
        System.out.println(update);
    }

}
