package main.frame;

import main.constants.Constants;
import main.dto.Root;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GraphTableModel extends AbstractTableModel {
    List<Root> rootsList;

    Frame frame;

    public GraphTableModel(List<Root> rootsList, Frame frame){
        this.rootsList = rootsList;
        this.frame = frame;
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return rootsList.size();
    }

    public String getStartTargetNodes(int column){
        return rootsList.get(column).getStartNode() + "-" + rootsList.get(column).getTargetNode();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowIndex == 0 ? getStartTargetNodes(columnIndex) : rootsList.get(columnIndex).getLength();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int oldStartNode = rootsList.get(columnIndex).getStartNode();
        int oldEndNode = rootsList.get(columnIndex).getTargetNode();
        int oldLength = rootsList.get(columnIndex).getLength();
        try{
            String toChangeVal = aValue.toString();
            if(rowIndex == 0){
                if(toChangeVal.length() <= 3){
                    int startNode = Integer.parseInt(toChangeVal.substring(0,1));
                    int endNode = Integer.parseInt(toChangeVal.substring(2));
                    if(startNode > 0 || endNode < Constants.MAX_NODE_NUMBER - 1){
                        rootsList.get(columnIndex).setStartNode(startNode);
                        rootsList.get(columnIndex).setTargetNode(endNode);
                    }else{
                        rootsList.get(columnIndex).setStartNode(oldStartNode);
                        rootsList.get(columnIndex).setTargetNode(oldEndNode);
                        JOptionPane.showMessageDialog(frame, "Недопустиме значення!");

                    }
                }else{
                    rootsList.get(columnIndex).setStartNode(oldStartNode);
                    rootsList.get(columnIndex).setTargetNode(oldEndNode);
                    JOptionPane.showMessageDialog(frame, "Недопустиме значення!");
                }

            }else if(rowIndex == 1){
                int length = Integer.parseInt(toChangeVal);
                if(length >= 1){
                    rootsList.get(columnIndex).setLength(length);
                }else{
                    JOptionPane.showMessageDialog(frame, "Недопустиме значення!");
                    rootsList.get(columnIndex).setLength(oldLength);
                }
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Недопустиме значення!");
        }

    }
}
