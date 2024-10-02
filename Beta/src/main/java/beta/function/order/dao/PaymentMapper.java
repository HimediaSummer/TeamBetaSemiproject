package beta.function.order.dao;

import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    /*game_payment에 넣어주기*/
    void insertPayment(PaymentDTO payment);

    /*결제 확인*/
    List<PaymentDTO> orderResult(int userCode);

    void insertOrder(OrderDTO order);
}
