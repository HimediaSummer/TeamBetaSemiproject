package beta.function.account.service;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<AccountDTO> userAllList() {

        return accountMapper.userAllList();
    }
}