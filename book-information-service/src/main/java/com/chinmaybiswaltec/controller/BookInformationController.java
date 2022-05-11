package com.chinmaybiswaltec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinmaybiswaltec.model.BookInfo;

@RestController
@RequestMapping("/bookinfo")
public class BookInformationController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookInformationController.class);

	@Value("${api.key}")
	private String key;

	@GetMapping("/{bookId}")
	public BookInfo getBookInformation(@PathVariable("bookId") String bookId) {
		logger.info("In BookInformationController {} ", bookId);
		return BookInfo.builder().bookName("book name").bookId(bookId).desc("desc ").build();
	}

}
