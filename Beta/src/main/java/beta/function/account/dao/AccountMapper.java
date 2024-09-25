package beta.function.account.dao;

import beta.function.account.dto.AccountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    List<AccountDTO> userAllList();
}
