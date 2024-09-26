package beta.function.order.controller;

import beta.function.order.dto.CartDTO;
import beta.function.order.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CartController {

    private final CartService cartService;
    private final MessageSource messageSource;

    @Autowired
    public CartController(CartService cartService, @Qualifier("messageSource") MessageSource messageSource) {
        this.cartService = cartService;
        this.messageSource = messageSource;
    }

    /*장바구니 리스트*/
    @GetMapping("/order/cart")
    public String findCartList(Model model){

        List<CartDTO> cartList = cartService.findAllList();

        model.addAttribute("cartList", cartList);

        System.out.println("CartController");
        return "order/cart";
    }

    /*회원별 장바구니 리스트*/
     @PostMapping("/order/userCart/{userCode}")
     public String findCartList(@PathVariable("userCode") int userCode,
                                Model model){

         List<CartDTO> users = cartService.findByUser(userCode);

         model.addAttribute("users", users);

         return "order/userCart";
     }

    /*장바구니에 담기*/
    @PostMapping("/game/insert/{gameCode}")
    public String addItem(@PathVariable("gameCode") int gameCode,
                          HttpSession session,
                          RedirectAttributes rAttr){

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 3);

        Integer userCode = (Integer) session.getAttribute("userCode");

        if (userCode == null) {
            System.out.println("로그인 하고 담자~");
            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }

        System.out.println("담으러 갈까?");
        try{
            cartService.addItem(gameCode, userCode);
            rAttr.addFlashAttribute("successMessage", "장바구니에 담겼습니다.");
        }catch (IllegalStateException e){
            rAttr.addFlashAttribute("errorMessage", "이미 장바구니에 있는 게임입니다.");
        }

        return "redirect:/game/listAll";
    }

    /*장바구니 게임 삭제*/
    @PostMapping("/cart/delete/{gameCode}")
    public String deleteCart(@PathVariable("gameCode") int gameCode){

        cartService.deleteCart(gameCode);

        return "redirect:/order/cart";
    }

//    
}