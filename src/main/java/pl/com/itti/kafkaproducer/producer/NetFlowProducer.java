package pl.com.itti.kafkaproducer.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.com.itti.model.NetFlowFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class NetFlowProducer {

    private static final Logger logger = LoggerFactory.getLogger(NetFlowProducer.class);
    private static final String DELIMITER = ",";
    private static final String DIR_DATA = "data";
    private static final String TOPIC = "netflow-z11";

    private final KafkaTemplate<String, NetFlowFrame> kafkaTemplate;

    @Autowired
    public NetFlowProducer(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaTemplate<String, NetFlowFrame> kafkaTemplate) throws InterruptedException {
        this.kafkaTemplate = kafkaTemplate;
        convertLoadFileToModel();
    }


    private void sendMessage(NetFlowFrame netFlowFrame) {
        logger.info(String.format("#### -> Producing message -> %s", netFlowFrame.toString()));
        this.kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), netFlowFrame);
    }

    private void convertLoadFileToModel() throws InterruptedException {
        logger.info("Loading all NetFlow Model into Memory");
        List<String> loadedFiles = loadAllFilePathFromDirectory();

        List<NetFlowFrame> frames = new ArrayList<>();

        loadedFiles.forEach(filePath -> {
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lines.filter(line -> line.contains(DELIMITER)).forEach(
                        line -> frames.add(getNetFlowFrameFromLine(line.split(DELIMITER)))
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        for(int x =0 ; x < frames.size();x++){
            var current = frames.get(x);
            var next = frames.get(x+1);

            logger.info("waiting for : " + (next.getStartTime().toEpochMilli() - current.getStartTime().toEpochMilli()) + " ms");
            Thread.sleep(next.getStartTime().toEpochMilli() - current.getStartTime().toEpochMilli());
            sendMessage(current);
        }


        logger.info("Complete - Load all NetFlow Model into Memory: " + frames.size());
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


    private NetFlowFrame getNetFlowFrameFromLine(String[] netFlowLine) {
        return new NetFlowFrame(
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
                LocalDateTime.parse(netFlowLine[12], DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.nnnnnn")).atZone(ZoneId.of("UTC")).toInstant(),
                LocalDateTime.parse(netFlowLine[13], DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.nnnnnn")).atZone(ZoneId.of("UTC")).toInstant(),
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
