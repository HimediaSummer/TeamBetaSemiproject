package beta.function.order.controller;

import beta.function.order.dto.PaymentDTO;
import beta.function.order.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*주문 리스트 담기*/
    @PostMapping("/order/insert/{userCode}")
    public String paymentResult(@PathVariable("userCode") int userCode,
                                HttpSession session,
                                RedirectAttributes rAttr){

//      임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//
//        Integer userCode = (Integer) session.getAttribute("userCode");

        /*주문 리스트 담기*/
        try{
            paymentService.addResultList(userCode);
            rAttr.addFlashAttribute("successMessage", "결제되었습니다.");
        }catch (IllegalStateException e){
            rAttr.addFlashAttribute("errorMessage", "결제 취소되었습니다.");
        }

        return "redirect:/order/result";
    }

    /*회원 주문 리스트*/
    @PostMapping("/order/result/{userCode}")
    public String paymentResult(Model model,
                                @PathVariable("userCode") int userCode,
                                HttpSession session){

//      임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);

//        Integer userCode = (Integer) session.getAttribute("userCode");

        /*주문 리스트 확인하기*/
        List<PaymentDTO> resultList = paymentService.paymentResult(userCode);
        model.addAttribute("resultList", resultList);

        System.out.println(resultList);

        return "order/result";
    }

}
