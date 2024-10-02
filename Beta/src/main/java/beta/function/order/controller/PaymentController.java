package beta.function.order.controller;

import beta.function.order.dto.CartDTO;
import beta.function.order.dto.OrderDTO;
import beta.function.order.dto.PaymentDTO;
import beta.function.order.service.CartService;
import beta.function.order.service.OrderService;
import beta.function.order.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class PaymentController {

    private final PaymentService paymentService;
    private final MessageSource messageSource;
    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public PaymentController(PaymentService paymentService, @Qualifier("messageSource") MessageSource messageSource, OrderService orderService, CartService cartService) {
        this.paymentService = paymentService;
        this.messageSource = messageSource;
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/result")
//    @ResponseBody
    public String addItem(HttpSession session,
                          Model model,
//                          @RequestParam String userCode,
                          @RequestParam List<String> gameCode){

        System.out.println("gameCodeList 실행 1");
        System.out.println("gameCodeList " + gameCode);
//        List<String> gameCodeListcheck = cartService.gameCodeList( gameCodeList);
        cartService.gameCode(gameCode);
        cartService.deleteY();
//        cartService.gameCodeList(userCode, gameCodeList);

//        System.out.println("gameCodeListcheck" + gameCodeListcheck);

        System.out.println("[PaymentController] addItem : 나오나?");

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        Integer userCode = (Integer) session.getAttribute("userCode");

        try{
            /*game_payment 테이블에 추가*/
            paymentService.addPayment(userCode);
//            System.out.println("[PaymentController] userCode : " + userCode);

            /*game_order 테이블에 추가*/
            orderService.addOrder(userCode);
////          System.out.println("[PaymentController] userCode : " + userCode);

            model.addAttribute("successMessage", "결제가 완료되었습니다.");

            /*결제 확인*/
            List<PaymentDTO> resultList = paymentService.orderResult(userCode);
//            System.out.println("[PaymentController] resultList : " + resultList);

            model.addAttribute("resultList", resultList);

//            System.out.println(resultList);

            return "redirect:/order/resultdetail";

        }catch (IllegalStateException e){

            model.addAttribute("errorMessage", "결제가 취소되었습니다.");

//            System.out.println("[PaymentController] : ???");
        }

        return "redirect:/order/userCart";
    }

    // Create 또는 Insert 부분
    @RequestMapping("/resultdetail")
    public void resultdetail(HttpSession session, Model model) {

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        Integer userCode = (Integer) session.getAttribute("userCode");

        /*결제 확인*/
        List<PaymentDTO> resultList = paymentService.orderResult(userCode);
//            System.out.println("[PaymentController] resultList : " + resultList);

        model.addAttribute("resultList", resultList);
    }
}

