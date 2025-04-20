package com.shop.Barcik.Service;

import com.shop.Barcik.model.Item;
import com.shop.Barcik.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository ItemRepository;

    public ItemService(ItemRepository ItemRepository) {
        this.ItemRepository = ItemRepository;
    }

    public List<Item> findAll() {
        return ItemRepository.findAll();
    }

    public Item addItem(Item item) {
        return ItemRepository.save(item);
    }

    public List<Item> findByName(String name) {
        return ItemRepository.findByName(name);
    }

    public void deleteById(Long id) {
        ItemRepository.deleteById(id);
    }

    public Item updateItem(Item item) {
        return ItemRepository.save(item);
    }
}
