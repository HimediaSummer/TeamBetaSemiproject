package beta.function.order.service;


import beta.function.order.dao.CartMapper;
import beta.function.order.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartService {

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {

        this.cartMapper = cartMapper;
    }

    /*장바구니 리스트*/
    public List<CartDTO> findAllList() {

        System.out.println("CartService");
        return cartMapper.findAllList();
    }

    @Transactional
    public void addItem(CartDTO newCart) {
        cartMapper.addItem(newCart);
    }

//    public List<CartDTO> findAllList(int userCode) {
//
//        System.out.println("CartService");
//        return cartMapper.findAllList(userCode);
//    }

}
