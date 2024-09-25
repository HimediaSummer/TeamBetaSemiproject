package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDTO implements UserDetails {

    private int userCode;
    private String username;
    private String password;
    private String fullName;
    private UserRole userRole;
    private String nickName;    // 사용자 닉네임
    private String birthday;      // 사용자 생일
    private String email;       // 사용자 이메일
    private String phone;       // 사용자 폰번호
    private String profileimg;  // 사용자 프로필 사진

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> userRole.getRole());

        System.out.println("SpringSecurity가 권한을 요구한다 : " + authorities);

        return authorities;
    }


    @Override
    public String getPassword() {
        System.out.println("SpringSecurity가 비밀번호를 요구한다 : " + this.password);
        return this.password;
    }


    @Override
    public String getUsername() {
        System.out.println("SpringSecurity가 아이디를 요구한다 : " + this.username);
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }


    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String username, String password, String fullName, UserRole userRole, String nickName, String birthday, String email, String phone, String profileimg) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
        this.nickName = nickName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.profileimg = profileimg;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    @Override
    public String toString() {
        return "AccountDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userRole=" + userRole +
                ", nickName='" + nickName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profileimg='" + profileimg + '\'' +
                '}';
    }
}