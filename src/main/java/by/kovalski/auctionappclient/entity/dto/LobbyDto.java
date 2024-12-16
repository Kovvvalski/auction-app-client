package by.kovalski.auctionappclient.entity.dto;

import by.kovalski.auctionappclient.entity.LobbyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LobbyDto {
    private Long id;

    private LotDto lot;

    private UserDto owner;

    private Integer maxUsers;

    private Integer currentUsers;

    private LobbyStatus lobbyStatus;
}
