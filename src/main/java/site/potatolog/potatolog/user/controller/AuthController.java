package site.potatolog.potatolog.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.potatolog.potatolog.user.service.AuthService;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login/oauth2/code/github")
    public void getUserInfo(@RequestParam(value = "code") String code) {
        System.out.println("코드값 : " + code);
        this.authService.gitLogin(code);
    }
}
