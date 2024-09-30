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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final MessageSource messageSource;
    private final HttpSession httpSession;

    @Autowired
    public CartController(CartService cartService, @Qualifier("messageSource") MessageSource messageSource) {
        this.cartService = cartService;
        this.messageSource = messageSource;
        this.httpSession = httpSession;
    }

    /*회원 최근 내역*/
    @GetMapping("/history")
    public String myPage(HttpSession session){

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        return "cart/history";
    }

    /*회원별 장바구니 리스트*/
     @GetMapping("/userCart")
     public String findCartList(Model model,
                                HttpSession session){

         // 임의로 userCode를 2로 설정
         session.setAttribute("userCode", 2);

         Integer userCode = (Integer) session.getAttribute("userCode");

         List<CartDTO> users = cartService.findByUser(userCode);

         model.addAttribute("users", users);

         /*게임 총 금액*/
         int total = cartService.gamePriceTotal(userCode);
         model.addAttribute("total", total);
         System.out.println("[CartController] total : " + total);

         return "cart/userCart";
     }

    /*장바구니 게임 삭제*/
    @PostMapping("/delete/{gameCode}")
    public String deleteCart(@PathVariable("gameCode") int gameCode){

        cartService.deleteCart(gameCode);

        return "redirect:/cart/userCart";
    }

    /*장바구니에 담기 (게임 상세 페이지에서)*/
    @PostMapping("/game/insert/{gameCode}")
    public String addItem(@PathVariable("gameCode") int gameCode,
                          HttpSession session,
                          RedirectAttributes rAttr){

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        Integer userCode = (Integer) session.getAttribute("userCode");

        if (userCode == null) {
            System.out.println("[CartController] : 로그인하고!");
            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }

        System.out.println("[CartController] : 담자!!");
        try{
//            List<CartDTO> cart = cartService.addItem(gameCode, userCode);
            cartService.addItem(gameCode, userCode);

//            httpSession.setAttribute("cart", cart);

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