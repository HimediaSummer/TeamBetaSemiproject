package beta.function.auth.service;

import beta.function.account.dao.AccountDAO;
import beta.function.account.dto.AccountDTO;
import beta.function.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private PasswordEncoder encoder;
    private AccountService accountService;
    private final AccountDAO accountDAO;

    @Autowired
    public AuthService(PasswordEncoder encoder, AccountService accountService, AccountDAO accountDAO) {
        this.encoder = encoder;
        this.accountService = accountService;
        this.accountDAO = accountDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AccountDTO foundUser = accountService.findByUsername(username);

        if (Objects.isNull(foundUser)) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return foundUser;
    }

    public AccountDTO lostid(String email) {
        System.out.println("버튼호출 어스서비스 1 ");

        return accountDAO.lostId(email);
    }

    public AccountDTO lostpwd(String email) {
        System.out.println("버튼호출 어스서비스 1 ");

        return accountDAO.lostId(email);
    }

    @Transactional
    public Integer changepwd(AccountDTO accountInfo) {

        System.out.println("데이터 뭐 가져옴? 어스서비스 changepwd  1 : " + accountInfo);
        System.out.println("평문 : " + accountInfo.getPassword());
        accountInfo.setPassword(encoder.encode(accountInfo.getPassword()));
        System.out.println("암호문 : " + accountInfo.getPassword());
        System.out.println("데이터 뭐 가져옴? 어스서비스 changepwd  2 : " + accountInfo);

        Integer changepwd = null;

        try {
            changepwd = accountDAO.changepwd(accountInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            changepwd = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            changepwd = 0;
            e.printStackTrace();
        }
        System.out.println("회원가입 처리 결과 => " + changepwd);
        return changepwd;
    }
}
