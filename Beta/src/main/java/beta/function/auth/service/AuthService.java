package beta.function.auth.service;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.SignupDTO;
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
    private final AccountMapper accountMapper;

    @Autowired
    public AuthService(PasswordEncoder encoder, AccountService accountService, AccountMapper accountMapper) {
        this.encoder = encoder;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
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
        return accountMapper.lostId(email);
    }

    public AccountDTO lostpwd(String email) {

        return accountMapper.lostId(email);
    }

    @Transactional
    public void changepwd(AccountDTO accountInfo) {

        System.out.println("평문 : " + accountInfo.getPassword());
        accountInfo.setPassword(encoder.encode(accountInfo.getPassword()));
        System.out.println("암호문 : " + accountInfo.getPassword());

        accountMapper.changepwd(accountInfo);
    }
}
