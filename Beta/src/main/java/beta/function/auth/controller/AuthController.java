package beta.function.auth.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/fail")
    public ModelAndView loginFail(ModelAndView mv,
                                  @RequestParam String message) {

        mv.addObject("message", message);
        mv.setViewName("/auth/fail");

        return mv;
    }

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/lostid")
    public void findlostid() {}

    @PostMapping("/lostid")
    public String findlostid(String email, Model model) {

        System.out.println("버튼호출 어스컨트롤러 1 ");

        AccountDTO whereid = authService.lostid(email);
        System.out.println("버튼호출 어스컨트롤러 2 ");

        model.addAttribute("whereid", whereid.getUsername());
        System.out.println("버튼호출 어스컨트롤러 3 " + whereid.getUsername());
        System.out.println("버튼호출 어스컨트롤러 3 " + whereid);

        return "auth/lostid";
    }
}
