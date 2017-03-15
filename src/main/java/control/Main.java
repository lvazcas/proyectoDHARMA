package control;

import communications.SECEventReceiver;
import core.Graph;
import communications.LogReceiver;
import communications.SensorCollector;
import communications.SyslogCreator;
import utils.ActivePeriods;
import utils.DharmaProperties;

/**
 * This class starts the DHARMA system
 *
 * @author UPM (member of DHARMA Development Team) (http://dharma.inf.um.es)
 * @version 1.0
 */
public class Main {

    //private static SECEventReceiver eventReceiver;
    private static SensorConfigurator sensorConfigurator;
    private static DataCataloger dataCataloger;
    private static LogReceiver logReceiver;
    private static Dharma dharma;
    private static MarkovController markovController;
    private static SensorCollector sensorCollector;
    private static final DharmaProperties props = new DharmaProperties();

    public static void main(String[] args) {

        Graph.exportCleanJSON();
        markovController = new MarkovController();
        sensorCollector = new SensorCollector(props.getAnomalyPathsValue());
        //eventReceiver = new SECEventReceiver();
        //new Thread(eventReceiver).start();

        markovController.parse("IDAtaque=1;TipoAtaque=Denegacion de Servicio;Nodos=Intento de intrusion,Buffer Overflow,Denegacion de Servicio;Estado=Buffer Overflow;PEstado=0.9998914037855784;PFinal=0.6231988799803523;Risk=2;Markovid=1");

        sensorConfigurator = new SensorConfigurator();
        //dataCataloger = new DataCataloger();

        logReceiver = new LogReceiver(5000, "127.0.0.1");

        //ActivePeriods.create();
        //new Thread(dataCataloger).start();
        new Thread(sensorConfigurator).start();
        logReceiver.start();

        /*SyslogCreator syslogCreator = new SyslogCreator();
        syslogCreator.put("---> Network ANOMALY of: 45/100");
        syslogCreator.put("10/18/2016-10:53:43.274321  [**] [1:2010937:2] ET POLICY "
                + "Suspicious inbound to mySQL port 3306 [**] [Classification: Potentially Bad Traffic] "
                + "[Priority: 2] {TCP} 10.0.0.19:33167 -> 10.0.0.18:3306");
        
        syslogCreator.put("10/18/2016-10:53:44.274321  [**] [1:2010937:2] ET POLICY "
                + "Suspicious inbound to hola port 3307 [**] [Classification: Potentially Bad Traffic] "
                + "[Priority: 2] {TCP} 10.0.0.19:33143 -> 10.0.0.18:3307");
        
        
        syslogCreator.put("10/18/2017-10:53:44.274321  [**] [1:2010937:2] ET POLICY "
                + "Suspicious inbound to asdf port 1234 [**] [Classification: Potentially Bad Traffic] "
                + "[Priority: 2] {TCP} 10.0.0.19:8080 -> 10.0.0.18:1234");
        
        syslogCreator.put("Anomalía WiFi en biblioteca;\"IP\":\"192.168.1.1\";\"MAC\":\"AA:DD:33:11:01\"");*/
    }
}
