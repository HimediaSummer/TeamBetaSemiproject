package beta.function.order.dao;

import beta.function.order.dto.CartDTO;
import beta.function.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    /*장바구니 리스트*/
    List<CartDTO> findAllList();

    /*회원별 장바구니 리스트*/
    List<CartDTO> findByUser(int userCode);

    /*장바구니에 담기*/
    void addItem(CartDTO cart);
    void updateItem(CartDTO cart);

    /*동일한 게임이 있는지 확인*/
//    CartDTO findItemByGameAndUser(int gameCode, int userCode);

    /*장바구니 게임 삭제*/
    void deleteCart(int gameCode);

    /*장바구니 전체 내역 삭제*/
    void deleteAllCart(Integer userCode);

    //    장바구니 담기 전 장바구니 체크
    int cartListCheck(CartDTO cart);

    //    void gameCode(@Param("userCode") String userCode, @Param("gameCodeList") List<String> gameCodeList);
    void gameCode(Integer userCode, @Param("list") List<String> gameCode);

    void deleteY();

    List<CartDTO> haveOrderList(int userCode);

    List<CartDTO> haveOrderList();

    /*유저코드만*/
    void findUser();



}