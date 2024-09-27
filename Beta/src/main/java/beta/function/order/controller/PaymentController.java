package beta.function.order.controller;

import beta.function.order.dto.PaymentDTO;
import beta.function.order.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*회원 주문 리스트*/
    @PostMapping("/order/result")
    public String paymentResult(Model model,
                                HttpSession session){

//      임의로 userCode를 2로 설정
        session.setAttribute("userCode", 2);

        Integer userCode = (Integer) session.getAttribute("userCode");

        List<PaymentDTO> resultList = paymentService.paymentResult(userCode);

        model.addAttribute("resultList", resultList);

//        System.out.println("dkjlksjfklsjflkjflds");
//        System.out.println(resultList);

        return "order/result";
    }

}
