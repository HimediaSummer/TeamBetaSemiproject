package beta.function.order.dao;

import beta.function.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderDTO> findOrderList();

    void insertOrder(OrderDTO order);
}
