package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;

public class SignupDTO {

    private int userCode;
    private String username;    // 사용자 로그인 ID
    private String password;    // 사용자 로그인 PW
    private String fullName;    // 사용자 이름
    private UserRole userRole;

    public SignupDTO() {
    }

    public SignupDTO(int userCode, String username, String password, String fullName, UserRole userRole) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", authorityCode=" + userRole +
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
