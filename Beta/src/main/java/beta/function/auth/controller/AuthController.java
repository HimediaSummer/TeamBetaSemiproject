package beta.function.auth.controller;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.SignupDTO;
import beta.function.account.service.AccountService;
import beta.function.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/lostpwd")
    public void findlostpwd(){}

    @PostMapping("/lostpwd")
    public String findlostpwd(String email, Model model) {

        System.out.println("wherepwd 어스 컨트롤러 1 = " );
        AccountDTO wherepwd = authService.lostid(email);

        System.out.println("wherepwd 어스 컨트롤러 2 = " + wherepwd);
        model.addAttribute("wheerepwd",wherepwd);
        System.out.println("wherepwd 어스 컨트롤러 3 = " + wherepwd);
//
//        if( wherepwd != null ){
//            authService.changepwd(wherepwd);
//        }

        return "auth/lostpwd";
    }

    @GetMapping("/changepwd")
    public void changepwd(){}

    @PostMapping("/changepwd")
    public String changepwd(@ModelAttribute AccountDTO accountInfo, RedirectAttributes rAttr) {

        System.out.println("changepwd 어스 컨트롤러 1 " );

        authService.changepwd(accountInfo);
        System.out.println("changepwd 어스 컨트롤러 2  " );

        rAttr.addFlashAttribute("successMessage", "성공적으로 수정되었습니다.");
        System.out.println("changepwd 어스 컨트롤러 3  " );

        System.out.println("테스트 로그");
        
        return "auth/changepwd";
//        return "redirect:/auth/login";
    }
}
