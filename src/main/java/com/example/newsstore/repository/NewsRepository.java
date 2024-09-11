package com.example.newsstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newsstore.model.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    //todo custom queries
}
