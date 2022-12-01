package com.kartamonov.data.constants;

public class RabbitConstants {
    public static final String EXCHANGE_NAME = "news";
    public static final String ITEMS = "items";

    public static class QueuesNames {
        public static final String SAVE_ITEMS = "saveItems";
        public static final String GET_ALL = "findAll";
        public static final String GET_ITEM_BY_ID = "findById";
        public static final String GET_ITEMS_BY_AUTHOR = "findByAuthor";
    }
}
