package beta.function.order.service;


import beta.function.game.dto.GameDTO;
import beta.function.order.dao.CartMapper;
import beta.function.order.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartService {

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    /*장바구니 리스트*/
    public List<CartDTO> findAllList() {

        System.out.println("CartService");
        return cartMapper.findAllList();
    }


    /*회원별 장바구니 리스트*/
    public List<CartDTO> findByUser(int userCode) {

        System.out.println("CartService");
        return cartMapper.findByUser(userCode);
    }

    /*장바구니에 담기*/
    @Transactional
    public void addItem(int gameCode, int userCode) {

        // 동일한 게임이 있는지 확인
//        CartDTO existingItem = cartMapper.findItemByGameAndUser(gameCode, userCode);

        // 동일한 게임이 있으면 추가하지 않음
//        if (existingItem != null) {
//            throw new IllegalStateException("이미 장바구니에 추가된 게임입니다.");
//        }

        CartDTO cart = new CartDTO();
        cart.setUserCode(userCode);
        cart.setGameCode(gameCode);

        cartMapper.addItem(cart);
    }

    /*장바구니 게임 삭제*/
    @Transactional
    public void deleteCart(int gameCode) {

        cartMapper.deleteCart(gameCode);
    }

    /*장바구니 전체 내역 삭제*/
    public void clearCart(Integer userCode) {

        cartMapper.deleteAllCart(userCode);
    }

    /*게임 총 금액*/
    public int gamePriceTotal(int userCode) {

        List<CartDTO> cartList = cartMapper.findByUser(userCode);
        int total = cartList.stream()
                .mapToInt(cart -> cart.getGameDTO().getGamePrice())
                .sum();

        return total;
    }
}
