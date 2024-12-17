package by.kovalski.auctionappclient.controller;

import by.kovalski.auctionappclient.entity.dto.ItemDto;
import by.kovalski.auctionappclient.service.AuthService;
import by.kovalski.auctionappclient.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final AuthService authService;

    public ItemController(ItemService itemService, AuthService authService) {
        this.itemService = itemService;
        this.authService = authService;
    }

    @GetMapping
    public String getAllItems(Model model) {
        List<ItemDto> items = itemService.getAllItems(authService.getJwtToken());
        model.addAttribute("items", items);
        return "item_list";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        ItemDto item = itemService.getItemById(id, authService.getJwtToken());
        model.addAttribute("item", item);
        return "item_detail"; // имя HTML-шаблона для отображения деталей предмета
    }

    @GetMapping("/new")
    public String createItem(Model model) {
        model.addAttribute("item", new ItemDto());
        return "new_item";
    }

    @PostMapping("/new")
    public String createItem(@ModelAttribute ItemDto item) {
        itemService.createItem(item, authService.getJwtToken());
        return "redirect:/items"; // перенаправление на список предметов после создания
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute ItemDto item) {
        itemService.updateItem(id, item, authService.getJwtToken());
        return "redirect:/items";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id, authService.getJwtToken());
        return "redirect:/items";
    }
}
