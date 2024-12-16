package by.kovalski.auctionappclient.entity.dto;

import by.kovalski.auctionappclient.entity.LotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LotDto {
    private Long id;

    private ItemDto item;

    private Double price;

    private UserDto seller;

    private UserDto lastCustomer;

    private LocalDateTime creationTime;

    private LocalDateTime expirationTime;

    private LotType lotType;
}
