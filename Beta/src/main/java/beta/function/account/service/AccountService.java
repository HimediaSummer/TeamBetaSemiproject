package beta.function.account.service;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountService {

    private AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    public AccountDTO findByUsername(String username) {

        AccountDTO foundUser = accountMapper.findByUsername(username);

        if (!Objects.isNull(foundUser)) {
            return foundUser;
        }else  {
            return null;
        }
    }

}
