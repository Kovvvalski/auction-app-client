package by.kovalski.auctionappclient.service;

import by.kovalski.auctionappclient.entity.dto.ItemDto;
import by.kovalski.auctionappclient.entity.dto.LotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LotService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/lot";

    @Autowired
    public LotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<LotDto> getAllLots(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        LotDto[] lots = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, LotDto[].class).getBody();
        return List.of(lots);
    }

    public LotDto getLotById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.GET, entity, LotDto.class).getBody();
    }
}
