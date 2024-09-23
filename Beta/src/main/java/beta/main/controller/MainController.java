package beta.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("main/main");
        return mv;
    }

    /* 설명. 관리자 권한 설정 체크 */
    @GetMapping("/admin/page")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("admin/admin");
        return mv;
    }

    /* 설명. 유저 권한 설정 체크 */
    @GetMapping("/user/page")
    public ModelAndView user(ModelAndView mv) {
        mv.setViewName("user/user");
        return mv;
    }

    /* 설명. 게임 상세페이지 */
    @GetMapping("/game/detail")
    public ModelAndView detail(ModelAndView mv){
        mv.setViewName("/game/detail");
        return mv;
    }

    /* 설명. 장바구니 */
    @GetMapping("/order/cart")
    public ModelAndView cart(ModelAndView mv){
        mv.setViewName("order/cart");
        return mv;
    }

    /**/
    /* function/security 브랜치 추가 */
}
