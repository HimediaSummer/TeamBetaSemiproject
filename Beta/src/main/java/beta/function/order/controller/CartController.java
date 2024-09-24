package beta.function.order.controller;

import beta.function.order.dto.CartDTO;
import beta.function.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/order")
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

//    @GetMapping(value = "user", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<AccountDTO> findCategoryList() {
//        System.out.println("JavaScript 내장 함수인 fetch");
//        return cartService.findAllUserCode();
//    }

    /*장바구니에 담기*/
//    @GetMapping("/add/{gameCode}")
//    public List<CartDTO> addCart(@PathVariable int gameCode,
//                                          RedirectAttributes rAttr,
//                                          Locale locale){
//        cartService.addCart(gameCode);
//
//        rAttr.addFlashAttribute("successMessage", "장바구니에 등록되었습니다.");
//
//        return "redirect:/menu/detail";
//    }

    /*회원별 장바구니 리스트*/
    @GetMapping("cart/{userCode}")
    public String findCartList(@PathVariable int userCode,
                               Model model){

        List<CartDTO> cartList = cartService.findAllList(userCode);

        System.out.println("[CC] 회원 코드 : " + userCode);

        model.addAttribute("cartList", cartList);

        return "order/cart";
    }
}