import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GUI{

    public GUI(){
        JFrame frame = new JFrame(); //ogólne dane ramki apki
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("To Do List App");
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Pole nazwy listy
        JTextField title = new JTextField("Twoja ToDoLista");
        title.setHorizontalAlignment(JTextField.CENTER);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(title, constraints);

        //Add Button
        JButton addTask = new JButton("Dodaj zadanie");
        addTask.setHorizontalAlignment(JButton.CENTER);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(addTask, constraints);

        //Save Button
        JButton saveFile = new JButton("Zapisz listę");
        saveFile.setHorizontalAlignment(JButton.CENTER);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(saveFile, constraints);

        //Lista zadan
        String[] columns = {"Priorytet", "Zrobione?", "Zadanie", "Usunąć?"};
        //dane poczatkowe
        Object[][] data = {
                {1, false, "Wpisz swoje pierwsze zadanie!!!", " " },
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model) {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        //delete button
        table.getColumn("Usunąć?").setCellRenderer(new MyRendererAndEditor(table));
        table.getColumn("Usunąć?").setCellEditor(new MyRendererAndEditor(table));

        //add button funkcja
        addTask.addActionListener(e -> model.addRow(new Object[]{1, false, "Wpisz zadanie", ""}));

        //rozmiary tabeli
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(20);
        table.setRowHeight(20);

        //sortowanie
        table.setAutoCreateRowSorter(true);

        String filename = title.getText();
        //save button funkcja
        saveFile.addActionListener(e -> {
            try{
                String userHomeFolder = System.getProperty("user.home");
                File file = new File(userHomeFolder + "\\Desktop", filename + ".txt");
                if(!file.exists()){
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i = 0; i < table.getRowCount(); i++){
                    for(int j = 0; j < table.getColumnCount()-1; j++){
                        bw.write(table.getModel().getValueAt(i, j)+" ");
                    }
                    bw.write("\n_________\n");
                }
                bw.close();
                fw.close();
                JOptionPane.showMessageDialog(null, "Zapisano!");

            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        //przypominajka pop up
        int delay = 30000;
        ActionListener taskPerformer = evt -> JOptionPane.showMessageDialog(null,"Pamiętaj o swoich zadaniach!" ,"Przypomnienie" ,JOptionPane.PLAIN_MESSAGE);
        new Timer(delay, taskPerformer).start();


        //polozenie todoListy
        JScrollPane scrollPane = new JScrollPane(table);
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(scrollPane, constraints);

        //koncowe fofmatowanie okna
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); //otwieranie na środku ekranu
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new GUI();
    }

}
