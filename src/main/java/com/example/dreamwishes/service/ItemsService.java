package com.example.dreamwishes.service;

import com.example.dreamwishes.dto.ItemsDTO;
import com.example.dreamwishes.entity.Items;
import com.example.dreamwishes.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public ItemsDTO getItemById(Long itemId) {
        // Retrieve item from repository
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        // Map Items entity to ItemsDTO
        ItemsDTO itemsDTO = mapToDTO(item);

        return itemsDTO;
    }

    // Helper method to map Items entity to ItemsDTO
    private ItemsDTO mapToDTO(Items item) {
        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setId(item.getId());
        itemsDTO.setName(item.getName());
        itemsDTO.setDescription(item.getDescription());
        itemsDTO.setPrice(item.getPrice());
        // Map other fields as needed
        return itemsDTO;
    }

    public ItemsDTO createItem(ItemsDTO itemsDTO) {
        // Map ItemsDTO to Items entity
        Items item = mapToEntity(itemsDTO);

        // Save the item in the repository
        Items savedItem = itemsRepository.save(item);

        // Map the saved Items entity back to ItemsDTO
        ItemsDTO savedItemDTO = mapToDTO(savedItem);

        return savedItemDTO;
    }
    // Helper method to map ItemsDTO to Items entity
    private Items mapToEntity(ItemsDTO itemsDTO) {
        Items item = new Items();
        item.setName(itemsDTO.getName());
        item.setDescription(itemsDTO.getDescription());
        item.setPrice(itemsDTO.getPrice());
        // Map other fields as needed
        return item;
    }

}

