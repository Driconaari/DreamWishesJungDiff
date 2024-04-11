package com.example.dreamwishes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping
    public ResponseEntity<ItemsDTO> createItem(@RequestBody ItemsDTO itemsDTO) {
        ItemsDTO createdItem = itemsService.createItem(itemsDTO);
        return ResponseEntity.created(URI.create("/api/items/" + createdItem.getId())).body(createdItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemsDTO> getItem(@PathVariable Long itemId) {
        ItemsDTO itemsDTO = itemsService.getItemById(itemId);
        return ResponseEntity.ok(itemsDTO);
    }
}