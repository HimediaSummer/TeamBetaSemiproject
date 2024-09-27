package beta.function.order.service;

import beta.function.game.dto.GameDTO;
import beta.function.order.dao.PaymentMapper;
import beta.function.order.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    /*회원별 주문 리스트 담기*/
    public void addResultList(Integer userCode) {

        paymentMapper.addResultList(userCode);
    }

    /*회원별 주문 리스트*/
    public List<PaymentDTO> paymentResult(Integer userCode) {

        return paymentMapper.paymentResult(userCode);
    }

}
