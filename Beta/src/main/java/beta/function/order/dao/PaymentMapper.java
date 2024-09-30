package beta.function.order.dao;

import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    /*game_payment에 넣어주기*/
    void insertPayment(PaymentDTO payment);

    /*결제 확인*/
    List<PaymentDTO> orderResult(int userCode);
}
