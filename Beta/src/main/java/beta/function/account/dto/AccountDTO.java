package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AccountDTO implements UserDetails {

    private int userCode;
    private String username;    // 사용자 로그인 ID
    private String password;    // 사용자 로그인 PW
    private String fullName;    // 사용자 이름
    private UserRole userRole;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> userRole.getRole());

        System.out.println("SpringSecurity가 권한을 요규한다. : " + authorities);

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

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String username, String password, String userfullname, UserRole userRole) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.userfullname = userfullname;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userfullname='" + userfullname + '\'' +
                ", userRole=" + userRole +
                '}';
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

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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

    public AccountDTO(int userCode, String username, String password, String fullName, UserRole userRole) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

