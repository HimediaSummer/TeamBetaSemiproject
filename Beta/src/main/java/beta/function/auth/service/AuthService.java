package beta.function.auth.service;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import beta.function.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AuthService(AccountService accountService, AccountMapper accountMapper) {
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
}
