package beta.function.account.dto;

import beta.function.auth.userRole.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import beta.function.order.dto.CartDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private char suspension;
    private char deletion;
    private int authorityCode;
    private CartDTO cartDTO;

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
}