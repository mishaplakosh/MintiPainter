package main.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Root {

    private int startNode;
    private int targetNode;
    private int length;
}
