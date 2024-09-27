package beta.function.account.dao;

import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
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
}
