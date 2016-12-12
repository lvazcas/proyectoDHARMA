package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * This class allows to get the required configuration parameters to run the
 * evaluationSystemExecutor package.
 *
 * @author UPM (member of DHARMA Development Team)(http://dharma.inf.um.es)
 * @version 1.0
 */
public class DharmaProperties {

    private static Properties dharmaproperties;

    private static String DHARMA_PROPERTIES_FILE;

    private static final String DHARMA_PATH_PROP = "dharma.uri";
    private static final String BAG_VISUALIZATOR_PATH_PROP = "bag.visualizator.uri";
    private static final String JSON_PATH_PROP = "json.uri";
    private static final String EVENT_LOG_PATH_PROP = "event.log.uri";
    private static final String DATASET_PATH_PROP = "dataset.uri";
    private static final String DATASET_TIMESTAMPS_PROP = "dataset.timestamps";
    private static final String TIMESTAMP_REF_PROP = "timestamp.ref";
    private static final String ACTIVE_PERIODS_PROP = "active.periods";
    private static final String ANOMALY_THRESHOLD_PROP = "anomaly.threshold";
    private static final String SYSLOG_PERIOD_PROP = "syslog.period";

    //Valores de los parametros del fichero properties
    private static String DHARMA_PATH_VALUE;
    private static String BAG_VISUALIZATOR_PATH_VALUE;
    private static String JSON_PATH_VALUE;
    private static String EVENT_LOG_PATH_VALUE;
    private static String DATASET_PATH_VALUE;
    private static String DATASET_TIMESTAMPS_VALUE;
    private static String TIMESTAMP_REF_VALUE;
    private static String ACTIVE_PERIODS_VALUE;
    private static String ANOMALY_THRESHOLD_VALUE;
    private static String SYSLOG_PERIOD_VALUE;

    public DharmaProperties() {
        dharmaproperties = new Properties();
        InputStream is = null;
        try {
            String configFile = "dharma.conf";
            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            DHARMA_PROPERTIES_FILE = (new File(path).getParentFile().getPath() + File.separator + configFile).toString();
            File f = new File(DHARMA_PROPERTIES_FILE);
            is = new FileInputStream(f);
            dharmaproperties.load(is);
            DHARMA_PATH_VALUE = dharmaproperties.getProperty(DHARMA_PATH_PROP);
            BAG_VISUALIZATOR_PATH_VALUE = dharmaproperties.getProperty(BAG_VISUALIZATOR_PATH_PROP);
            JSON_PATH_VALUE = dharmaproperties.getProperty(JSON_PATH_PROP);
            EVENT_LOG_PATH_VALUE = dharmaproperties.getProperty(EVENT_LOG_PATH_PROP);
            DATASET_PATH_VALUE = dharmaproperties.getProperty(DATASET_PATH_PROP);
            DATASET_TIMESTAMPS_VALUE = dharmaproperties.getProperty(DATASET_TIMESTAMPS_PROP);
            TIMESTAMP_REF_VALUE = dharmaproperties.getProperty(TIMESTAMP_REF_PROP);
            ACTIVE_PERIODS_VALUE = dharmaproperties.getProperty(ACTIVE_PERIODS_PROP);
            ANOMALY_THRESHOLD_VALUE = dharmaproperties.getProperty(ANOMALY_THRESHOLD_PROP);
            SYSLOG_PERIOD_VALUE = dharmaproperties.getProperty(SYSLOG_PERIOD_PROP);
            
        } catch (IOException e) {
            throw new RuntimeException("Could not read config module config. (File: " + DHARMA_PROPERTIES_FILE + ")", e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public String getDharmaPathValue() {
        return DHARMA_PATH_VALUE;
    }

    public String getBagVisualizatorPathValue() {
        return BAG_VISUALIZATOR_PATH_VALUE;
    }

    public String getJSONPathValue() {
        return JSON_PATH_VALUE;
    }

    public String getEventLogPathValue() {
        return EVENT_LOG_PATH_VALUE;
    }

    public String getDatasetPathValue() {
        return DATASET_PATH_VALUE;
    }

    public String getDatasetTimestampsValue() {
        return DATASET_TIMESTAMPS_VALUE;
    }

    public String getTimestampReferenceValue() {
        return TIMESTAMP_REF_VALUE;
    }

    public String getActivePeriodsValue() {
        return ACTIVE_PERIODS_VALUE;
    }

    public String getAnomalyThresholdValue() {
        return ANOMALY_THRESHOLD_VALUE;
    }
    
    public String getSyslogPeriodValue() {
        return SYSLOG_PERIOD_VALUE;
    }
}
