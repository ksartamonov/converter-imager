package com.kartamonov.converter.service.implementation;

import com.kartamonov.data.constants.RabbitConstants;
import com.kartamonov.data.dto.AckDto;
import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.converter.service.exceptions.BadSourceException;
import com.kartamonov.data.factories.AuthorDtoFactory;
import com.kartamonov.data.factories.IdDtoFactory;
import com.kartamonov.data.factories.ItemsListDtoFactory;
import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.kartamonov.data.model.ItemEntity;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.kartamonov.converter.service.ConverterService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    public ConverterServiceImpl(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public List<ItemEntity> readNews(String source) {
        RssReader reader = new RssReader();
        Stream<Item> rssFeed;
        try {
            rssFeed = reader.read(source);
        } catch (IOException e) {
            throw new BadSourceException("Incorrect RSS source");
        }
//        List <Item> items = rssFeed.collect(Collectors.toList());
        List <ItemEntity> items = createEntityListFromItemList(rssFeed.collect(Collectors.toList()));
        return items;
//        return createEntityListFromItemList(rssFeed.collect(Collectors.toList()));
//        return rssFeed.collect(Collectors.toList());
    }

    @Override
    public AckDto convertAndSendNews(String source, List<ItemEntity> items) {
        return (AckDto) rabbitTemplate.
                convertSendAndReceive(
                        directExchange.getName(), RabbitConstants.QueuesNames.SAVE_ITEMS,
                        ItemsListDtoFactory.makeItemsListDto(items));
    }

    private List<ItemEntity> createEntityListFromItemList(List<Item> itemList) {
        List<ItemEntity> itemEntityList = new ArrayList<>();
        for (Item item : itemList) {
            itemEntityList.add(new ItemEntity(item.getTitle().orElse(null),
                    item.getLink().orElse(null),
                    item.getAuthor().orElse(null),
                    item.getPubDate().orElse(null),
                    item.getChannel().getTitle()));
        }
        return itemEntityList;
    }

    @Override
    public ResponseEntity<ItemsListDto> getAllItemsService() {
        try {
            ItemsListDto itemsListDto = (ItemsListDto) rabbitTemplate.convertSendAndReceive(directExchange.getName(), RabbitConstants.QueuesNames.GET_ALL, "");
            if (itemsListDto == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(itemsListDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<ItemsListDto> getItemByIdService(UUID id) {
        try {
            ItemsListDto itemsListDto = (ItemsListDto) rabbitTemplate.convertSendAndReceive(
                    directExchange.getName(), RabbitConstants.QueuesNames.GET_ITEM_BY_ID, IdDtoFactory.makeIdDto(id));
            if (itemsListDto == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(itemsListDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<ItemsListDto> getItemsByAuthor(String author) {
        try {
            ItemsListDto itemsListDto = (ItemsListDto) rabbitTemplate.convertSendAndReceive(
                    directExchange.getName(), RabbitConstants.QueuesNames.GET_ITEMS_BY_AUTHOR,
                    AuthorDtoFactory.makeAuthorDto(author));
            if (itemsListDto == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(itemsListDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

