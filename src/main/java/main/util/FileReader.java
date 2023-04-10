package main.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import main.dto.Graph;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class FileReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Graph readGraphFromJson(String fileName) {

        var file = new File(fileName);
        var graph = new Graph();

        try {
            graph = objectMapper.readValue(file, Graph.class);
        } catch (IOException ioException) {
            System.err.println("Failed to read file: " + fileName);
            System.exit(1);
        }

        return graph;
    }
}
