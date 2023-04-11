package main.frame;

import main.algoritm.Minti;
import main.algoritm.MintiAlgorithm;
import main.constants.Constants;
import main.dto.Graph;

import main.dto.MintyResult;
import main.dto.Root;
import main.util.FileReader;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Frame extends JFrame {

    private List<Root> roots;
    Graph graph;
    ButtonPanel buttonPanel;

    boolean nodesWasPainted = false;

    List<Rectangle> nodes;

    List<String> minty;

    private final Random random = new Random();

    public Frame() {
        super("Test");
        this.graph = FileReader.readGraphFromJson("input.json");
        this.roots = graph.getRoots();
        graph.getRoots().forEach(node -> System.out.println(node.getStartNode() + " | " + node.getTargetNode() + " | " + node.getLength()));
        buttonPanel = new ButtonPanel(this);
        add(buttonPanel);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

//        if(!nodesWasPainted){
//            return;
//        }

        nodes = new ArrayList<Rectangle>();

        IntStream.range(0, Constants.NODE_NUMBER).forEach(index -> {

            var intersects = false;
            Rectangle node;

            do {
                intersects = false;

                var x = random.nextInt(Constants.MAX_X - Constants.MIN_X) + Constants.MIN_X;
                var y = random.nextInt(Constants.MAX_Y - Constants.MIN_Y) + Constants.MIN_Y;

                node = new Rectangle(x, y, Constants.NODE_WIDTH, Constants.NODE_HEIGHT);

                for (Rectangle r : nodes) {
                    if (r.intersects(node)) {
                        intersects = true;
                        break;
                    }
                }
            } while (intersects);

            nodes.add(node);

            FramePainter.drawNode(g, node, index);
        });
        ArrayList<MintyResult> mintyShortestPath = (ArrayList) Minti.getMintyShortestPath(roots);
        roots.forEach(root -> FramePainter.drawArrow(g, nodes.get(root.getStartNode() - 1), nodes.get(root.getTargetNode() - 1)));
    }

    private Color getRandomColor() {

        var r = random.nextFloat();
        var g = random.nextFloat();
        var b = random.nextFloat();
        return new Color(r, g, b);
    }

    public void calculateButtonPressed(){
        minty = Minti.runMinty(graph.getRoots());
        buttonPanel.createShortestPassText(minty);
    }
}
