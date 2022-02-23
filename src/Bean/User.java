package Bean;
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String head_photo;
    private Integer isAdmin;

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(String head_photo) {
        this.head_photo = head_photo;
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email, String head_photo, Integer isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.head_photo = head_photo;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", head_photo='" + head_photo + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
