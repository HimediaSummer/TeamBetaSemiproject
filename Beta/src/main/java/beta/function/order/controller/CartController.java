package beta.function.order.controller;

import beta.function.order.dto.CartDTO;
import beta.function.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/order")
public class CartController {

    private final CartService cartService;
    private final MessageSource messageSource;

    @Autowired
    public CartController(CartService cartService, @Qualifier("messageSource") MessageSource messageSource) {
        this.cartService = cartService;
        this.messageSource = messageSource;
    }

    /*장바구니 리스트*/
    @GetMapping("/cart")
    public String findCartList(Model model){

        List<CartDTO> cartList = cartService.findAllList();

        model.addAttribute("cartList", cartList);

        System.out.println("CartController");
        return "order/cart";
    }

    /*회원별 장바구니 리스트*/
//    @GetMapping("/cart/{userCode}")
//    public String findCartList(@PathVariable("userCode") int userCode,
//                               Model model){
//
//        List<CartDTO> cartList = cartService.findAllList(userCode);
//
//        model.addAttribute("cartList", cartList);
//
//        System.out.println("CartController");
//
//        return "order/cart";
//    }

    /*게임 담기*/
    @PostMapping("/cart/{gameCode}")
    public String addItem(CartDTO newCart, RedirectAttributes rAttr, Locale locale){
        cartService.addItem(newCart);

        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("addItem", null, locale));

        return "redirect:/order/cart";
    }
}