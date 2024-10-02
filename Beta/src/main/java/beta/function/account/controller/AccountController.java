package beta.function.account.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.account.dto.AuthorityDTO;
import beta.function.account.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private final AccountService accountService;
    private final MessageSource messageSource;

    @Autowired
    private ResourceLoader resourceLoader;

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

        System.out.println("멤버 리스트 1 ");

        List<AccountDTO> memberList = accountService.findAllMember();
        System.out.println("멤버 리스트 2 " + memberList);

        model.addAttribute("memberList", memberList);

        return "admin/memberList";
    }

    // 개별 조회
    @GetMapping("/memberlistone")
    public void memeberListone() {}

    @PostMapping("/memberlistone")
    public String findmemberListOne(Model model, @RequestParam("userCode") int userCode){

        AccountDTO member = accountService.findMemberByCode(userCode);

        if (member == null) {
            model.addAttribute("errorMessage", "해당 회원코드 정보가 없습니다");
        } else {
            model.addAttribute("member", member);
        }
        System.out.println("member = " + member);

        return "admin/memberDetail";
    }


    // Create 또는 Insert 부분
    @GetMapping("/memberRegist")
    public void registPage() {}

    @GetMapping(value = "authority", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AuthorityDTO> findAuthority() {
        return accountService.findAllAuthority();
    }

    // 회원 등록
    @PostMapping("/memberRegist")
    public String registMember(@RequestParam("username") String username,
                               @RequestParam("fullname") String fullname,
                               @RequestParam("nickName") String nickName,
                               @RequestParam("password") String password,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("suspension") char suspension,
                               @RequestParam("deletion") char deletion,
                               @RequestParam("profileimg") MultipartFile profileimg,
                               @RequestParam("authorityCode") int authorityCode,
                               RedirectAttributes rAttr,
                               Locale locale) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/account");

        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재X
            String root = "src/main/resources/static/uploadedFiles/img/account";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/account").getFile().getAbsolutePath();
        }

        // Handle profileimg upload
        String memberImgOriginalName = profileimg.getOriginalFilename();
        String memberImgExtension = memberImgOriginalName.substring(memberImgOriginalName.lastIndexOf("."));
        String savedImgName = UUID.randomUUID().toString().replace("-", "") + memberImgExtension;
        profileimg.transferTo(new File(filePath + "/" + savedImgName));

        // Save paths in AccountDTO
        AccountDTO newMember = new AccountDTO();

        newMember.setUsername(username);
        newMember.setFullname(fullname);
        newMember.setNickName(nickName);
        newMember.setPassword(password);
        newMember.setBirthday(birthday);
        newMember.setEmail(email);
        newMember.setPhone(phone);
        newMember.setSuspension(suspension);
        newMember.setDeletion(deletion);
        newMember.setProfileimg(savedImgName);
        newMember.setAuthorityCode(authorityCode);

        accountService.registNewMember(newMember);

        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMember", null, locale));

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

    @PostMapping("/memberEdit/{userCode}")
    public String showEditMemberForm(@PathVariable("userCode") int userCode, Model model) {


        AccountDTO member = accountService.findMemberByCode(userCode);
        model.addAttribute("member", member);

        System.out.println("회원 수정3" + member);

//        return "admin/memberEdit";
        return "admin/memberList";
    }

    @PostMapping("/update")
    public String updateMember(@RequestParam("userCode") int userCode,
                               @RequestParam("username") String username,
                               @RequestParam("fullname") String fullname,
                               @RequestParam("nickName") String nickName,
                               @RequestParam("password") String password,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("suspension") char suspension,
                               @RequestParam("deletion") char deletion,
                               @RequestParam("profileimg") MultipartFile profileimg,
                               @RequestParam("authorityCode") int authorityCode,
                               RedirectAttributes rAttr,
                               Locale locale) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/account");

        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재X
            String root = "src/main/resources/static/uploadedFiles/img/account";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/account").getFile().getAbsolutePath();
        }

        // Handle profileimg upload
        String memberImgOriginalName = profileimg.getOriginalFilename();
        String memberImgExtension = memberImgOriginalName.substring(memberImgOriginalName.lastIndexOf("."));
        String savedImgName = UUID.randomUUID().toString().replace("-", "") + memberImgExtension;
        profileimg.transferTo(new File(filePath + "/" + savedImgName));

        // Save paths in AccountDTO
        AccountDTO member = new AccountDTO();

        member.setUserCode(userCode);
        member.setUsername(username);
        member.setFullname(fullname);
        member.setNickName(nickName);
        member.setPassword(password);
        member.setBirthday(birthday);
        member.setEmail(email);
        member.setPhone(phone);
        member.setSuspension(suspension);
        member.setDeletion(deletion);
        member.setProfileimg(savedImgName);
        member.setAuthorityCode(authorityCode);

        System.out.println("member = " + member);

        accountService.updateMember(member);

        logger.info("Locale : {}", locale);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다.");

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


}