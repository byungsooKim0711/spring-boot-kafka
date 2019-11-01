package com.humuson.demo.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MessageModel {
	
	private String id;
	
	private String message;
	
	private ZonedDateTime timestamp;
}
