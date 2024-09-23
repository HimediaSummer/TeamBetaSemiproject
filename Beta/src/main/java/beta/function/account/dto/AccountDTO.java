package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountDTO implements UserDetails {

    private int userCode;
    private String userId;    // 사용자 로그인 ID
    private String pwd;    // 사용자 로그인 PW
    private String userName;    // 사용자 이름
    private UserRole userRole;

    public AccountDTO() {
    }

    public AccountDTO(int userCode, String userId, String pwd, String userName, UserRole userRole) {
        this.userCode = userCode;
        this.userId = userId;
        this.pwd = pwd;
        this.userName = userName;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /*설명. 인증된 사용자에게 부여할 권한 컬렉션(List)*/
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> userRole.getRole());

        System.out.println("SpringSecurity가 권한을 요규한다. : " + authorities);

        return List.of();
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "userCode=" + userCode +
                ", username='" + userId + '\'' +
                ", password='" + pwd + '\'' +
                ", fullName='" + userName + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
