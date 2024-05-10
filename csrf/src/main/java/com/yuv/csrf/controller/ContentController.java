package com.yuv.csrf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuv.csrf.dto.MessageDto;
import com.yuv.csrf.service.ContentService;

@RestController
public class ContentController {
	
	@Autowired
	ContentService service;

	@GetMapping("/messages")
	ResponseEntity<List<MessageDto>> messages()
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllMessage());
	}
	
	@PostMapping(path ="/messages")
	ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.addMessage(messageDto));
	}
	
}
