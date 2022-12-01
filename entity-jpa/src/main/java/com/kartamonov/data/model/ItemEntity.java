package com.kartamonov.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "channel_title")
    private String channelTitle;

    public ItemEntity(String title, String link, String author, String publicationDate, String channelTitle) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.publicationDate = publicationDate;
        this.channelTitle = channelTitle;
    }
}

