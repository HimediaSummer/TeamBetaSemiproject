package beta.function.order.dao;

import beta.function.game.dto.GameDTO;
import beta.function.order.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    /*회원별 주문 리스트 담기*/
    void addResultList(Integer userCode);

    /*회원별 주문 리스트*/
    List<PaymentDTO> paymentResult(Integer userCode);
}
