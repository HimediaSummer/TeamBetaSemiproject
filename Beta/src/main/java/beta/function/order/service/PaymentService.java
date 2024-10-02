package beta.function.order.service;

import beta.function.order.dao.PaymentMapper;
import beta.function.order.dto.CartDTO;
import beta.function.order.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final CartService cartService;

    @Autowired
    public PaymentService(PaymentMapper paymentMapper, CartService cartService) {
        this.paymentMapper = paymentMapper;
        this.cartService = cartService;
    }

    public Integer cartListCheck(Integer userCode, List<String> gameCode) {

        return paymentMapper.cartListCheck(userCode ,gameCode);
    }

    /*결제 확인*/
    public List<PaymentDTO> orderResult(int userCode) {

        return paymentMapper.orderResult(userCode);
    }


    /*주문 리스트 결제로 넘겨주기*/
    @Transactional
    public void addPayment(Integer userCode) {

        System.out.println("userCode" + userCode);

        /*game_cart에 있는 userCode 찾아오기*/
//        List<CartDTO> cartList = cartService.findByUser(userCode);
        List<CartDTO> cartList = cartService.haveOrderList(userCode);
        System.out.println("[PS] cartList : " + cartList);

        System.out.println("[PaymentService] addPayment() 1111");
        int totalAmount = cartList.stream()
                .mapToInt(cart -> cart.getGameDTO().getGamePrice())
                .sum();

        System.out.println("[PS] : " + totalAmount);

        PaymentDTO payment = new PaymentDTO();
        payment.setUserCode(userCode);
        payment.setAmount(totalAmount);

        System.out.println("[PaymentService] addPayment() 2222");
        paymentMapper.insertPayment(payment);

        /*장바구니 전체 내역 삭제*/
        cartService.clearCart(userCode);
    }

}
