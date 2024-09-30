package beta.function.account.service;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AccountService {

    private PasswordEncoder encoder;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(PasswordEncoder encoder, AccountMapper accountMapper) {
        this.encoder = encoder;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public Integer regist(SignupDTO newUserInfo) {

        System.out.println("평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = accountMapper.regist(newUserInfo);
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

        AccountDTO foundUser = accountMapper.findByUsername(username);

        if (!Objects.isNull(foundUser)) {
            return foundUser;
        } else {
            return null;
        }
    }

    public Integer checkId(SignupDTO newUserInfo) {

        System.out.println("어카운트 서비스 : 체크 아이디 호출됨");
        accountMapper.checkId(newUserInfo);
        System.out.println("어카운터 서비스 : 아이디 체크 완료");

        Integer result = accountMapper.checkId(newUserInfo);

        return result;
    }
}
