package pl.com.itti.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.itti.kafkaproducer.model.NetFlowFrame;
import pl.com.itti.kafkaproducer.producer.NetFlowProducer;

@RestController
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    private final NetFlowProducer netFlowProducer;

    @Autowired
    public ProducerController(NetFlowProducer netFlowProducer) {
        this.netFlowProducer = netFlowProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody NetFlowFrame netFlowFrame) {
        this.netFlowProducer.sendMessage(netFlowFrame);
    }

}
