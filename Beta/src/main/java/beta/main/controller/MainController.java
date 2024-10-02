package beta.main.controller;

import beta.function.game.dto.GameDTO;
import beta.function.game.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final GameService gameService;

    public MainController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = {"/", "/main"})
    public ModelAndView findGameList(ModelAndView mv) {

        List<GameDTO> gameList = gameService.findAllGame();
        mv.addObject("gameList", gameList);
        mv.setViewName("main/main");

        return mv;

//        public String findGameList(Model model) {
//            List<GameDTO> gameList = gameService.findAllGame();
//
//            model.addAttribute("gameList", gameList);
//
//            return "game/listAll";
//        }

    }

    /* 설명. 유저 권한 설정 체크 */
    @GetMapping("/user/page")
    public ModelAndView user(ModelAndView mv) {
        mv.setViewName("user/user");
        return mv;
    }
}

