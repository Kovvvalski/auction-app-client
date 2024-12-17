package by.kovalski.auctionappclient.service;

import by.kovalski.auctionappclient.entity.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;

@Service
public class ItemService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/item";

    @Autowired
    public ItemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ItemDto> getAllItems(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ItemDto[] items = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, ItemDto[].class).getBody();
        return List.of(items);
    }

    public ItemDto getItemById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.GET, entity, ItemDto.class).getBody();
    }

    public ItemDto createItem(ItemDto item, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<ItemDto> entity = new HttpEntity<>(item, headers);

        return restTemplate.postForObject(BASE_URL, entity, ItemDto.class);
    }

    public void updateItem(Long id, ItemDto item, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<ItemDto> entity = new HttpEntity<>(item, headers);

        restTemplate.put(BASE_URL + "/" + id, entity);
    }

    public void deleteItem(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, entity, Void.class);
    }
}
