package beta.function.order.service;


import beta.function.account.dto.AccountDTO;
import beta.function.order.dao.CartMapper;
import beta.function.order.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CartService {

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

//    public int addCart(CartDTO request, int gameCode) {
//        AccountDTO member = (AccountDTO) cartMapper.findByUserCode(request.getUserCode())
//                .orElseThrow(() -> new NoSuchElementException(MEMBER_NOT_FOUND));
//
//        GameDTO game = (GameDTO) cartMapper.findByGameCode(gameCode)
//                .orElseThrow();
//
//        CartDTO existingCart = (CartDTO) cartMapper.findByGameManagementAndMember(game, member).orElse(null);
//
//        if(existingCart == null){
//        }
//
//    }

    public List<CartDTO> findAllList(int userCode) {
        System.out.println("서비스리스트호출");
        return cartMapper.findAllList(userCode);
    }

}
