package beta.function.order.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.order.dao.PaymentMapper;
import beta.function.order.dto.CartDTO;
import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import beta.function.order.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final PaymentMapper paymentMapper;

    @Autowired
    public CartController(CartService cartService, @Qualifier("messageSource") MessageSource messageSource, PaymentMapper paymentMapper) {
        this.cartService = cartService;
        this.messageSource = messageSource;
        this.paymentMapper = paymentMapper;
    }

    /*회원 최근 내역*/
    @GetMapping("/history")
    public String myPage(HttpSession session,
                         @AuthenticationPrincipal AccountDTO user,
                         Model model,
                         RedirectAttributes rAttr){

        // 임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//        session.getAttribute("userCode");


//        Integer userCode = (Integer) session.getAttribute("userCode");

//        cartService.findUser();
//        System.out.println("로그인 안했잖아앙아ㅏ userCode : " + userCode);
//        if (userCode == null) {
//            System.out.println("[CartController] : 로그인하고!");
//            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
//            return "redirect:/auth/login";  // 로그인 페이지로 리다이렉트
//        }
//
//
//        List<CartDTO> mypage = cartService.haveOrderList(userCode);
//
//        System.out.println("mypage 2 :" + mypage);
//
//        model.addAttribute("mypage", mypage);
//
//        return "cart/history";



        if(user != null){
            int userCode = user.getUserCode();


            List<CartDTO> mypage = cartService.haveOrderList(userCode);

            System.out.println("mypage 2 :" + mypage);

            model.addAttribute("mypage", mypage);

            return "cart/history";
        }else{
            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/auth/login";  // 로그인 페이지로 리다이렉트
        }


    }

    /*회원별 장바구니 리스트*/
     @GetMapping("/userCart")
     public String findCartList(Model model,
                                @AuthenticationPrincipal AccountDTO user,
                                RedirectAttributes rAttr,
                                HttpSession session){

         // 임의로 userCode를 2로 설정
//         session.setAttribute("userCode", 2);
//         session.getAttribute("userCode");

//         Integer userCode = (Integer) session.getAttribute("userCode");

        int userCode = user.getUserCode();
        System.out.println("userCode00 : " +userCode);


        List<CartDTO> users = cartService.findByUser(userCode);
        System.out.println("정보가 없나? : " + users);
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
                          HttpServletRequest req,
                          @AuthenticationPrincipal AccountDTO user,
                          RedirectAttributes rAttr) {

        if(user != null){
            Integer userCode = user.getUserCode();

            try{
//            List<CartDTO> cart = cartService.addItem(gameCode, userCode);
                cartService.addItem(gameCode, userCode);
                System.out.println("[CartController] gameCode : " + gameCode);
                System.out.println("[CartController] userCode : " + userCode);
//            httpSession.setAttribute("cart", cart);

                int isCheckOrderList = cartService.isCheckOrderList(userCode, gameCode);

                if(isCheckOrderList == 0) {

//                    cartService.OrderGameN(userCode, gameCode);


//                    for (CartDTO cart : cartList) {
//                        int paymentCode = payment.getPaymentCode();
//                        int cartCode = cart.getCartCode();
//                        OrderDTO order = new OrderDTO();
//                        order.setUserCode(userCode);
//                        order.setCartCode(cartCode);
//                        order.setGameCode(gameCode);
//                        order.setPaymentCode(paymentCode);
//
//                        System.out.println("order 내용물 " + order);
//                    }

                }

                rAttr.addFlashAttribute("successMessage", "장바구니에 담겼습니다.");
            }catch (IllegalStateException e){
                rAttr.addFlashAttribute("errorMessage", "이미 장바구니에 있는 게임입니다.");
            }

            return "redirect:/";
        }else{
            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/auth/login";  // 로그인 페이지로 리다이렉트
        }



//         임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//        session.getAttribute("userCode");

//        Integer userCode = (Integer) session.getAttribute("userCode");
//
//        System.out.println("로그인 안했잖아앙아ㅏ userCode : " + userCode);
//        if (userCode == null) {
//            System.out.println("[CartController] : 로그인하고!");
//            rAttr.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
//            return "redirect:/auth/login";  // 로그인 페이지로 리다이렉트
//        }
//
//        System.out.println("[CartController] : 담자!!");
//        try {
////            List<CartDTO> cart = cartService.addItem(gameCode, userCode);
//            cartService.addItem(gameCode, userCode);
//            System.out.println("[CartController] gameCode : " + gameCode);
//            System.out.println("[CartController] userCode : " + userCode);
////            httpSession.setAttribute("cart", cart);
//
//            rAttr.addFlashAttribute("successMessage", "장바구니에 담겼습니다.");
//        } catch (IllegalStateException e) {
//            rAttr.addFlashAttribute("errorMessage", "이미 장바구니에 있는 게임입니다.");
//        }

//        return "redirect:/";
    }

}