package com.chinmaybiswaltec.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chinmaybiswaltec.controller.BooksCatalogController;
import com.chinmaybiswaltec.model.BookRating;
import com.chinmaybiswaltec.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BookRatingService {
	
	private static final Logger logger = LoggerFactory.getLogger(BooksCatalogController.class);
	
	@Autowired
	private RestTemplate template;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackBookRatings")
	public UserRating getBookRatings(String userId) {
		logger.info("In BookRatingService {}", userId);
		UserRating userRating = template.getForObject("http://book-rating-service/bookratings/users/" + userId,
				UserRating.class);
		return userRating;
	}
	
	public UserRating getFallbackBookRatings(String userId) {
		logger.info("In BookRatingService fall back {}", userId);
		List<BookRating> bookRatings = new ArrayList<>();
		BookRating rating =BookRating.builder().bookId("userId").rating("0").build();
		bookRatings.add(rating);
		return UserRating.builder().bookRatings(bookRatings).build();
	}

}
