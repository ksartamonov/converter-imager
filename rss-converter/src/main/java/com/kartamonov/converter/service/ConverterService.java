package com.kartamonov.converter.service;

import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.data.model.ItemEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ConverterService {
    List<ItemEntity> readNews(String source);

    ItemsListDto convertAndSendNews(String source, List<ItemEntity> items);

    ResponseEntity<?> getAllItemsService();

    public ResponseEntity<ItemsListDto> getItemByIdService(UUID id);

    ResponseEntity<ItemsListDto> getItemsByAuthor(String author);
}
