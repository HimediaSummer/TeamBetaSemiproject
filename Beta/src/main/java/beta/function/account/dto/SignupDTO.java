package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;

import java.io.Serializable;
import java.util.Date;

public class SignupDTO implements Serializable {

    private String username;    // 사용자 로그인 ID
    private String password;    // 사용자 로그인 PW
    private String fullName;    // 사용자 이름
    private String nickName;    // 사용자 닉네임
    private String  birthday;      // 사용자 생일
    private String email;       // 사용자 이메일
    private String phone;       // 사용자 폰번호
    private String profileimg;  // 사용자 프로필 사진
    private UserRole userRole;  // 사용자 권한

    public SignupDTO() {
    }

    public SignupDTO(String username, String password, String fullName, String nickName, String  birthday, String email, String phone, String profileimg, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.profileimg = profileimg;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", authorityCode=" + userRole +
                '}';
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String  getBirthday() {
        return birthday;
    }

    public void setBirthday(String  birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
