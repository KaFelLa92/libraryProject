package libraryProject.model.dto;

public class UserDto {
    // 1. 멤버변수
    private String uid;
    private String upwd;
    private String uname;
    private String uphone;

    // 2. 생성자
    public UserDto() {
    }

    public UserDto(String uid, String upwd, String uname, String uphone) {
        this.uid = uid;
        this.upwd = upwd;
        this.uname = uname;
        this.uphone = uphone;
    }

    public UserDto(String uid, String upwd) {
        this.uid = uid;
        this.upwd = upwd;
    }

    // 3. 메소드
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                ", uid='" + uid + '\'' +
                ", upwd='" + upwd + '\'' +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                '}';
    }
}