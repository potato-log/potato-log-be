package site.potatolog.potatolog.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    //발급받은 클라이언트ID
    @Value("${spring.github.clientId}")
    private String clientId;
    //secret 코드
    @Value("${spring.github.clientSecret}")
    private String clientSecret;

    //콜백 uri
    @Value("${spring.github.redirectUri}")
    private String redirectUri;

    public void gitLogin(String code){
        String accessToken = getGithubAccessToken(code);
        System.out.println("accessToken : " + accessToken);
        try {
            JsonNode userResourceNode = getUserResource(accessToken);
            ObjectMapper objectMapper = new ObjectMapper();
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userResourceNode);
            System.out.println("유저 리소스 값 : " + formattedJson);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public String getGithubAccessToken(String code){
        //여기로 code 보내서 accessToken 발급 받아야됨
        String accessTokenUrl = "https://github.com/login/oauth/access_token";

        //헤더설정
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");

        //요청 파라미터 설정
        MultiValueMap<String, String>param = new LinkedMultiValueMap<>();
        param.add("client_id", clientId);
        param.add("client_secret", clientSecret);
        param.add("code", code);
        param.add("redirect_uri", redirectUri);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(accessTokenUrl, new HttpEntity<>(param, headers), JsonNode.class);
        JsonNode accessTokenNode = response.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    public JsonNode getUserResource(String accessToken){
        //이 주소로 accesstoken값으로 get 요청을 보내서 유저 정보를 받아옴
        String resourceUri = "https://api.github.com/user";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }

}
