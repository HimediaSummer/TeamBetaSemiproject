package beta.function.order.controller;

import beta.function.order.dto.OrderDTO;
import beta.function.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping("/orderListOne")
//    public String findOrderList(Model model) {
//
//        OrderDTO order = orderService.UserByOrderList();
//        model.addAttribute("order", order);
//
//        return "admin/orderListAll";
//    }
}
