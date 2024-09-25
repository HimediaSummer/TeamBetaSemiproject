package beta.function.account.dao;


import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    int regist(SignupDTO newUserInfo);

    AccountDTO findByUsername(String username);

    AccountDTO lostId(String email);
}
