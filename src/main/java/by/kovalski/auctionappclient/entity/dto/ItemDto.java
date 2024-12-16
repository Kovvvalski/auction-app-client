package by.kovalski.auctionappclient.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private UserDto user;
}
