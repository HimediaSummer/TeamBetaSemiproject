package beta.function.account.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
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
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
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

    // 회원 리스트
    @GetMapping("/memberList")
    public String findMemberList(Model model) {

        List<AccountDTO> memberList = accountService.findAllMember();

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

        accountService.registNewMember(newMember);

        logger.info("Locale : {}", locale);

        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMember", null, locale));

        return "redirect:/admin/memberList";
    }

    @GetMapping("/memberDetail/{userCode}")
    public String findMemberDetail(@PathVariable("userCode") int userCode, Model model) {

        AccountDTO member = accountService.findMemberByCode(userCode);

        model.addAttribute("member", member);

        return "admin/memberDetail";
    }

    @GetMapping("/memberEdit/{userCode}")
    public String showEditMemberForm(@PathVariable("userCode") int userCode, Model model) {

        AccountDTO member = accountService.findMemberByCode(userCode);

        model.addAttribute("member", member);

        return "admin/memberEdit";
    }

    @PostMapping("/update")
    public String updateMember(AccountDTO member, RedirectAttributes rAttr) {

        accountService.updateMember(member);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다.");

        return "redirect:/admin/memberDetail/" + member.getUserCode();
    }

    @PostMapping("/delete/{userCode}")
    public String deleteMember(@PathVariable("userCode") int userCode, RedirectAttributes rAttr){

        accountService.deleteMember(userCode);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

        return "redirect:/admin/memberList";
    }
}
