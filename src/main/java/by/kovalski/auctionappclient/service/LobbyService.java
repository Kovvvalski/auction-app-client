package by.kovalski.auctionappclient.service;

import by.kovalski.auctionappclient.entity.dto.LobbyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LobbyService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/lobby"; // URL вашего REST API

    @Autowired
    public LobbyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<LobbyDto> getAllLobbies(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        LobbyDto[] lobbies = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, LobbyDto[].class).getBody();
        return List.of(lobbies);
    }

    public LobbyDto getLobbyById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.GET, entity, LobbyDto.class).getBody();
    }

    public LobbyDto createLobby(LobbyDto lobby, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<LobbyDto> entity = new HttpEntity<>(lobby, headers);

        return restTemplate.postForObject(BASE_URL, entity, LobbyDto.class);
    }

    public void updateLobby(Long id, LobbyDto lobby, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<LobbyDto> entity = new HttpEntity<>(lobby, headers);

        restTemplate.put(BASE_URL + "/" + id, entity);
    }

    public void deleteLobby(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, entity, Void.class);
    }
}
