package beta.function.order.dao;

import beta.function.account.dto.AccountDTO;
import beta.function.game.dto.GameDTO;
import beta.function.order.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

//    회원별 장바구니 리스트
    List<CartDTO> findAllList(int userCode);

//    회원 코드 가져오기
//    Optional<Object> findByUserCode(int userCode);

//    게임 코드 가져오기
//    Optional<Object> findByGameCode(int gameCode);

//    회원, 게임 정보로 장바구니 객체 가져오기
//    Optional<Object> findByGameManagementAndMember(GameDTO game, AccountDTO member);

//    void addCart(AccountDTO member, GameDTO game);

}