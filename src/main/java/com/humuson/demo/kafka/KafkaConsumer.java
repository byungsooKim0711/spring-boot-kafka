package com.humuson.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.humuson.demo.model.MessageModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	
	@KafkaListener(topics = KafkaTopics.SIMPLE_KAFKA_TOPIC, groupId = "group_id")
	public void consume(String message) {
		log.info("Consumed message: {}", message);
	}
	
	@KafkaListener(topics = KafkaTopics.JSON_KAFKA_TOPIC, groupId = "group_json", containerFactory = "messageModelKafkaListenerFactory")
	public void consumeJson(MessageModel messageModel) {
		log.info("Consumed Json Message: {}", messageModel);
	}
}
