package com.chinmaybiswaltec.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.model.BookCatalog;
import com.chinmaybiswaltec.model.BookInfo;
import com.chinmaybiswaltec.model.UserRating;
import com.chinmaybiswaltec.service.BookInfoService;
import com.chinmaybiswaltec.service.BookRatingService;

@RestController
@RequestMapping(path = "/bookcatalog")
public class BooksCatalogController {

	private static final Logger logger = LoggerFactory.getLogger(BooksCatalogController.class);
	
	@Autowired
	private BookRatingService bookRatingService;
	
	@Autowired
	private BookInfoService bookInfoService;

	@GetMapping(path = "/{userId}")
	public List<BookCatalog> getBookCatalog(@PathVariable("userId") String userId) {
		logger.info("In BooksCatalogController {}", userId);
		// Get all the rated books
		UserRating userRating = bookRatingService.getBookRatings(userId);

		return userRating.getBookRatings().stream().map(rate -> {
			BookInfo bookInfo = bookInfoService.getBookInfo(rate);
			return BookCatalog.builder().name(bookInfo.getBookName())
					.rating(rate.getRating()).desc(bookInfo.getDesc()).build();
		}).collect(Collectors.toList());
	}

}
