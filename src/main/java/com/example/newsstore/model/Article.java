package com.example.newsstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Article {

    @JsonProperty("source")
    @Embedded
    private Source source;

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    @Column(columnDefinition = "TEXT") // Change the data type to TEXT
    private String description;

    @JsonProperty("url")
    private String url;

    @JsonProperty("urlToImage")
    @Column(columnDefinition = "TEXT") // Change the data type to TEXT
    private String urlToImage;

    @JsonProperty("publishedAt")
    private String publishedAt;

    @JsonProperty("content")
    @Column(columnDefinition = "TEXT") // Change the data type to TEXT
    private String content;
}
