package main.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jgrapht.GraphPath;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class MintyResult {
    int sink;
    GraphPath path;
    public List getPath(){
        return path.getVertexList();
    }
}
