package com.kartamonov.data.factories;

import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.data.model.ItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemsListDtoFactory {
    public static ItemsListDto makeItemsListDto(List<ItemEntity> itemsList) {
        return ItemsListDto.builder()
                .items(itemsList)
                .build();
    }
}
