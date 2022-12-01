package com.kartamonov.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDto { // Based on Item Entity of RssReader. Fields can be changed according to the specifics of the task

    @NonNull
    private String title;

    @NonNull
    private String link;

    @NonNull
    private String author;

    @NonNull
            @JsonProperty("publication_date")
    private String publicationDate;

    @NonNull
            @JsonProperty("channel_title")
    private String channelTitle;
}
