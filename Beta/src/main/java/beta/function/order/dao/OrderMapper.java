package beta.function.order.dao;

import beta.function.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderDTO> findOrderList();



    OrderDTO userByOrderList(int orderCode);

    /*game_order 중복체크 확인*/

    Integer cartListCheck(OrderDTO orderList);
//    Integer cartListCheck(List<OrderDTO> orderList);

    void updateOrder(OrderDTO orderList);

    void insertOrder(OrderDTO order);
}
