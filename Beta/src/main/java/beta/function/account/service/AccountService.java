package beta.function.account.service;

import beta.function.account.dao.AccountDAO;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<AccountDTO> findAllMember() {
        return accountDAO.findAllMember();
    }

    public List<AuthorityDTO> findAllAuthority() {
        return accountDAO.findAllAuthority();
    }

    @Transactional
    public void registNewMember(AccountDTO newMember) {
        accountDAO.registNewMember(newMember);
    }

    public AccountDTO findMemberByCode(int userCode) {
        return accountDAO.findMemberByCode(userCode);
    }

    @Transactional
    public void updateMember(AccountDTO member) {
        accountDAO.updateMember(member);
    }

    @Transactional
    public void deleteMember(int userCode) {

        accountDAO.deleteMember(userCode);
    }

}
