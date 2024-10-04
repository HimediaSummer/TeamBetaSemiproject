package beta.function.order.controller;

import beta.function.account.dto.AccountDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
                          @AuthenticationPrincipal AccountDTO user,
                          @RequestParam List<String> gameCode){

        Integer userCode = user.getUserCode();
        // 임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//        session.getAttribute("userCode");
//        Integer userCode = (Integer) session.getAttribute("userCode");

        System.out.println("gameCodeList 실행 1");
        System.out.println("gameCodeList " + gameCode);
//        gameCode.add(userCode)
//        cartService.gameCodeList(userCode, gameCodeList);
//        List<String> userCodeList = new ArrayList<>();
//        if(userCode != null){
//            userCodeList.add(userCode.toString());
//        }
//        System.out.println("gameCodeListcheck" + gameCodeListcheck);

        System.out.println("[PaymentController] addItem : 나오나?");


        try{
            /*game_payment 테이블에 추가*/
            System.out.println("addpayment1 ");
            paymentService.addPayment(userCode);
            System.out.println("addpayment2 ");
//            System.out.println("[PaymentController] userCode : " + userCode);

            /*game_order 테이블에 추가*/
            System.out.println("addOrder 1 ");
            orderService.addOrder(userCode);
            System.out.println("addOrder 2 ");
////          System.out.println("[PaymentController] userCode : " + userCode);

            model.addAttribute("successMessage", "결제가 완료되었습니다.");

            /*결제 확인*/
            System.out.println("orderResult 1 ");
            List<PaymentDTO> resultList = paymentService.orderResult(userCode);
            System.out.println("orderResult 2 ");
//            System.out.println("[PaymentController] resultList : " + resultList);

            model.addAttribute("resultList", resultList);

            System.out.println("gameCodeList 실행 2");
            cartService.gameCode(userCode, gameCode);
            System.out.println("gameCodeList 실행 3");
            cartService.deleteY();
            System.out.println("gameCodeList 실행 4");

//            System.out.println(resultList);

//            return "redirect:/order/resultdetail";
            return "redirect:/cart/history";

        }catch (IllegalStateException e){

            model.addAttribute("errorMessage", "결제가 취소되었습니다.");

//            System.out.println("[PaymentController] : ???");
        }

        return "redirect:/order/userCart";
    }

    // Create 또는 Insert 부분
    @RequestMapping("/resultdetail")
    public void resultdetail(HttpSession session, Model model, @AuthenticationPrincipal AccountDTO user) {

        Integer userCode = user.getUserCode();
        // 임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//        session.getAttribute("userCode");

//        Integer userCode = (Integer) session.getAttribute("userCode");

        /*결제 확인*/
        List<PaymentDTO> resultList = paymentService.orderResult(userCode);
//            System.out.println("[PaymentController] resultList : " + resultList);

        model.addAttribute("resultList", resultList);
    }
}

