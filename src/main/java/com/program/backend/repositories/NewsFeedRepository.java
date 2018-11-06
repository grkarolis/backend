package com.program.backend.repositories;

import com.program.backend.beans.entity.NewsFeed;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsFeedRepository extends PagingAndSortingRepository<NewsFeed, Long> {
}
