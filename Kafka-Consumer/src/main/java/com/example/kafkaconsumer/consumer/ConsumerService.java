package com.example.kafkaconsumer.consumer;

import com.example.kafkaconsumer.model.EventModel;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class ConsumerService
{

      private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

      @KafkaListener(topics = {"customer-topic", "restaurant-topic","another-test-topic"}, groupId = "exam-project")

      public void consume(String message) throws IOException
      {
            String filePath = "C:\\tmp\\consumerservice-logs\\consumer.log";
            FileHandler fileHandler = new FileHandler(filePath, 0, 1, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            ObjectMapper mapper = new ObjectMapper();
            try {
                  EventModel eventModel = mapper.readValue(message, EventModel.class);

                  String status = eventModel.getStatus();

                  int statusCode = Integer.parseInt(status.split(" ")[0]);
                  if (statusCode >= 300 && statusCode < 400){
                        System.out.println("Log for 300");
                        logger.info(message);
                  } else if (statusCode >= 400 && statusCode < 500){
                        System.out.println("Log for 400");
                        logger.warning(message);
                  } else if (statusCode >= 500 && statusCode < 600) {
                        System.out.println("Log for 500");
                        logger.severe(message);
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }

      }

}

