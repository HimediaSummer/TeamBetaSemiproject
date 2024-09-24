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

    /* Note:
     *  In Java, the Logger class is used for logging messages for a specific system or application component.
     *  Logging allows developers to track events, errors, and general information about the application during runtime.
     *  Java provides several logging frameworks, including the built-in java.util.logging and more advanced third-party
     *  frameworks like Log4j, SLF4J, and Logback.
     * */

    /* Note
     *  Logger.getLogger():
     *      You create a logger by calling Logger.getLogger() with a name, typically the class name.
     *  Log Levels:
     *      Java logging API defines several logging levels, which allow you to specify the importance or severity of the message.
     *      - Level.SEVERE: Highest value, indicates a serious failure
     *      - Level.WARNING: Indicates a potential problem.
     *      - Level.INFO: Provides informational messages.
     *      - Level.CONFIG: Used to log configuration messages.
     *      - Level.FINE, Level.FINER, Level.FINEST: Used for finer-grained messages, generally useful for debugging.
     *  Logging Messages:
     *      You can log messages by calling log(Level, String) or using shortcut methods like inf(), warning(), or severe()
     * */
    private static final Logger logger = LogManager.getLogger(GameController.class);

    private final GameService gameService;
    private final MessageSource messageSource;

    /* Note:
     *  The @Autowired annotation in Spring is used to automatically inject dependencies into a class.
     *  It allows Spring to manage the creation and injection of objects, reducing the need for manual
     *      wiring of components and making the application more modular and easier to maintain.
     *  In this case:
     *      - The GameController class depends on GameService and MessageSource to perform some operations.
     *      - Instead of creating and instances of GameService and MessageSource in gameService, Spring injects them automatically using @Autowired
     *      - The dependency is passed in via the constructor, making it explicit and easier to test.
     *  */
    @Autowired
    public GameController(GameService gameService, MessageSource messageSource) {
        this.gameService = gameService;
        this.messageSource = messageSource;
    }

    /* Model:
     *  In Spring, the Model is used to pass data from the controller to the view(typically a Thymeleaf, JSP, or other template engine.)
     *  It serves as a container for data that the view needs to render the page.
     *  It's part of the Model-View-Controller(MVC) design pattern, which Spring MVC implements.
     *  When you work with Spring MVC, the controller is responsible for handling HTTP requests, processing data, and determining which view to render.
     *  The Model helps the controller pass data to the view.
     * */

    /* Model Interface:
     *  The Model interface in Spring provides methods to add and retrieve attributes, which will then be made available to the view for rendering.
     *  It is typically used in controller methods that return a view name.
     * */

    /* ModelMap and ModelAndView:
     *  Spring provides other variations like ModelMap(a more flexible map implementation) and ModelAndView(which combines both the model and the view in a single object).
     *  ModelMap works similarly to Model, but it also provides additional functionality for map-based access.
     *  ModelAndView holds both the model data and the view name in one object.
     * */

    /* Purpose of Model:
     *  To pass data from the controller to the view.
     * Common Usage:
     *  Model is used in controller methods to add attributes, which are then accessible in the view.
     * Variations:
     *  ModelMap: A map-like implementation for passing attributes.
     *  ModelAndView: Combines both the model data and view name in one object.
     * Work with Views:
     *  The data added to the Model is accessible in view templates (such as Thymeleaf, JSP, etc.).
     * */
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

    /* RedirectAttributes:
     *  RedirectAttributes is used to pass attributes from one request to another, specifically in a redirect scenario.
     *  When a controller method returns a redirect (using redirect:/someURL), the RedirectAttributes allows you to add attributes
     *      that will be available in the target URL or in the target view after the redirection.
     *  RedirectAttributes is useful when you need to preserve data (like a form submission result or messages) across redirects,
     *      following the Post/Redirect/Get(PRG) pattern, which helps prevent duplicate form submission.
     *  */
    /* Flash Attributes:
     *  These are stored temporarily for the redirect and then cleared after the redirect is completed.
     *  They are sued when you don't want the parameters to apperar in the URL.
     * */
    /* Query Parameters:
     *  Attributes that you pass via the URL .
     *  */
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
