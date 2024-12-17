package by.kovalski.auctionappclient.controller;

import by.kovalski.auctionappclient.entity.dto.LobbyDto;
import by.kovalski.auctionappclient.service.AuthService;
import by.kovalski.auctionappclient.service.LobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lobbies")
@RequiredArgsConstructor
public class LobbyController {

    private final AuthService authService;
    private final LobbyService lobbyService;

    @GetMapping
    public String getAllLobbies(Model model) {
        List<LobbyDto> lobbies = lobbyService.getAllLobbies(authService.getJwtToken());
        model.addAttribute("lobbies", lobbies);
        return "lobby_list";
    }

    @GetMapping("/{id}")
    public String getLobby(@PathVariable Long id, Model model) {
        LobbyDto lobby = lobbyService.getLobbyById(id, authService.getJwtToken());
        model.addAttribute("lobby", lobby);
        return "lobby_detail";
    }

    @GetMapping("/new")
    public String createLobby(Model model) {
        model.addAttribute("lobby", new LobbyDto());
        return "new_lobby";
    }

    @PostMapping("/new")
    public String createLobby(@ModelAttribute LobbyDto lobby) {
        lobbyService.createLobby(lobby, authService.getJwtToken());
        return "redirect:/lobby";
    }

    @PostMapping("/update/{id}")
    public String updateLobby(@PathVariable Long id, @ModelAttribute LobbyDto lobby) {
        lobbyService.updateLobby(id, lobby, authService.getJwtToken());
        return "redirect:/lobby";
    }

    @PostMapping("/delete/{id}")
    public String deleteLobby(@PathVariable Long id) {
        lobbyService.deleteLobby(id, authService.getJwtToken());
        return "redirect:/lobby";
    }

}
