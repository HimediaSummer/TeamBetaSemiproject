package beta.function.account.controller;

import beta.function.account.dto.AccountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import beta.function.account.dto.SignupDTO;
import beta.function.account.service.AccountService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv,
                               @ModelAttribute SignupDTO newUserInfo) {

        System.out.println("어카운트 컨드롤러 호출됨.");
        Integer usernameExists = accountService.checkId(newUserInfo);


        String message = null;


//        if (usernameExists) {
//            mv.addAttribute("usernameError", "이미 사용 중인 아이디입니다.");
//        }
        if (usernameExists == 1) {

            System.out.println("어카운트 컨드롤러 : 아이디 중복체크");
            message = "이미 사용 중인 아이디입니다.";
            System.out.println("message = " + message);

            mv.setViewName("user/signup"); // 회원가입 페이지로 이동

        } else if(usernameExists == 0) {

            System.out.println("어카운트 컨드롤러 : 아이디 중복체크 결과 문제 없음. 회원가입 로직 실행");
            message = "사용가능한 아이디입니다.";
            System.out.println("message = " + message);

            // 중복되지 않으면 회원가입 진행
            accountService.regist(newUserInfo); // 회원가입 로직 실행


            mv.setViewName("auth/login"); // 회원가입 성공 후 로그인 페이지로 리다이렉트

        }
        return mv;
        }



//        Integer result = accountService.regist(newUserInfo);
//
//        String message = null;
//
//        if(result == null) {
//            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
//            System.out.println(message);
//
//            mv.setViewName("user/signup");
//        }else if(result == 0) {
//            message = "회원가입에 실패했습니다. 다시 시도해주세요";
//            System.out.println(message);
//
//            mv.setViewName("user/signup");
//        } else if (result >= 1) {
//            message = "회원가입에 성공적으로 완료되었습니다.";
//            System.out.println(message);
//
//            mv.setViewName("auth/login");
//        }else {
//            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
//            System.out.println(message);
//
//            mv.setViewName("user/signup");
//        }
//
//        return mv;
//    }



}
