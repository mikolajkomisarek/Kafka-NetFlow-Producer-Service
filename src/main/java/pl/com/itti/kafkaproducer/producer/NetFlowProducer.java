package pl.com.itti.kafkaproducer.producer;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.com.itti.kafkaproducer.model.NetFlowFrame;

@Component
public class NetFlowProducer {

    private static final Logger logger = LoggerFactory.getLogger(NetFlowProducer.class);
    private static final String TOPIC = "netflow";
    private final KafkaTemplate<String, NetFlowFrame> kafkaTemplate;

    @Autowired
    public NetFlowProducer(KafkaTemplate<String, NetFlowFrame> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NetFlowFrame netFlowFrame) {
        logger.info(String.format("#### -> Producing message -> %s", netFlowFrame.toString()));
        this.kafkaTemplate.send(TOPIC, netFlowFrame);
    }
}
