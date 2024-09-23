package beta.function.auth.service;


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

    @Autowired
    public AuthService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountDTO foundUser = accountService.findByUsername(username);

        if (Objects.isNull(foundUser)) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        return foundUser;
    }


}
