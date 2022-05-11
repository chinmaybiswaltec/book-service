package com.chinmaybiswaltec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chinmaybiswaltec.model.BookInfo;
import com.chinmaybiswaltec.model.BookRating;
import com.fasterxml.jackson.core.sym.Name;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BookInfoService {
	
	@Autowired
	private RestTemplate template;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackBookInfo", 
			commandProperties = {
					@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value="2000")		
			})
	public BookInfo getBookInfo(BookRating rate) {
		BookInfo bookInfo = template.getForObject("http://book-info-service/bookinfo/" + rate.getBookId(),
				BookInfo.class);
		return bookInfo;
	}
	
	public BookInfo getFallbackBookInfo(BookRating rate) {
		return BookInfo.builder().bookId("").bookName("Info fallback").build();
	}
}
