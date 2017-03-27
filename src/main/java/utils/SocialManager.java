package utils;

import communications.SensorCollector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocialManager {

    private final String path;
    private Double risk;
    private static final DharmaProperties props = new DharmaProperties();
    private HashMap<String, Object> value = new HashMap<>();
    private HashMap<String, HashMap<String, Object>> output = new HashMap<>();

    public SocialManager(String path) {
        this.path = path;
        risk = 0.0;
        process();
    }

    private void process() {

        try {
            Double content = Double.parseDouble(new String(Files.readAllBytes(Paths.get(path))));
            if (content != risk) {
                if (content >= Double.parseDouble(props.getSocialThresholdValue())) {
                    value.put("Anomaly", content);
                }
                risk = content;
                
                value.put("Time", System.currentTimeMillis());
                
                output.put("Social", value);
                SensorCollector.receiveNewData(output);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocialManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
