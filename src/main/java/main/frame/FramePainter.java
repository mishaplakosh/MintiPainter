package main.frame;

import main.constants.Constants;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public class FramePainter {

    private static final Random random = new Random();

    public static void drawNode(Graphics g, Rectangle node, int index) {

        // draw outline
        g.setColor(Color.BLACK);
        g.fillOval(node.x - 2, node.y - 2, node.width + 4, node.height + 4);

        // draw node
        g.setColor(getRandomColor());
        g.fillOval(node.x, node.y, node.width, node.height);

        // draw node index
        var font = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(String.valueOf(index + 1), node.x + Constants.NODE_WIDTH / 2 - 5, node.y + Constants.NODE_WIDTH / 2 + 6);
    }

    public static void paintWeight(Graphics g, List<String> minty, int index, Rectangle rect){
        if(minty != null){
            if(minty.get(index) != null){
                String wholeString = minty.get(index);
                StringBuilder sb=new StringBuilder(wholeString);
                sb.reverse();
                int indexOfFirstWhitespace = sb.indexOf(" ");
                String weight = wholeString.substring(wholeString.length() - indexOfFirstWhitespace);
                g.drawString(weight, rect.x + Constants.NODE_WIDTH / 2 - 5, rect.y + Constants.NODE_WIDTH / 2 + 6);
            }
        }
    }

    public static void drawArrow(Graphics g, Rectangle startNode, Rectangle targetNode) {

        // draw arrow base
        var x1 = startNode.x; // center x of first rectangle
        var y1 = startNode.y; // center y of first rectangle
        var x2 = targetNode.x; // center x of second rectangle
        var y2 = targetNode.y; // center y of second rectangle

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);

        // draw arrow head
        var angle = Math.atan2(y2 - y1, x2 - x1);

        var arrowLength = 10;
        var arrowWidth = 5;

        var arrowX = (int) (x2 - arrowLength * Math.cos(angle));
        var arrowY = (int) (y2 - arrowLength * Math.sin(angle));

        int[] xPoints = {arrowX, (int) (arrowX + arrowWidth * Math.cos(angle + Math.PI / 2)), x2, (int) (arrowX + arrowWidth * Math.cos(angle - Math.PI / 2))};
        int[] yPoints = {arrowY, (int) (arrowY + arrowWidth * Math.sin(angle + Math.PI / 2)), y2, (int) (arrowY + arrowWidth * Math.sin(angle - Math.PI / 2))};

        g.fillPolygon(xPoints, yPoints, 4);
    }

    private static Color getRandomColor() {

        var r = random.nextFloat();
        var g = random.nextFloat();
        var b = random.nextFloat();
        return new Color(r, g, b);
    }
}
