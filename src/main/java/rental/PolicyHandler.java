package rental;

import rental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

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
            Product product = null;
            Optional<Product> optional = productRepository.findById(orderCanceled.getProductId());
            if(optional.isPresent()) {
                product = optional.get();
                product.setId(orderCanceled.getProductId());
                product.setAmount(product.getAmount() != null ? product.getAmount().intValue() + 1 : 0);
                productRepository.save(product);
                System.out.println("##### listener wheneverOrderCanceled_ProductChange : " + orderCanceled.toJson());
            }else
                System.out.println("##### listener wheneverOrderCanceled_ProductChange : null ");
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_ProductChange(@Payload Ordered ordered){

        if(ordered.isMe()){
            Product product = null;
            Optional<Product> optional = productRepository.findById(ordered.getProductId());
            if(optional.isPresent()) {
                product = optional.get();

                product.setId(ordered.getProductId());
                product.setAmount(product.getAmount() != null ? product.getAmount().intValue() - 1 : 0);
                productRepository.save(product);
                System.out.println("##### listener wheneverOrdered_ProductChange : " + ordered.toJson());
            }else
                System.out.println("##### listener wheneverOrdered_ProductChange : null ");
        }
    }

}
