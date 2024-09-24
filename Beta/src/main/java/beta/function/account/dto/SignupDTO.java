package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;

public class SignupDTO {

    private int userCode;
    private String username;    // 사용자 로그인 ID
    private String password;    // 사용자 로그인 PW
    private String userfullname;    // 사용자 이름
    private UserRole authorityCode;

    public SignupDTO() {
    }

    public SignupDTO(String userName, String password, String fullName, String role) {
        this.username = userName;
        this.password = password;
        this.userfullname = userfullname;
        this.authorityCode = authorityCode;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userfullname='" + userfullname + '\'' +
                ", authorityCode=" + authorityCode +
                '}';
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
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

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public UserRole getAuthorityCode() {
        return authorityCode;
    }
}
