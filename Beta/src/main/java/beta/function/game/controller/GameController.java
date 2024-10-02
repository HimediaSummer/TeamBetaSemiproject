package beta.function.game.controller;

import beta.function.game.dto.GameDTO;
import beta.function.game.service.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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

    @Autowired
    public GameController(GameService gameService, MessageSource messageSource) {
        this.gameService = gameService;
        this.messageSource = messageSource;
    }

    /* Model:
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

        System.out.println("list all " + gameList);

        model.addAttribute("gameList", gameList);

        return "game/listAll";
    }

    @GetMapping("/listOne")
    public void listOnePage() {}

    @PostMapping("/listOne")
    public String findGameDetail(Model model,
                                 @RequestParam("gameCode") int gameCode) {

        GameDTO game = gameService.findGameByCode(gameCode);
        model.addAttribute("game", game);

        return "game/listDetail";
    }

    @GetMapping("/regist")
    public void registPage() {}

    /* The purpose of a ResourceLoader is to efficiently manage the loading, handling, and delivery of external
     *  resources like scripts, styles, images, or other assets within an application or system.
     * It ensures that resources are loaded only when needed, reducing the load time and improving the performance of the application.
     * */
    /* 설명. ResourceLoader 의존성 주입:
     *  /build 경로 하위에 작성한 파일 업로드 경로를 가져오기 위해 ResourceLoader 의존성을 주입함.
     *  여기서는 간단하게 필드 주입 방식을 사용함.
     *  */
    @Autowired
    private ResourceLoader resourceLoader;

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
    /* static directory:
     *  The static directory in a Spring Boot application is special because it is one of the default locations
     *      where Spring Boot automatically serves static resources such as HTML, CSS, JavaScript, images, etc.
     * Key Points:
     *  1. Default Static Resource Directory:
     *      Spring Boot serves static content from the /static, /public, /resources, or /META-INF/resources
     *          directories in the classpath by default. Among these, the /static directory is commonly used.
     *  2. Automatic Mapping:
     *      Any files placed inside the static directory are automatically served as static resources without
     *          any additional configuration. For example, a file placed at src/main/resources/static/images/log.png
     *          can be accessed via http://localhost:8080/images/logo.png
     *  3. Location in the Project Structure:
     *      Typically, the static directory is located in src/main/resources/static. This directory is included in
     *          the classpath by default, so any files placed here are available to the Spring Boot application at runtime.
     *  4. Serving Static Content:
     *      Spring Boot uses Spring MVC behind the scene to serve static content. When a request is made to a static
     *          resource (like a CSS file or an image), the request is mapped to the appropriate resource from the static directory.
     *  5. No Controller Needed:
     *      You don't need to write a controller to handle requests for static resources. Spring Boot serves files
     *          from the static directory automatically, which simplifies development.
     *  6. Customization:
     *      If necessary, you can change the default static resource locations by customizing the
     *          spring.resources.static-locations property in your application.properties or application.yml file.
     *  7. Caching:
     *      Spring Boot includes automatic caching for static resources. You can configure cache settings for
     *          static resources using properties such as spring.resources.-cache.cachecontrol.max-age.
     *  Example:
     *      If you hava an HTML file in src/main/resources/static/index.html, it will be served at
     *          http://localhost:8080/index.html without any additional configuration.
     *  Summary:
     *      In conclusion, the static directory is "special" in Spring Boot because it is one fo the designated
     *          locations for serving static resources automatically, making it a key part of the framework's handling of assets.
     *  */
    @PostMapping("/regist")
    public String registGame(@RequestParam("gameName") String gameName,
                             @RequestParam("gameStorage") String gameStorage,
                             @RequestParam("gamePrice") int gamePrice,
                             @RequestParam("uploadDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
                             @RequestParam("gameOrigin") String gameOrigin,
                             @RequestParam("gameRequirement") String gameRequirement,
                             @RequestParam("gamePicture") MultipartFile gamePicture, // Handle files separately
                             @RequestParam("gameThumbnail") MultipartFile gameThumbnail,
                             RedirectAttributes rAttr,
                             Locale locale) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/game");

        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img/game";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();

        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/game").getFile().getAbsolutePath();
        }

        // Handle gamePicture upload
        String gamePictureOriginalName = gamePicture.getOriginalFilename();
        String gamePictureExtension = gamePictureOriginalName.substring(gamePictureOriginalName.lastIndexOf("."));
        String savedGamePictureName = UUID.randomUUID().toString().replace("-", "") + gamePictureExtension;
        gamePicture.transferTo(new File(filePath + "/" + savedGamePictureName));

        // Handle gameThumbnail upload
        String gameThumbnailOriginalName = gameThumbnail.getOriginalFilename();
        String gameThumbnailExtension = gameThumbnailOriginalName.substring(gameThumbnailOriginalName.lastIndexOf("."));
        String savedGameThumbnailName = UUID.randomUUID().toString().replace("-", "") + gameThumbnailExtension;
        gameThumbnail.transferTo(new File(filePath + "/" + savedGameThumbnailName));

        // Save paths in GameDTO

        GameDTO newGame = new GameDTO();

        newGame.setGameName(gameName);
        newGame.setGameStorage(gameStorage);
        newGame.setGamePrice(gamePrice);
        newGame.setUploadDate(uploadDate);
        newGame.setGameOrigin(gameOrigin);
        newGame.setGameRequirement(gameRequirement);
        newGame.setGamePicture(savedGamePictureName);
        newGame.setGameThumbnail(savedGameThumbnailName);

        gameService.registNewGame(newGame);

        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registGame", null, locale));
        return "redirect:/game/listAll";

    }

    @PostMapping("/edit/{gameCode}")
    public String showEditGameForm(@PathVariable("gameCode") int gameCode,
                                   Model model) {

        // Add the list of all games and the game to be edited to the model
        List<GameDTO> gameList = gameService.findAllGame();
        model.addAttribute("gameList", gameList);  // Keep all games displayed

        GameDTO game = gameService.findGameByCode(gameCode);
        model.addAttribute("game", game);

//        return "game/gameModal";
        return "game/listAll";
    }

    @PostMapping("/update")
    public String updateGame(@RequestParam("gameCode") int gameCode,
                             @RequestParam("gameName") String gameName,
                             @RequestParam("gameStorage") String gameStorage,
                             @RequestParam("gamePrice") int gamePrice,
                             @RequestParam("uploadDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
                             @RequestParam("gameOrigin") String gameOrigin,
                             @RequestParam("gameRequirement") String gameRequirement,
                             @RequestParam("gamePicture") MultipartFile gamePicture, // Handle files separately
                             @RequestParam("gameThumbnail") MultipartFile gameThumbnail,
                             RedirectAttributes rAttr,
                             Locale locale) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/game");

        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img/game";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();

        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/game").getFile().getAbsolutePath();
        }

        // Handle gamePicture upload
        String gamePictureOriginalName = gamePicture.getOriginalFilename();
        String gamePictureExtension = gamePictureOriginalName.substring(gamePictureOriginalName.lastIndexOf("."));
        String savedGamePictureName = UUID.randomUUID().toString().replace("-", "") + gamePictureExtension;
        gamePicture.transferTo(new File(filePath + "/" + savedGamePictureName));

        // Handle gameThumbnail upload
        String gameThumbnailOriginalName = gameThumbnail.getOriginalFilename();
        String gameThumbnailExtension = gameThumbnailOriginalName.substring(gameThumbnailOriginalName.lastIndexOf("."));
        String savedGameThumbnailName = UUID.randomUUID().toString().replace("-", "") + gameThumbnailExtension;
        gameThumbnail.transferTo(new File(filePath + "/" + savedGameThumbnailName));

        GameDTO game = new GameDTO();

        game.setGameCode(gameCode);
        game.setGameName(gameName);
        game.setGameStorage(gameStorage);
        game.setGamePrice(gamePrice);
        game.setUploadDate(uploadDate);
        game.setGameOrigin(gameOrigin);
        game.setGameRequirement(gameRequirement);
        game.setGamePicture(savedGamePictureName);
        game.setGameThumbnail(savedGameThumbnailName);

        System.out.println("game = " + game);

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
