package com.yuv.csrf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yuv.csrf.dto.MessageDto;

@Service
public class ContentService {

List<MessageDto> messageList = new ArrayList<>();
	
	public List<MessageDto> getAllMessage()
	{
		return messageList;
	}
	
	public MessageDto addMessage(MessageDto message)
	{
		messageList.add(message);
		return message;
	}
	
}
