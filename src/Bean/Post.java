package Bean;


public class Post {
    private Integer post_id;
    private String title;
    private String content;
    private String tag;
    private Integer author_id;
    private String date;
    private String author;

    public Post() {
    }

    public Post(Integer post_id, String title, String content, String tag, Integer author_id, String date, String author) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.author_id = author_id;
        this.date = date;
        this.author = author;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", author_id=" + author_id +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
