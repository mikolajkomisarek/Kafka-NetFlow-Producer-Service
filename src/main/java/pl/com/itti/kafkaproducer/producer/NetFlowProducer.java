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
    private static final String TOPIC = "netflow-czar";

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


        for (int x = 0; x < frames.size(); x++) {
            var current = frames.get(x);
            var next = frames.get(x + 1);

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
                Long.getLong(netFlowLine[3]),
                Long.getLong(netFlowLine[4]),
                netFlowLine[5],
                Long.getLong(netFlowLine[6]),
                Long.getLong(netFlowLine[7]),
                netFlowLine[8],
                netFlowLine[9],
                Long.getLong(netFlowLine[10]),
                Long.getLong(netFlowLine[11]),
                LocalDateTime.parse(netFlowLine[12], DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.nnnnnn")).atZone(ZoneId.of("UTC")).toInstant(),
                LocalDateTime.parse(netFlowLine[13], DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.nnnnnn")).atZone(ZoneId.of("UTC")).toInstant(),
                Long.getLong(netFlowLine[14]),
                Long.getLong(netFlowLine[15]),
                Double.parseDouble(netFlowLine[16]),
                Double.parseDouble(netFlowLine[17]),
                Double.parseDouble(netFlowLine[18]),
                Long.getLong(netFlowLine[19]),
                Long.getLong(netFlowLine[20]),
                Long.getLong(netFlowLine[21]),
                Long.getLong(netFlowLine[22]),
                Long.getLong(netFlowLine[23]),
                Long.getLong(netFlowLine[24]),
                Double.parseDouble(netFlowLine[25]),
                Long.getLong(netFlowLine[26]),
                Long.getLong(netFlowLine[27]),
                Long.getLong(netFlowLine[28]),
                Double.parseDouble(netFlowLine[29]),
                Double.parseDouble(netFlowLine[30]),
                Double.parseDouble(netFlowLine[31]),
                netFlowLine[32]
        );
    }
}
