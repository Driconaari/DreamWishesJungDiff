package com.example.dreamwishes.service;

import com.example.dreamwishes.entity.Items;
import com.example.dreamwishes.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public Items findItemByName(String name) {
        return itemsRepository.findByName(name);
    }

    public List<Items> findItemsByCategory(String category) {
        return itemsRepository.findByCategory(category);
    }

    // Other methods using repository methods as needed
}
