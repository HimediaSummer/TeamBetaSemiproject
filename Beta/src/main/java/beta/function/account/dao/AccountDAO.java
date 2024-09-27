package beta.function.account.dao;

import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
import beta.function.account.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDAO {

    List<AccountDTO> findAllMember();

    void registNewMember(AccountDTO newMember);

    AccountDTO findMemberByCode(int userCode);

    void updateMember(AccountDTO member);

    List<AuthorityDTO> findAllAuthority();

    void deleteMember(int userCode);

    //회원가입 , 로그인 , 아이디찾기 , 비밀번호찾기
    int regist(SignupDTO newUserInfo);

    AccountDTO findByUsername(String username);

    AccountDTO lostId(String email);

    AccountDTO lostpwd(String email);

    int changepwd(AccountDTO accountInfo);
}
