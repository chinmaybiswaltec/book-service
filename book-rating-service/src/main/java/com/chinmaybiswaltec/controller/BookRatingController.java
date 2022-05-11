package com.chinmaybiswaltec.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.model.BookRating;
import com.chinmaybiswaltec.model.UserRating;

@RestController
@RequestMapping(path = "/bookratings")
public class BookRatingController {
	private static final Logger logger = LoggerFactory.getLogger(BookRatingController.class);

	@GetMapping(path = "/{bookId}")
	public BookRating getBookRating(@PathVariable("bookId") String bookId) {
		return BookRating.builder().bookId(bookId).rating("5").build();
	}

	@GetMapping(path = "/users/{userId}")
	public UserRating getBookRatings(@PathVariable("userId") String userId) {
		logger.info("In BookInformationController {} ", userId);
		List<BookRating> bookRatingList = new ArrayList<>();
		bookRatingList.add(BookRating.builder().bookId("m1d0DwAAQBAJ").rating("4").build());
		return UserRating.builder().bookRatings(bookRatingList).build();
	}

}
