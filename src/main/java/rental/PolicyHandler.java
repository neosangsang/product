package rental;

import rental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
//modified : 20200714
@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_ProductChange(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            Product product = new Product();
            product.setId(orderCanceled.getId());
            product.setAmount(Integer.parseInt(""+product.getAmount())+1);
            productRepository.save(product);
            System.out.println("##### listener wheneverOrderCanceled_ProductChange : " + orderCanceled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_ProductChange(@Payload Ordered ordered){

        if(ordered.isMe()){
            Product product = new Product();
            product.setId(ordered.getId());
            product.setAmount(Integer.parseInt(""+product.getAmount())-1);
            productRepository.save(product);
            System.out.println("##### listener wheneverOrdered_ProductChange : " + ordered.toJson());
        }
    }

}
