package pl.com.itti.kafkaproducer.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.itti.kafkaproducer.model.NetFlowFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

@Component
public class NetFlowProducer {

    private static final Queue<NetFlowFrame> netFlowFrameQueue = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(NetFlowProducer.class);
    private static final String DELIMITER = ",";
    private static final String DIR_DATA = "data";
    private static final String TOPIC = "netflow";

    private final KafkaTemplate<String, NetFlowFrame> kafkaTemplate;

    @Autowired
    public NetFlowProducer(KafkaTemplate<String, NetFlowFrame> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        convertLoadFileToModel();
    }

    public void sendMessage(NetFlowFrame netFlowFrame) {
        logger.info(String.format("#### -> Producing message -> %s", netFlowFrame.toString()));
        this.kafkaTemplate.send(TOPIC, netFlowFrame);
    }

    @Scheduled(fixedRate = 1000)
    private void produceNetFlowFrameIntoKafka(){
        if (netFlowFrameQueue.peek() != null) {
            sendMessage(netFlowFrameQueue.poll());
        }
    }

    private void convertLoadFileToModel(){
        logger.info("Load all NetFlow Model into Memory");
        List<String> loadedFiles = loadAllFilePathFromDirectory();

        loadedFiles.forEach(filePath-> {
            try(Stream<String> lines = Files.lines(Paths.get(filePath))){
                lines.filter(line -> line.contains(DELIMITER)).forEach(
                        line -> netFlowFrameQueue.add(new NetFlowFrame(line.split(DELIMITER)))
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logger.info("Complete - Load all NetFlow Model into Memory: " + netFlowFrameQueue.size() );
    }


    private List<String> loadAllFilePathFromDirectory(){
        List<String> loadedFiles = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(DIR_DATA))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(it -> loadedFiles.add(it.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedFiles;
    }

}
