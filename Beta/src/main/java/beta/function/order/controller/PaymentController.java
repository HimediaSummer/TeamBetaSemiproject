package beta.function.order.controller;

import beta.function.order.dto.PaymentDTO;
import beta.function.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final MessageSource messageSource;

    @Autowired
    public OrderController(OrderService orderService, @Qualifier("messageSource") MessageSource messageSource) {
        this.orderService = orderService;
        this.messageSource = messageSource;
    }

    @PostMapping("/result")
    public String addItem(HttpSession session,
                          Model model){

        System.out.println("[OrderController] addItem : 나오나?");

        // 임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        Integer userCode = (Integer) session.getAttribute("userCode");

        try{

            /*game_payment 테이블에 추가*/
            orderService.addPayment(userCode);
            System.out.println("[OrderController] userCode : " + userCode);

            model.addAttribute("successMessage", "결제가 완료되었습니다.");

            /*결제 확인*/
            List<PaymentDTO> resultList = orderService.orderResult(userCode);
            System.out.println("[OrderController] resultList : " + resultList);

            model.addAttribute("resultList", resultList);

            System.out.println(resultList);

            return "order/result";

        }catch (IllegalStateException e){

            model.addAttribute("errorMessage", "결제가 취소되었습니다.");

            System.out.println("[OrderController] : ???");
        }

        return "redirect:/order/userCart";
    }
}

