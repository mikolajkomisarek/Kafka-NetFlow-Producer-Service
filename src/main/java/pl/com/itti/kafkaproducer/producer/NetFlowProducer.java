package pl.com.itti.kafkaproducer.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.itti.kafkaproducer.model.NetFlowFrameAvro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Component
public class NetFlowProducer {

    private static final Queue<NetFlowFrameAvro> netFlowFrameQueue = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(NetFlowProducer.class);
    private static final String DELIMITER = ",";
    private static final String DIR_DATA = "data";
    private static final String TOPIC = "netflow-raw-data";

    private final KafkaTemplate<String, NetFlowFrameAvro> kafkaTemplate;

    @Autowired
    public NetFlowProducer(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaTemplate<String, NetFlowFrameAvro> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        convertLoadFileToModel();
    }



    @Scheduled(fixedRate = 1000)
    private void produceNetFlowFrameIntoKafka() {
        if (netFlowFrameQueue.peek() != null) {
            sendMessage(netFlowFrameQueue.poll());
        }
    }


    private void sendMessage(NetFlowFrameAvro netFlowFrame) {
        logger.info(String.format("#### -> Producing message -> %s", netFlowFrame.toString()));
        this.kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), netFlowFrame);
    }

    private void convertLoadFileToModel() {
        logger.info("Loading all NetFlow Model into Memory");
        List<String> loadedFiles = loadAllFilePathFromDirectory();

        loadedFiles.forEach(filePath -> {
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lines.filter(line -> line.contains(DELIMITER)).forEach(
                        line -> netFlowFrameQueue.add(getNetFlowFrameFromLine(line.split(DELIMITER)))
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logger.info("Complete - Load all NetFlow Model into Memory: " + netFlowFrameQueue.size());
    }


    private List<String> loadAllFilePathFromDirectory() {
        List<String> loadedFiles = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(DIR_DATA))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(it -> !it.toString().contains(".keep") && it.toString().contains(".2format"))
                    .forEach(it -> loadedFiles.add(it.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedFiles;
    }


    private NetFlowFrameAvro getNetFlowFrameFromLine(String[] netFlowLine) {
        return new NetFlowFrameAvro(
                netFlowLine[0],
                netFlowLine[1],
                netFlowLine[2],
                netFlowLine[3],
                netFlowLine[4],
                netFlowLine[5],
                netFlowLine[6],
                netFlowLine[7],
                netFlowLine[8],
                netFlowLine[9],
                netFlowLine[10],
                netFlowLine[11],
                netFlowLine[12],
                netFlowLine[13],
                netFlowLine[14],
                netFlowLine[15],
                netFlowLine[16],
                netFlowLine[17],
                netFlowLine[18],
                netFlowLine[19],
                netFlowLine[20],
                netFlowLine[21],
                netFlowLine[22],
                netFlowLine[23],
                netFlowLine[24],
                netFlowLine[25],
                netFlowLine[26],
                netFlowLine[27],
                netFlowLine[28],
                netFlowLine[29],
                netFlowLine[30],
                netFlowLine[31],
                netFlowLine[32]
        );
    }
}
