package beta.function.order.dao;

import beta.function.order.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    /*장바구니 리스트*/
    List<CartDTO> findAllList();

    void addItem(CartDTO newCart);

//    List<CartDTO> findAllList(int userCode);

}