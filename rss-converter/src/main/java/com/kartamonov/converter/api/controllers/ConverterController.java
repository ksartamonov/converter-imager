package com.kartamonov.converter.api.controllers;

import com.kartamonov.data.dto.ItemsListDto;
import com.kartamonov.converter.service.ConverterService;
import com.apptasticsoftware.rssreader.Item;
import com.kartamonov.data.model.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/converter-imager-api/converter")
public class ConverterController {
//    public static final String GET_NEWS = "/api/news/{rss_source}";
    List<Item> news;
    private final ConverterService converterService;

    public static final String SAVE_ITEMS = "/save_items";
    public static final String GET_ALL_ITEMS = "/get_all";
    public static final String GET_ITEM_BY_ID = "/get_by_id/{id}";
    public static final String GET_ITEMS_BY_AUTHOR = "/get_by_author/{author}";

    @Autowired
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

//    @PostMapping(SAVE_ITEMS)
//    public ResponseEntity<ItemsListDto> saveItems(
//            @PathVariable  String source) {
//        try {
//            List<ItemEntity> items = converterService.readNews(source);
//            ItemsListDto itemsListDto = converterService.convertAndSendNews(source, items);
//            return ResponseEntity.ok(itemsListDto);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping(SAVE_ITEMS)
    public ResponseEntity<ItemsListDto> saveItems(
            @RequestParam(value = "source", required = true) Optional<String> source) {
        try {
            List<ItemEntity> items = converterService.readNews(source.orElse(null));
            ItemsListDto itemsListDto = converterService.convertAndSendNews(source.orElse(null), items);
            return ResponseEntity.ok(itemsListDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(GET_ALL_ITEMS)
    public ResponseEntity<?> getAll() {
        return converterService.getAllItemsService();
    }


    @GetMapping(GET_ITEM_BY_ID)
    public ResponseEntity<?> getItemById(@PathVariable UUID id) {
        return converterService.getItemByIdService(id);
    }

    @GetMapping(GET_ITEMS_BY_AUTHOR)
    public ResponseEntity<?> getItemsByAuthor(@PathVariable String author) {
        return converterService.getItemsByAuthor(author);
    }

}


