package com.kartamonov.imager.rabbit;

import com.kartamonov.data.constants.RabbitConstants;
import com.kartamonov.data.dto.AckDto;
import com.kartamonov.data.dto.AuthorDto;
import com.kartamonov.data.dto.IdDto;
import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.imager.service.ImagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class ImagerListener {
    private final ImagerService imagerService;
    private final Logger logger = LoggerFactory.getLogger(ImagerListener.class);

    @Autowired
    public ImagerListener(ImagerService imagerService) {
        this.imagerService = imagerService;
    }

    @RabbitListener(queues = RabbitConstants.QueuesNames.SAVE_ITEMS)
    public AckDto saveItems(@Payload ItemsListDto itemsListDto) {
        System.out.println("Items received!");
        try {
            AckDto ackDto = imagerService.save(itemsListDto.getItems());
            logger.info("Items added successfully");
            return ackDto;
        } catch (Exception e) {
            throw new RuntimeException("Exception during saving items: " + e);
        }
    }

    @RabbitListener(queues = RabbitConstants.QueuesNames.GET_ALL)
    public ItemsListDto getALl() {
        try {
            ItemsListDto items = imagerService.findAll();
            logger.info("Items found successfully");
            return items;
        } catch (Exception e) {
            throw new RuntimeException("Exception during getting all items: " + e);
        }
    }

    @RabbitListener(queues = RabbitConstants.QueuesNames.GET_ITEM_BY_ID)
    public ItemsListDto getItemById(@Payload IdDto idDto) {
        try {
            ItemsListDto item = imagerService.findById(idDto.getId());
            logger.info("Item with id = {} found", idDto.getId());
            return item;
        } catch (Exception e) {
            throw new RuntimeException("Item with id = " + idDto.getId() + " not found");
        }
    }

    @RabbitListener(queues = RabbitConstants.QueuesNames.GET_ITEMS_BY_AUTHOR)
    public ItemsListDto getItemByAuthor(@Payload AuthorDto authorDto) {
        try {
            ItemsListDto item = imagerService.findAllByAuthor(authorDto.getAuthor());
            logger.info("Item with author = {} found", authorDto.getAuthor());
            return item;
        } catch (Exception e) {
            throw new RuntimeException("Item with author = " + authorDto.getAuthor() + " not found");
        }
    }



}
