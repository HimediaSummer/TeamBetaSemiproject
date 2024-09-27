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
    private char suspension;
    private char deletion;
    private int authorityCode;
    
    
}