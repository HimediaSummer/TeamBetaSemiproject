package beta.function.account.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import beta.function.auth.userRole.UserRole;

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

    /* 설명. 권한 정보를 반환하는 메서드
     *   UsernamePasswordAuthenticationToken에 사용자 권한 정보를 반환할 때 사용됨.
     *  */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /* 설명. 인증된 사용자에게 부여할 권한 컬렉션(List) */
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> userRole.getRole());

        System.out.println("SpringSecurity가 권한을 요구한다. : " + authorities);

        return List.of();
    }

    /* 설명. 사용자의 비밀번호를 반환하는 메서드
     *   UsernamePasswordAuthenticationToken과 사용자의 비밀번호를 비교할 때 사용됨.
     *  */
    @Override
    public String getPassword() {
        System.out.println("SpringSecurity가 비밀번호를 요구한다 : " + this.pwd);
        return this.pwd;
    }

    /* 설명. 사용자의 아이디를 반환하는 메서드
     *   UsernamePasswordAuthenticationToken과 사용자의 아이디를 비교할 때 사용됨.
     *  */
    @Override
    public String getUsername() {
        System.out.println("SpringSecurity가 아이디를 요구한다 : " + this.userId);
        return this.userId;
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
}
