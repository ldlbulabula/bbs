package Bean;

public class PersonalPage {
    private String username;
    private String head_photo;
    private String title;
    private String tag;
    private String date;
    private int post_id;
    public PersonalPage() {
    }

    public PersonalPage(String username, String head_photo, String title, String tag, String date, int post_id) {
        this.username = username;
        this.head_photo = head_photo;
        this.title = title;
        this.tag = tag;
        this.date = date;
        this.post_id = post_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(String head_photo) {
        this.head_photo = head_photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PersonalPage{" +
                "username='" + username + '\'' +
                ", head_photo='" + head_photo + '\'' +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", date='" + date + '\'' +
                ", post_id=" + post_id +
                '}';
    }
}
