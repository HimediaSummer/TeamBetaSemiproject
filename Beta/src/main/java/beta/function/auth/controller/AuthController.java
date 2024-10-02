package beta.function.auth.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        System.out.println("버튼호출 어스컨트롤러 2 " + whereid);

        model.addAttribute("whereid", whereid.getUsername());
        System.out.println("버튼호출 어스컨트롤러 3 " + whereid.getUsername());
        System.out.println("버튼호출 어스컨트롤러 3 " + whereid);

        int result = Integer.parseInt(String.valueOf(whereid));

        String message = null;

//        if (result == null) {
//            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
//            System.out.println(message);
//
//            mv.setViewName("user/signup");
//        } else if (result == 0) {
//            message = "회원가입에 실패했습니다. 다시 시도해주세요";
//            System.out.println(message);

        return "auth/lostid";
//        return "auth/lostpwd";
    }

    @GetMapping("/lostpwd")
    public void findlostpwd(){}

    @PostMapping("/lostpwd")
    public String findlostpwd(String email, Model model) {

        System.out.println("lostpwd 어스컨트롤러 1 ");

        AccountDTO lostpwd = authService.lostpwd(email);
        System.out.println("lostpwd 어스컨트롤러 2 " + lostpwd);

        model.addAttribute("lostpwd", lostpwd.getUsername());
        System.out.println("lostpwd 어스컨트롤러 3 " + lostpwd.getUsername());

        return "auth/lostpwd";
//        return "redirect:/auth/changepwd?username=" + lostpwd.getUsername();
//        return "redirect:/change-password?email=" + email;
    }

    @GetMapping("/changepwd")
    public void changepwd(){}

    @PostMapping("/changepwd")
    public String changepwd(@ModelAttribute AccountDTO accountInfo, RedirectAttributes rAttr) {

        System.out.println("changepwd 어스 컨트롤러 1 " );

        Integer changepwd = authService.changepwd(accountInfo);
        System.out.println("changepwd 어스 컨트롤러 2  " );

        rAttr.addFlashAttribute("successMessage", "성공적으로 수정되었습니다.");
        System.out.println("changepwd 어스 컨트롤러 3  " );

//        return "auth/lostpwd";
        return "redirect:/auth/login";
    }
}
