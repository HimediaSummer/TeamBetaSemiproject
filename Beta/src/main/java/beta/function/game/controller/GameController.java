package beta.function.game.controller;

import beta.function.game.dto.GameDTO;
import beta.function.game.service.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/game")
public class GameController {

    private static final Logger logger = LogManager.getLogger(GameController.class);

    private final GameService gameService;
    private final MessageSource messageSource;

    @Autowired
    public GameController(GameService gameService, MessageSource messageSource) {
        this.gameService = gameService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listAll")
    public String findGameList(Model model) {

        List<GameDTO> gameList = gameService.findAllGame();

        model.addAttribute("gameList", gameList);

        return "game/listAll";
    }

    @RequestMapping("/listOne")
    public void listOnePage() {}

    @PostMapping("/listOne")
    public String findGameDetail(Model model,
                                 @RequestParam("gameCode") int gameCode) {

        GameDTO game = gameService.findGameByCode(gameCode);

        model.addAttribute("gameList", game);

        return "game/listAll";
    }

    @GetMapping("regist")
    public void registPage() {}

    @PostMapping("regist")
    public String registGame(@RequestParam("uploadDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate uploadDate,
                             GameDTO newGame,
                             RedirectAttributes rAttr,
                             Locale locale) {

        newGame.setUploadDate(uploadDate);
        gameService.registNewGame(newGame);

        logger.info("Locale : {}", locale);
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registGame", null, locale));

        return "redirect:/game/listAll";
    }

    @PostMapping("/edit/{gameCode}")
    public String showEditMenuForm(@PathVariable("gameCode") int gameCode,
                                    Model model) {

        GameDTO game = gameService.findGameByCode(gameCode);

        model.addAttribute("game", game);

        return "game/edit";
    }

    @PostMapping("/update")
    public String updateGame(@RequestParam("uploadDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate uploadDate,
                             GameDTO game,
                             RedirectAttributes rAttr,
                             Locale locale) {

        game.setUploadDate(uploadDate);
        gameService.updateGame(game);

        logger.info("Locale : {}", locale);
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("updateGame", null, locale));

        return "redirect:/game/listAll";
    }

    @PostMapping("/delete/{gameCode}")
    public String deleteGame(@PathVariable("gameCode") int gameCode,
                             RedirectAttributes rAttr,
                             Locale locale) {

        gameService.deleteGame(gameCode);

        logger.info("Locale : {}", locale);
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteGame", null, locale));

        return "redirect:/game/listAll";
    }
}
