package by.kovalski.auctionappclient.controller;

import by.kovalski.auctionappclient.entity.dto.LotDto;
import by.kovalski.auctionappclient.service.AuthService;
import by.kovalski.auctionappclient.service.LotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/lots")
public class LotController {

    private final LotService lotService;
    private final AuthService authService;

    @GetMapping
    public String getAllLots(Model model) {
        List<LotDto> lots = lotService.getAllLots(authService.getJwtToken());
        model.addAttribute("lots", lots);
        return "lot_list";
    }

    @GetMapping("/{id}")
    public String getLot(@PathVariable Long id, Model model) {
        LotDto lot = lotService.getLotById(id, authService.getJwtToken());
        model.addAttribute("lot", lot);
        return "lot_detail";
    }
}
