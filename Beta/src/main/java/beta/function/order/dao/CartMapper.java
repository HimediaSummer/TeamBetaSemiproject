package beta.function.order.dao;

import beta.function.order.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    /*장바구니 리스트*/
    List<CartDTO> findAllList();

    /*회원별 장바구니 리스트*/
//    List<CartDTO> findAllList(int userCode);

    /*장바구니에 담기*/
    void addItem(CartDTO cart);

    /*동일한 게임이 있는지 확인*/
    CartDTO findItemByGameAndUser(int gameCode, int userCode);

    /*장바구니 게임 삭제*/
    void deleteCart(int gameCode);




}