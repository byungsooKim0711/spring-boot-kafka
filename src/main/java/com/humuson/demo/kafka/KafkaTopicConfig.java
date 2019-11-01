package com.humuson.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	/* 
	 * 스프링 부트를 사용할 땐 KafkaAdmin이 Bean으로 자동 등록되므로 NewTopic만 Bean으로 정의하면 된다. 
	 */
	
	@Bean
	public NewTopic topicProperties() {
		return null;
//		return TopicBuilder
//				.name(KafkaTopics.SIMPLE_KAFKA_TOPIC)
//				.partitions(10)
//				.replicas(3)
//				.compact()
//				.config(TopicConfig.COMPRESSION_TYPE_CONFIG, "xxxx???")
//				.assignReplicas(0, Arrays.asList(0, 1))
//				.assignReplicas(1, Arrays.asList(1, 2))
//				.build();
	}
	
	@Bean
	public NewTopic simpleKafkaTopic() {
		return TopicBuilder
				.name(KafkaTopics.SIMPLE_KAFKA_TOPIC)
				.build();
	}
	
	@Bean
	public NewTopic jsonKafkaTopic() {
		return TopicBuilder
				.name(KafkaTopics.JSON_KAFKA_TOPIC)
				.build();
	}
}
