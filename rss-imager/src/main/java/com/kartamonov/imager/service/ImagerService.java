package com.kartamonov.imager.service;

import com.kartamonov.data.dto.AckDto;
import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.data.model.ItemEntity;

import java.util.List;
import java.util.UUID;

public interface ImagerService {
    AckDto save(List<ItemEntity> items);

    ItemsListDto findById(UUID id);

    ItemsListDto findAll();

    ItemsListDto findAllByAuthor(String author);
}
