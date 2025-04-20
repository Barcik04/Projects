package com.shop.Barcik.Controller;

import com.shop.Barcik.Service.ItemService;
import com.shop.Barcik.model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.findAll();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @GetMapping("/{name}")
    public List<Item> getItemByName(@PathVariable String name) {
        return itemService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return itemService.updateItem(item);
    }
}
