package beta.function.auth.controller;

import beta.function.account.dao.AccountMapper;
import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.SignupDTO;
import beta.function.account.service.AccountService;
import beta.function.auth.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

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
    public String findlostpwd(HttpServletRequest req, HttpServletResponse resp, String email, Model model) throws IOException {

        System.out.println("wherepwd 어스 컨트롤러 1 = " );
        AccountDTO wherepwd = authService.lostid(email);

        System.out.println("wherepwd 어스 컨트롤러 2 = " + wherepwd);
        model.addAttribute("wheerepwd",wherepwd);
        System.out.println("wherepwd 어스 컨트롤러 3 = " + wherepwd);

        System.out.println("/session 서블릿 호출됨...");

        // 다시 생각해보니 세션에서 정보를 가져올수가없음 로그인을 안하니까
        // spring security를 거치지않아서 로그인정보 < 를 요청못함
        // 그냥 버튼 하나로만들고 화면만 좀 꾸며주고 main에다 합치는걸로

//
//        if( wherepwd != null ){
//            authService.changepwd(wherepwd);
//        }

        resp.sendRedirect("changepwd");

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

        return "auth/changepwd";
//        return "redirect:/auth/login";
    }
}
