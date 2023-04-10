package main.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonPanel extends JPanel {
    JButton calculateBtn;

    JButton aboutProgramBtn;

    JButton instructionBtn;
    Frame frame;
    GraphTableModel tableModel;
    JTable table;

    JPanel textPanel;

    public ButtonPanel(Frame frame){
        this.frame = frame;
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        createButtonPanel();
        tableModel = new GraphTableModel(frame.graph.getRoots(), frame);
        table = new JTable(tableModel);
        add(table);
        add(textPanel);
    }
    public void createButtonPanel(){
        calculateBtn = new JButton("Отримати найкоротший шлях");
        add(calculateBtn);
        aboutProgramBtn = new JButton("Про програму");
        aboutProgramBtn.addActionListener(evt -> {
            JOptionPane.showMessageDialog(frame,
                    "Програма розроблена у межах дисципліни 'Мережі та потоки' на базі кафедри МПУіК" +
                            "під керівництвом \nдоцента кафедри математичних проблем управління і кібернетики" +
                            "Руснака Миколи Андрійовича студентами групи 441:\n\n" +
                            "Войтоловський Веніамін - тімлід\n" +
                            "Гульпак Антон, Плакош Михайло - розробники\n" +
                            "Мельничук Роман - тестувальник\n" +
                            "Захарків Микола - відповідальний за документацію.\n\n" +
                            "Програма призначена для знаходження найкоротшого шляху методом Мінті.\nПрограма виконує" +
                            "обчислення найкоротшого шляху від початку до кожної вершини, якої можна досягнути.\n\n" +
                            "Для придбання програми звертайтеся по номеру +380665430276");
        });
        add(aboutProgramBtn);
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
        add(instructionBtn);
        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.calculateButtonPressed();
            }
        });
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
