package com.example.kafkaconsumer.consumer;

import com.example.kafkaconsumer.model.EventModel;
import com.example.kafkaconsumer.utils.SendEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class ConsumerService
{

      private static final Logger logger = Logger.getLogger("ConsumerService");

      @KafkaListener(topics = {"customer-topic", "restaurant-topic", "courier-topic", "another-test-topic"}, groupId = "exam-project")
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
                  if (statusCode >= 200 && statusCode < 400){
                        System.out.println("Log for 200 / 300");
                        logger.info(message);
                  } else if (statusCode >= 400 && statusCode < 500){
                        System.out.println("Log for 400");
                        logger.warning(message);
                  } else if (statusCode >= 500 && statusCode < 600) {
                        if (statusCode == 503) {
                              SendEmail sendEmail = new SendEmail();
                              sendEmail.Send_Email("Service Unavailable","mattibenhansen@gmail.com","Service Unavailable: "+eventModel.getResponseBody());
                        }
                        System.out.println("Log for 500");
                        logger.severe(message);
                  }
            } catch (IOException e) {
                  e.printStackTrace();
            }

      }

}