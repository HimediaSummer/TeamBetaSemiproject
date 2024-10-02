package beta.function.order.service;

import beta.function.game.dto.GameDTO;
import beta.function.order.dao.CartMapper;
import beta.function.order.dao.OrderMapper;
import beta.function.order.dao.PaymentMapper;
import beta.function.order.dto.CartDTO;
import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final CartMapper cartMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper, CartService cartService, PaymentService paymentService, PaymentMapper paymentMapper, CartMapper cartMapper) {
        this.orderMapper = orderMapper;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
        this.cartMapper = cartMapper;
    }

    public List<OrderDTO> findOrderList() {

        return orderMapper.findOrderList();
    }

    @Transactional
    public void addOrder(int userCode) {

        /*game_cart 있는 userCode 찾아오기*/
//        List<CartDTO> cartList = cartService.findByUser(userCode);
        List<CartDTO> cartList = cartService.haveOrderList(userCode);
        System.out.println("[OS] cartList : " + cartList);

        /*game_payment에 있는 userCode 찾아오기*/
        List<PaymentDTO> paymentList = paymentService.orderResult(userCode);
        System.out.println("[OS] paymentList : " + paymentList);

        PaymentDTO payment = new PaymentDTO();
        payment.setUserCode(userCode);
        paymentMapper.insertPayment(payment);

        int paymentCode = payment.getPaymentCode();
        System.out.println("[OrderService] paymentCode : " + paymentCode);

        for (CartDTO cart : cartList) {
            int cartCode = cart.getCartCode();
            int gameCode = cart.getGameCode();

            OrderDTO order = new OrderDTO();
            order.setUserCode(userCode);
            order.setCartCode(cartCode);
            order.setGameCode(gameCode);
            order.setPaymentCode(paymentCode);

//            orderMapper.insertOrder(order); // insert

            System.out.println("[OrderService] userCode: " + userCode);
            System.out.println("[OrderService] cartCode: " + cartCode);
            System.out.println("[OrderService] gameCode: " + gameCode);

            Integer ordercheck = orderMapper.cartListCheck(order);

            System.out.println("ordercheck 1 " + ordercheck);

            System.out.println("order 내용물 " + order);

            if(ordercheck == 1) {
                System.out.println("ordercheck if문 1 일때 ");
                orderMapper.updateOrder(order); // update
            } else if (ordercheck == 0){
                System.out.println("ordercheck if문 0 일때 ");
                orderMapper.insertOrder(order); // insert
            }
        }

    }

    public OrderDTO userByOrderList(int orderCode) {

        return orderMapper.userByOrderList(orderCode);
    }
}
