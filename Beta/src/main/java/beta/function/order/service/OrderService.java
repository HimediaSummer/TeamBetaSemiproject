package beta.function.order.service;

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
    public void addOrder(Integer userCode) {

        /*game_cart 있는 userCode 찾아오기*/
        List<CartDTO> cartList = cartService.findByUser(userCode);
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

            // 주문 테이블에 삽입
            orderMapper.insertOrder(order);

            System.out.println("[OrderService] userCode: " + userCode);
            System.out.println("[OrderService] cartCode: " + cartCode);
            System.out.println("[OrderService] gameCode: " + gameCode);
        }

    }
}
