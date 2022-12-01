package com.kartamonov.imager.service.implementation;

import com.kartamonov.data.dto.AckDto;
import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.data.model.ItemEntity;
import com.kartamonov.imager.service.ImagerService;
import com.kartamonov.imager.service.exceptions.ItemNotFoundException;
import com.kartamonov.imager.store.repositories.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Service
public class ImagerServiceImpl implements ImagerService {
    private ItemRepository itemRepository;

    @Autowired
    public ImagerServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public AckDto save(List<ItemEntity> items) {
        itemRepository.saveAll(items);
        return AckDto.builder().answer(true).build();
    }

    @Override
    public ItemsListDto findById(UUID id) {
        List<ItemEntity> res = new ArrayList<>();
        res.add(itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id)));
        return ItemsListDto.builder().items(res).build();
    }

    @Override
    public ItemsListDto findAll() {
        List<ItemEntity> items = itemRepository.findAll();
        return ItemsListDto.builder().items(items).build();
    }

    @Override
    public ItemsListDto findAllByAuthor(String author) {
        List<ItemEntity> items = itemRepository.findAllByAuthor(author).orElseThrow(() -> new ItemNotFoundException("Item with author = " + author + " not exists"));
        return ItemsListDto.builder().items(items).build();
    }

}
