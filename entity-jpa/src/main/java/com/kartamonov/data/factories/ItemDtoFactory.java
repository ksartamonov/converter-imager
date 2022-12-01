package com.kartamonov.data.factories;

import com.kartamonov.data.dto.ItemDto;
import com.apptasticsoftware.rssreader.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoFactory {
    public static ItemDto makeItemDto(Item item) {

        return ItemDto.builder()
                .title(item.getTitle().get())
                .channelTitle(item.getChannel().getTitle())
                .link(item.getLink().get())
                .author(item.getAuthor().get())
                .publicationDate(item.getPubDate().get())
                .build();
    }
}
