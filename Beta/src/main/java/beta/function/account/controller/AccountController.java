package beta.function.account.controller;

import beta.function.account.dto.AccountDTO;
import beta.function.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    회원 코드를 가져오기 위한 메서드
    @GetMapping("user/listAll")
    public String userAllList(Model model){

        List<AccountDTO> userList = accountService.userAllList();

        model.addAttribute("userList", userList);

        return "user/listAll";
    }
}
