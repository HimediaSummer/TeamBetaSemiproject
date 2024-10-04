package beta.main.controller;

import beta.function.game.dto.GameDTO;
import beta.function.game.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    }

    @PostMapping("/main/game/{gameCode}")
    public ModelAndView callGame(@PathVariable("gameCode") int gameCode,
                                 ModelAndView mv) {

        // Add the list of all games and the game to be displayed
        List<GameDTO> gameList = gameService.findAllGame();
        mv.addObject("gameList", gameList);  // Keep all games displayed

        GameDTO game = gameService.findGameByCode(gameCode);
        mv.addObject("game", game);

        mv.setViewName("main/product-detail");

        return mv;
    }

    /* 설명. 유저 권한 설정 체크 */
    @GetMapping("/user")
    public ModelAndView user(ModelAndView mv) {
        mv.setViewName("user/user");
        return mv;
    }

    /* function/account추가*/

    /* 설명. 관리자 권한 설정 체크 */
    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("admin/admin");
        return mv;
    }

    @GetMapping("/game/detail")
    public ModelAndView detail(ModelAndView mv) {
        mv.setViewName("game/detail");
        return mv;
    }

    /* function/game 브랜치 추가 */

    /**/
    /**/
    /**/
    /* function/security 브랜치 추가 */

}

