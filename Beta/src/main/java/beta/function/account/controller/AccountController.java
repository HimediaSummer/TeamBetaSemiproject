package beta.function.account.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
import beta.function.account.dto.SignupDTO;
import beta.function.account.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping({"/user", "/admin"})
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private final AccountService accountService;
    private final MessageSource messageSource;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREA);
        return slr;
    }

    @Autowired
    public AccountController(AccountService accountService, MessageSource messageSource) {

        this.accountService = accountService;
        this.messageSource = messageSource;
    }

    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv,
                               @ModelAttribute SignupDTO newUserInfo) {

        Integer result = accountService.regist(newUserInfo);

        String message = null;

        if (result == null) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println(message);

            mv.setViewName("user/signup");
        } else if (result == 0) {
            message = "회원가입에 실패했습니다. 다시 시도해주세요";
            System.out.println(message);

            mv.setViewName("user/signup");
        } else if (result >= 1) {
            message = "회원가입에 성공적으로 완료되었습니다.";
            System.out.println(message);

            mv.setViewName("auth/login");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
            System.out.println(message);

            mv.setViewName("user/signup");
        }

        return mv;
    }

    // 회원 리스트
    @GetMapping("/memberList")
    public String findMemberList(Model model) {

        System.out.println("멤버 리스트 1 ");

        List<AccountDTO> memberList = accountService.findAllMember();
        System.out.println("멤버 리스트 2 " + memberList);

        model.addAttribute("memberList", memberList);

        return "admin/memberList";
    }

    // 개별 조회
    @RequestMapping("/memberListone")
    @GetMapping("/memberListone")
    public void memeberListone() {}

    // Create 또는 Insert 부분
    @GetMapping("/memberRegist")
    public void registPage() {}

    @GetMapping(value = "authority", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AuthorityDTO> findAuthority() {
        return accountService.findAllAuthority();
    }


    @PostMapping("/memberRegist")
    public String registMember(AccountDTO newMember, RedirectAttributes rAttr, Locale locale) {

        System.out.println("회원 등록 1");

        accountService.registNewMember(newMember);

        System.out.println("회원 등록 2" + newMember);

        logger.info("Locale : {}", locale);

        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMember", null, locale));

        System.out.println("회원 등록 3" + newMember);

        return "redirect:/admin/memberList";
    }

    @GetMapping("/memberDetail/{userCode}")
    public String findMemberDetail(@PathVariable("userCode") int userCode, Model model) {
        System.out.println("상세 정보 1");

        AccountDTO member = accountService.findMemberByCode(userCode);

        System.out.println("상세 정보 2" + member);

        model.addAttribute("member", member);

        System.out.println("상세 정보 3" + member);

        return "admin/memberDetail";
    }

    @GetMapping("/memberEdit/{userCode}")
    public String showEditMemberForm(@PathVariable("userCode") int userCode, Model model) {

        System.out.println("회원 수정1");

        AccountDTO member = accountService.findMemberByCode(userCode);

        System.out.println("회원 수정2" + member);

        model.addAttribute("member", member);

        System.out.println("회원 수정3" + member);

        return "admin/memberEdit";
    }

    @PostMapping("/update")
    public String updateMember(AccountDTO member, RedirectAttributes rAttr) {

        System.out.println("회원 업데이트1");

        accountService.updateMember(member);

        System.out.println("회원 업데이트2" + member);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다.");

        System.out.println("회원 업데이트3" + member);

        return "redirect:/admin/memberDetail/" + member.getUserCode();
    }

    @PostMapping("/delete/{userCode}")
    public String deleteMember(@PathVariable("userCode") int userCode, RedirectAttributes rAttr){

        System.out.println("회원삭제1");

        accountService.deleteMember(userCode);

        System.out.println("회원삭제2" + userCode);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

        System.out.println("회원삭제3" + userCode);

        return "redirect:/admin/memberList";
    }

    // 회원 코드를 가져오기 위한 메서드
    @GetMapping("/listAll")
    public String userAllList(Model model){

        List<AccountDTO> userList = accountService.userAllList();

        model.addAttribute("userList", userList);

        return "user/listAll";
    }
}
