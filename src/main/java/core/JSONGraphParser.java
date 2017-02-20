package core;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.util.HashSet;
import smile.Network;
import smile.SMILEException;

/**
 * This class makes a parsing of the JSON exported by the d3js graph generator
 *
 * @author UPM (member of DHARMA Development Team) (http://dharma.inf.um.es)
 * @version 1.0
 */
public class JSONGraphParser {

    public JSONGraphParser() {
    }

    /**
     * Parsea el JSON para obtener los nodos y enlaces
     *
     * @param json texto a parsear
     * @return nodos y enlaces
     */
    public HashMap<String, Object> parseGraph(String json) {
        Network net = new Network();

        HashMap<String, String> exportedNodes = new HashMap<>();
        HashSet<String[]> exportedEdges = new HashSet<>();
        HashMap<String, Object> parsedGraph = new HashMap<>();

        Map jsonMap = new Gson().fromJson(json, Map.class);
        String[] nodes = jsonMap.get("nodes").toString().replace("[", " ").replace("}]", "").replace(" {", "").split("},");
        String[] edges = jsonMap.get("edges").toString().replace("[", " ").replace("}]", "").replace(" {", "").split("},");

        for (String node : nodes) {

            String name = "";
            String id = "";

            for (String nodeElement : node.split(", ")) {
                String[] item = nodeElement.split("=");
                if ("title".equals(item[0])) {
                    name = item[1];
                } else if ("id".equals(item[0])) {
                    id = item[1];
                }
            }

            exportedNodes.put(id, name);
            net.addNode(Network.NodeType.Cpt, adjustName(name));
            net.setOutcomeId(adjustName(name), 0, "False");
            net.setOutcomeId(adjustName(name), 1, "True");

        }

        //System.out.println("Grafo generado: ");
        for (String edge : edges) {

            String source = "";
            String target = "";

            for (String edgeElement : edge.split(", ")) {
                String[] item = edgeElement.split("=");
                if ("source".equals(item[0])) {
                    source = exportedNodes.get(item[1]);
                } else if ("target".equals(item[0])) {
                    target = exportedNodes.get(item[1]);
                }
            }

            exportedEdges.add(new String[]{source, target});
            net.addArc(adjustName(source), adjustName(target));
        }

        parsedGraph.put("nodes", exportedNodes);
        parsedGraph.put("edges", exportedEdges);

        net.writeFile("bayesNet.xdsl");
        return parsedGraph;

    }

    private String adjustName(String name) {
        if (name.contains(" ")) {
            name = name.replace(" ", "_");
        }
        return name;
    }
}
