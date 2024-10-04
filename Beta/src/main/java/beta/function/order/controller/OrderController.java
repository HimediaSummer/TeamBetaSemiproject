package beta.function.order.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.game.dto.GameDTO;
import beta.function.order.dto.OrderDTO;
import beta.function.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderListAll")
    public String findOrderList(Model model) {

        List<OrderDTO> orderList = orderService.findOrderList();
        model.addAttribute("orderList", orderList);

        return "admin/orderListAll";
    }

    @RequestMapping("/orderListOne")
    public void listOnePage() {}

    @PostMapping("/orderListOne")
    public String findGameDetail(Model model, HttpSession session,
                                 @AuthenticationPrincipal AccountDTO user,
                                 @RequestParam("orderCode") int orderCode) {

//        Integer userCode = user.getUserCode();
        // 임의로 userCode를 2로 설정
//        session.setAttribute("userCode", 2);
//
        Integer userCode = (Integer) session.getAttribute("userCode");

        OrderDTO order = orderService.userByOrderList(orderCode);

        model.addAttribute("order", order);

        return "admin/orderListDetail";
    }
}
