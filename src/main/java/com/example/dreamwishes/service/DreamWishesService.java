package com.example.dreamwishes.service;

import com.example.dreamwishes.model.Wishes;
import com.example.dreamwishes.repository.WishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DreamWishesService {

    private final WishesRepository wishesRepository;

    @Autowired
    public DreamWishesService(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }


    public List<Wishes> getAllAttractions() {
        return wishesRepository.findAll();
    }

    public Optional<Wishes> getAttractionByName(String name) {
        return wishesRepository.findByName(name);
    }

    public void saveAttraction(Wishes attraction) {
        wishesRepository.save(attraction);
    }
    public void deleteAttractionByName(String name) {
        wishesRepository.deleteAttractionByName(name);
    }
}