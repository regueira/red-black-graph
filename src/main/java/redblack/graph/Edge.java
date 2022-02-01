package redblack.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {

    private Integer source;
    private Integer destination;
    private Weight weight;

}
