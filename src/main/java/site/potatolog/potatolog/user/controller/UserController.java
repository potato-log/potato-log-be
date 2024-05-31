package site.potatolog.potatolog.user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.potatolog.potatolog.user.domain.User;
import site.potatolog.potatolog.user.dto.UserRequest;
import site.potatolog.potatolog.user.dto.UserResponse;
import site.potatolog.potatolog.user.service.UserService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/login/oauth2/code/github")
    public ResponseEntity<List<UserResponse>> getUserInfo(@RequestParam(value = "code") String code) {
        try {
            String accessToken = userService.getGithubAccessToken(code);
            JsonNode userResourceNode = userService.getUserResource(accessToken);
            ObjectMapper objectMapper = new ObjectMapper();
            UserRequest userRequest = objectMapper.convertValue(userResourceNode, UserRequest.class);
            User user = userRequest.toEntity();

            User savedUser = userService.saveUser(user);

            List<UserResponse> userResponses = Collections.singletonList(new UserResponse(savedUser));

            return ResponseEntity.ok(userResponses);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
