package main.frame;

import main.constants.Constants;
import main.dto.Root;
import main.util.FileReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ButtonPanel extends JPanel {
    JButton calculateBtn;

    JButton aboutProgramBtn;

    JButton instructionBtn;
    Frame frame;
    GraphTableModel tableModel;
    JTable table;

    JButton addColumn;
    JButton removeColumn;

    JButton addNode;

    JButton removeNode;

    JPanel textPanel;

    JPanel buttonPanel;

    JButton saveToFile;

    public ButtonPanel(Frame frame){
        this.frame = frame;
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createButtonPanel();
        tableModel = new GraphTableModel(frame.graph.getRoots(), frame);
        table = new JTable(tableModel);
        add(table);
        add(textPanel);
    }
    public void createButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        calculateBtn = new JButton("Отримати найкоротший шлях");
        buttonPanel.add(calculateBtn);
        addColumn = new JButton("Додати дугу");
        buttonPanel.add(addColumn);
        addColumn.addActionListener(evt -> {
            tableModel.addRootToList(new Root(0,0,0));
            tableModel.fireTableStructureChanged();
            tableModel.fireTableDataChanged();
            table.revalidate();
            table.repaint();
            frame.revalidate();
            frame.repaint();
        });
        removeColumn = new JButton("Видалити дугу");
        buttonPanel.add(removeColumn);
        removeColumn.addActionListener(evt -> {
            tableModel.removeRootFromList(table.getSelectedColumn());
            tableModel.fireTableStructureChanged();
            tableModel.fireTableDataChanged();
            table.revalidate();
            table.repaint();
            frame.revalidate();
            frame.repaint();
        });

        addNode = new JButton("Додати вершину");
        addNode.addActionListener(evt -> {
            Constants.NODE_NUMBER ++;
            frame.revalidate();
            frame.repaint();
        });
        buttonPanel.add(addNode);
        removeNode = new JButton("Видалити вершину");
        removeNode.addActionListener(evt -> {
            Constants.NODE_NUMBER --;
            frame.revalidate();
            frame.repaint();
        });
        buttonPanel.add(removeNode);
        aboutProgramBtn = new JButton("Про програму");
        aboutProgramBtn.addActionListener(evt -> {
            JOptionPane.showMessageDialog(frame,
                    "Програма розроблена у межах дисципліни 'Мережі та потоки' на базі кафедри МПУіК" +
                            "під керівництвом \nдоцента кафедри математичних проблем управління і кібернетики\n" +
                            "Чернівецького Національного Університету імені Юрія Федьковича\n" +
                            "Руснака Миколи Андрійовича студентами групи 441:\n\n" +
                            "Войтоловський Веніамін - тімлід\n" +
                            "Гульпак Антон, Плакош Михайло - розробники\n" +
                            "Мельничук Роман - тестувальник\n" +
                            "Захарків Микола - відповідальний за документацію.\n\n" +
                            "Програма призначена для знаходження найкоротшого шляху методом Мінті.\nПрограма виконує" +
                            "обчислення найкоротшого шляху від початку до кожної вершини, якої можна досягнути.\n\n" +
                            "Для придбання програми звертайтеся по номеру +380665430276");
        });
        buttonPanel.add(aboutProgramBtn);
        instructionBtn = new JButton("Інструкція");
        instructionBtn.addActionListener(evt -> {
            JOptionPane.showMessageDialog(frame,"Програма містить такі функції:\n" +
                    "зчитування файлу\n" +
                    "зображення дуг і обмежень\n" +
                    "створення мережі\n" +
                    "зміна вершини\n" +
                    "зміна ваги ребра\n" +
                    "вивід найкоротшого шляху для кожної з вершин\n\n" +
                    "Використання програми:\n" +
                    "У файл з розширенням .json вписати з якої вершини виходить та входить ребро та вагу ребра\n" +
                    "Приклад:\n" +
                    "\t{\n" +
                    "  \"roots\": [\n" +
                    "\t{\n" +
                    "\t  \"startNode\": 1,\n" +
                    "\t  \"targetNode\": 2,\n" +
                    "\t  \"length\": 4\n" +
                    "\t},\n" +
                    "\t{\n" +
                    "\t  \"startNode\": 1,\n" +
                    "\t  \"targetNode\": 5,\n" +
                    "\t  \"length\": 18\n" +
                    "\t}\n" +
                    "\t]\n" +
                    "Отримаєм таблицю, в яку при потребі зможемо вносити зміни а також малюнок графа.\n" +
                    "При натисканні кнопки 'Отримати найкоротший шлях' \nотримаєм інформацію про найкоротший шлях для кожної з вершин.");
        });
        buttonPanel.add(instructionBtn);
        calculateBtn.addActionListener(e -> {
            if(tableModel.containsSameStartTargetNode()){
                JOptionPane.showMessageDialog(frame, "Недопустиме значення!");
            }else{
                frame.calculateButtonPressed();
            }
        });
        saveToFile = new JButton("Зберегти граф");
        saveToFile.addActionListener(evt->{
            try {
                FileReader.saveGraphToJson(frame.graph);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(saveToFile);
        add(buttonPanel);
    }

    public void createShortestPassText(List<String> shortestPath){
        textPanel.removeAll();
        for(String path : shortestPath){
            JLabel pathLabel = new JLabel(path);
            textPanel.add(pathLabel);
        }
        textPanel.revalidate();
        textPanel.repaint();
    }
}
