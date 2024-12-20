package beta.function.order.service;


import beta.function.game.dto.GameDTO;
import beta.function.order.dao.CartMapper;
import beta.function.order.dao.OrderMapper;
import beta.function.order.dao.PaymentMapper;
import beta.function.order.dto.CartDTO;
import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartService {

    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    private final PaymentMapper paymentMapper;

    @Autowired
    public CartService(CartMapper cartMapper, OrderMapper orderMapper, PaymentMapper paymentMapper) {
        this.cartMapper = cartMapper;
        this.orderMapper = orderMapper;
        this.paymentMapper = paymentMapper;
    }

    /*장바구니 리스트*/
    public List<CartDTO> findAllList() {

        System.out.println("CartService");
        return cartMapper.findAllList();
    }


    /*회원별 장바구니 리스트*/
    public List<CartDTO> findByUser(int userCode) {

        System.out.println("CartService findByUser" + userCode);

        return cartMapper.findByUser(userCode);
    }

    public List<CartDTO> haveOrderList(int userCode) {

        return cartMapper.haveOrderList(userCode);
    }

    /*장바구니에 담기*/
    @Transactional
    public void addItem(int gameCode, int userCode) {

        CartDTO cart = new CartDTO();
        cart.setUserCode(userCode);
        cart.setGameCode(gameCode);

        System.out.println("cart list :" + cart);
        Integer ischeck = cartMapper.cartListCheck(cart);

        System.out.println("ischeck" + ischeck);

        if(ischeck == 1) {
            cartMapper.updateItem(cart); // update
        } else if (ischeck == 0){
            cartMapper.addItem(cart); // insert
        }

        // 동일한 게임이 있는지 확인
//        CartDTO existingItem = cartMapper.findItemByGameAndUser(gameCode, userCode);

        // 동일한 게임이 있으면 추가하지 않음
//        if (existingItem != null) {
//            throw new IllegalStateException("이미 장바구니에 추가된 게임입니다.");
//        }

//        CartDTO cart = new CartDTO();
//        cart.setUserCode(userCode);
//        cart.setGameCode(gameCode);

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


//    public void gameCodeList(String userCode, List<String> gameCodeList) {
    public void gameCode(int userCode , List<String> gameCode) {

        System.out.println("gamecode service 1");
        System.out.println("gamecode service 2" + gameCode);
//       cartMapper.gameCodeList(userCode, gameCodeList);
       cartMapper.gameCode(userCode, gameCode);
        System.out.println("gamecode service 3" + gameCode);
    }

    public void deleteY() {
        cartMapper.deleteY();
    }

    public List<CartDTO> haveOrderList() {

        return cartMapper.haveOrderList();
    }

    /*유저코드만*/
    public void findUser() {

        cartMapper.findUser();
    }

    public int isCheckOrderList(int userCode, int gameCode) {

        return orderMapper.isCheckOrderList(userCode, gameCode);
    }

    public void OrderGameN(int userCode, int gameCode) {

        PaymentDTO payment = new PaymentDTO();
        payment.setUserCode(userCode);
        paymentMapper.insertPayment(payment);

        List<CartDTO> cartList = cartMapper.findByUserNoOrder(userCode);
        System.out.println("[CC] cartList111 : " + cartList);

        System.out.println(" 포문 도는지 확인1 ");

        for (CartDTO cart : cartList) {
            int paymentCode = payment.getPaymentCode();
            int cartCode = cart.getCartCode();
            OrderDTO order = new OrderDTO();
            order.setUserCode(userCode);
            order.setCartCode(cartCode);
            order.setGameCode(gameCode);
            order.setPaymentCode(paymentCode);

            System.out.println("order 내용물 " + order);
            System.out.println(" 포문 도는지 확인 2 ");

            orderMapper.OrderGameN(order);
        }

        System.out.println(" 포문 도는지 확인 3 ");

    }
}
