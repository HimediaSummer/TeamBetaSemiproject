package beta.function.account.service;

import beta.function.account.dao.AccountDAO;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
import beta.function.account.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import beta.function.account.dao.AccountMapper;

@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private PasswordEncoder encoder;
    private final AccountMapper accountMapper;
    
    @Autowired
    public AccountService(PasswordEncoder encoder, AccountDAO accountDAO) {
        this.encoder = encoder;
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public Integer regist(SignupDTO newUserInfo) {

        System.out.println("평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = accountDAO.regist(newUserInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("회원가입 처리 결과 => " + result);

        return result;
    }

    public AccountDTO findByUsername(String username) {

        AccountDTO foundUser = accountDAO.findByUsername(username);

        if (!Objects.isNull(foundUser)) {
            return foundUser;
        } else {
            return null;
        }
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

    public List<AccountDTO> userAllList() {

        return accountMapper.userAllList();
    }
}
