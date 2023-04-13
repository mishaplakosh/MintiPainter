package main.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import main.constants.Constants;
import main.dto.Graph;
import main.dto.Root;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class FileReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Graph readGraphFromJson(String fileName) {

        var file = new File(fileName);
        var graph = new Graph();

        try {
            graph = objectMapper.readValue(file, Graph.class);
            int maxValue = graph.getRoots().stream().map(Root::getTargetNode).mapToInt(Integer::intValue).max().getAsInt();
            Constants.NODE_NUMBER = maxValue;
        } catch (IOException ioException) {
            System.err.println("Failed to read file: " + fileName);
            System.exit(1);
        }

        return graph;
    }

    public static void saveGraphToJson(Graph graph) throws IOException {
        List<JSONObject> jsonObject = new ArrayList<JSONObject>();
        for(int i=0; i<graph.getRoots().size(); i++){
            Root root = graph.getRoots().get(i);
            JSONObject object = new JSONObject();
            object.put("startNode", root.getStartNode());
            object.put("targetNode", root.getTargetNode());
            object.put("length", root.getLength());
            jsonObject.add(object);
        }

        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);
        if(r == JFileChooser.APPROVE_OPTION){
            PrintWriter out = new PrintWriter(new FileWriter(j.getSelectedFile().getAbsolutePath() + ".json"));
            out.write("{\"roots\": [");
            for(int i=0; i<jsonObject.size(); i++){
                out.write(jsonObject.get(i).toString());
                if(i!=jsonObject.size()-1){
                    out.write(",");
                }
                out.write("\n");
            }
//            for(JSONObject object : jsonObject){
//                out.write(object.toString() + "," + "\n");
//            }
            out.write("]\n" +
                    "}");
            out.flush();
            out.close();
        }
    }
}
