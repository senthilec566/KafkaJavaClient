package com.ebay.kafkaclient;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducer {

	public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        Random rnd = new Random();
 
        Properties props = new Properties();
        props.put("metadata.broker.list", "");
        props.put("bootstrap.servers", "");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1"); 
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");        
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props); 
        for (long nEvents = 0; nEvents < events; nEvents++) { 
               long runtime = new Date().getTime();  
               String ip = "10.242.82." + rnd.nextInt(255); 
               String msg = runtime + ",www.example.com," + ip; 
               ProducerRecord<String, String> prodcuerRecord = new ProducerRecord<String, String>("knox_reports", msg);
               producer.send(prodcuerRecord);
        }
        producer.close();
    }
}
