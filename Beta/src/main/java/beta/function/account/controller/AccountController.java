package beta.function.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import beta.function.account.dto.SignupDTO;
import beta.function.account.service.AccountService;

@Controller
@RequestMapping("/user")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv,
                               @ModelAttribute SignupDTO newUserInfo) {

//        String validationError =  newUserInfo.validate();
//
//        if(validationError != null) {
//            mv.setViewName("user/signup");
//            mv.addObject("errorMessage",validationError);
//            return mv;
//        }

        if (newUserInfo.getUsername() == null || newUserInfo.getUsername().trim().isEmpty()) {
            mv.setViewName("user/signup");
            mv.addObject("errorMessage", "Username is required");
            return mv;
        }
        if (newUserInfo.getFullName() == null || newUserInfo.getFullName().trim().isEmpty()) {
            mv.setViewName("user/signup");
            mv.addObject("errorMessage", "Full name is required");
            return mv;
        }
        if (newUserInfo.getPassword() == null || newUserInfo.getPassword().length() < 8) {
            mv.setViewName("user/signup");
            mv.addObject("errorMessage", "Password must be at least 8 characters");
            return mv;
        }
        if (newUserInfo.getEmail() == null || !newUserInfo.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mv.setViewName("user/signup");
            mv.addObject("errorMessage", "Please enter a valid email address");
            return mv;
        }
        if (newUserInfo.getPhone() == null || newUserInfo.getPhone().trim().isEmpty()) {
            mv.setViewName("user/signup");
            mv.addObject("errorMessage", "Phone number is required");
            return mv;
        }

        Integer result = accountService.regist(newUserInfo);

        String message = null;

        if(result == null) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println(message);

            mv.setViewName("user/signup");
        }else if(result == 0) {
            message = "회원가입에 실패했습니다. 다시 시도해주세요";
            System.out.println(message);

            mv.setViewName("user/signup");
        } else if (result >= 1) {
            message = "회원가입에 성공적으로 완료되었습니다.";
            System.out.println(message);

            mv.setViewName("auth/login");
        }else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
            System.out.println(message);

            mv.setViewName("user/signup");
        }

        return mv;
    }
}
