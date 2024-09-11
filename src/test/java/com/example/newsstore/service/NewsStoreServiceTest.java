package com.example.newsstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.newsstore.model.NewsEntity;
import com.example.newsstore.repository.NewsRepository;

@ComponentScan(basePackages = "com.example.newsstore")
@DataJpaTest
@DisplayName("NewsStoreService Tests")
public class NewsStoreServiceTest {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsStoreService newsStoreService;

    @Test
    @DisplayName("Should save news to the database with status 'ok'")
    public void storeNews_ShouldSaveToDatabase() {
        // Arrange
        String newsJson = "{\"status\":\"ok\",\"totalResults\":34}";

        // Act
        newsStoreService.storeNews(newsJson);

        // Assert
        NewsEntity savedNewsEntity = newsRepository.findAll().get(0);
        System.out.println("Actual status: " + savedNewsEntity.getStatus());
        System.out.println("Actual Total Results: " + savedNewsEntity.getTotalResults());
        assertEquals("ok", savedNewsEntity.getStatus());
        assertEquals(34, savedNewsEntity.getTotalResults());
    }
}
