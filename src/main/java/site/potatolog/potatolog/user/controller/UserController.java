package site.potatolog.potatolog.user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.potatolog.potatolog.user.domain.User;
import site.potatolog.potatolog.user.dto.UserRequest;
import site.potatolog.potatolog.user.dto.UserResponse;
import site.potatolog.potatolog.user.service.UserService;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    @Value("${spring.github.clientId}")
    private String clientId;

    @Value("${spring.github.clientSecret}")
    private String clientSecret;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/oauth2/code/github")
    public ResponseEntity<?> getUserInfo(@RequestParam(value = "code") String code) {
        try {
            String accessToken = getGithubAccessToken(code);
            JsonNode userResourceNode = getUserResource(accessToken);
            ObjectMapper objectMapper = new ObjectMapper();
            UserRequest userRequest = objectMapper.convertValue(userResourceNode, UserRequest.class);
            User user = userRequest.toEntity();

            User savedUser = userService.saveUser(user);

            List<UserResponse> userResponses = Collections.singletonList(new UserResponse(savedUser));
            return ResponseEntity.ok(userResponses);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public String getGithubAccessToken(String code) {
        String accessTokenUrl = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("client_id", clientId);
        param.add("client_secret", clientSecret);
        param.add("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(accessTokenUrl, new HttpEntity<>(param, headers), JsonNode.class);

        JsonNode accessTokenNode = response.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    public JsonNode getUserResource(String accessToken) {
        String resourceUri = "https://api.github.com/user";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
