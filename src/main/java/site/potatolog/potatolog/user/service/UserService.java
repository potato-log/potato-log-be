package site.potatolog.potatolog.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import site.potatolog.potatolog.user.domain.User;
import site.potatolog.potatolog.user.domain.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${spring.github.clientId}")
    private String clientId;

    @Value("${spring.github.clientSecret}")
    private String clientSecret;

    @Autowired
    private final UserRepository userRepository;

    public User saveUser(User userInfo) {
        log.info("DB에 사용자 정보 저장");

        Optional<User> existingUserBySocialAccountUid = userRepository.findBySocialAccountUid(userInfo.getSocialAccountUid());
        if (existingUserBySocialAccountUid.isPresent()) {
            log.info("User already exists with socialAccountUid: {}", userInfo.getSocialAccountUid());
            return existingUserBySocialAccountUid.get();
        }

        log.info("User: {}", userInfo);

        return userRepository.save(userInfo);
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

