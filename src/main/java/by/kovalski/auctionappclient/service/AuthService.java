package by.kovalski.auctionappclient.service;

import by.kovalski.auctionappclient.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService {
    private static final String LOGIN_URL = "http://localhost:8080/api/auth/login";
    private String jwtToken;

    public String login(String email, String password) throws AuthenticationException {
        RestTemplate restTemplate = new RestTemplate();

        // Формируем тело запроса
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        // Заголовки HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Выполняем POST-запрос
            ResponseEntity<Map> response = restTemplate.postForEntity(LOGIN_URL, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Извлекаем токен из тела ответа
                jwtToken = (String) response.getBody().get("token");
                return jwtToken;
            }
        } catch (Exception e) {
            log.error("Error during authentication: {}", e.getMessage(), e);
            throw new AuthenticationException("Error during authentication, try again");
        }

        return null;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
