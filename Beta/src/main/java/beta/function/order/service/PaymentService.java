package beta.function.order.service;

import beta.function.order.dao.OrderMapper;
import beta.function.order.dto.CartDTO;
import beta.function.order.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final CartService cartService;

    @Autowired
    public OrderService(OrderMapper orderMapper, CartService cartService) {
        this.orderMapper = orderMapper;
        this.cartService = cartService;
    }

    /*결제 확인*/
    public List<PaymentDTO> orderResult(int userCode) {

        return orderMapper.orderResult(userCode);
    }


    /*주문 리스트 결제로 넘겨주기*/
    @Transactional
    public void addPayment(Integer userCode) {

        /*game_cart에 있는 userCode 찾아오기*/
        List<CartDTO> cartList = cartService.findByUser(userCode);
        System.out.println("[OS] cartList : " + cartList);

        System.out.println("[OrderService] addPayment() 1111");
        int totalAmount = cartList.stream()
                .mapToInt(cart -> cart.getGameDTO().getGamePrice())
                .sum();

        System.out.println("[OS] : " + totalAmount);

        PaymentDTO payment = new PaymentDTO();
        payment.setUserCode(userCode);
        payment.setAmount(totalAmount);

        System.out.println("[OrderService] addPayment() 2222");
        orderMapper.insertPayment(payment);

        /*장바구니 전체 내역 삭제*/
        cartService.clearCart(userCode);
    }
}
