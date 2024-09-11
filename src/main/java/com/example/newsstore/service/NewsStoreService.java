package com.example.newsstore.service;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.newsstore.model.NewsEntity;
import com.example.newsstore.repository.NewsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsStoreService {

    private final NewsRepository newsRepository;

    @RabbitListener(queues = "news-queue") // Match with the queue name used in NewsFetchService
    public void storeNews(String newsJson) {
        // Convert JSON string to NewsEntity and save it to the database
        LoggerFactory.getLogger(NewsStoreService.class).info("Got in storeNews method.");  //debug
        NewsEntity newsEntity = convertJsonToNewsEntity(newsJson);
        if (newsEntity != null) {
            // Delete all existing entries
            newsRepository.deleteAll();
            LoggerFactory.getLogger(NewsStoreService.class).info("Deleted all existing entries.");  //debug

            // Save the new entry
            newsRepository.save(newsEntity);
            LoggerFactory.getLogger(NewsStoreService.class).info("Saved new entry.");   //debug
        } else {
            // Log an error or handle the case where the conversion failed
            LoggerFactory.getLogger(NewsStoreService.class).error("Error converting JSON to NewsEntity.");
        }
        newsEntity=null;
    }

    //Json to Entity method that is used above
    private NewsEntity convertJsonToNewsEntity(String newsJson) {
        // Implement JSON to entity conversion logic here
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(newsJson, NewsEntity.class);
        } catch (JsonProcessingException e) {
            //Log the error
            LoggerFactory.getLogger(NewsStoreService.class).error("Error converting JSON to NewsEntity: {}", e.getMessage(), e);
            return null; // Return null to indicate the conversion failure
        }
    }
}
