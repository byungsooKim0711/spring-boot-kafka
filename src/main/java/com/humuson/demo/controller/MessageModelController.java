package com.humuson.demo.controller;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humuson.demo.kafka.KafkaTopics;
import com.humuson.demo.model.MessageModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("kafka")
@Slf4j
public class MessageModelController {

	@Autowired
	private KafkaTemplate<String, MessageModel> kafkaTemplate;
	
	@GetMapping("/pub/{message}")
	public ResponseEntity<MessageModel> sendMessage(@PathVariable("message") String message) {
		MessageModel model = new MessageModel();
		model.setId(UUID.randomUUID().toString());
		model.setMessage(message);
		model.setTimestamp(ZonedDateTime.now());
		
		ListenableFuture<SendResult<String, MessageModel>> future = kafkaTemplate.send(KafkaTopics.SIMPLE_KAFKA_TOPIC, model);
		future.addCallback(new ListenableFutureCallback<SendResult<String, MessageModel>>() {

			@Override
			public void onSuccess(SendResult<String, MessageModel> result) {
				log.info("Success on sending message \"" + message + "\"");
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Error on sending message \"" + message + "\", stacktrace " + ex.getMessage());
			}
		});
		
		return ResponseEntity.ok(model);
	}
	
	@GetMapping("/pub1/{message}")
	public ResponseEntity<MessageModel> send1Message(@PathVariable("message") String message) {
		MessageModel model = new MessageModel();
		model.setId(UUID.randomUUID().toString());
		model.setMessage(message);
		model.setTimestamp(ZonedDateTime.now());
		
		ListenableFuture<SendResult<String, MessageModel>> future = kafkaTemplate.send(KafkaTopics.JSON_KAFKA_TOPIC, model);
		future.addCallback(new ListenableFutureCallback<SendResult<String, MessageModel>>() {

			@Override
			public void onSuccess(SendResult<String, MessageModel> result) {
				log.info("Success on sending message \"" + message);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Error on sending message \"" + message + "\", stacktrace " + ex.getMessage());
			}
		});
		
		return ResponseEntity.ok(model);
	}
}
