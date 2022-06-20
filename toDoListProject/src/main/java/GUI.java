import javax.swing.*;
import java.awt.*;

public class GUI extends GUITask {
    
    public GUI(){
        JFrame frame = new JFrame(); //ogólne dane ramki apki
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("To Do List App");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Pole nazwy listy
        JTextField title = new JTextField("Wpisz nazwę swojej listy");
        title.setHorizontalAlignment(JTextField.CENTER);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(title, constraints);

        //Add Button
        JButton addTask = new JButton("Dodaj zadanie");
        addTask.setHorizontalAlignment(JButton.CENTER);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(addTask, constraints);

        //Save Button
        JButton saveFile = new JButton("Zapisz listę");
        saveFile.setHorizontalAlignment(JButton.CENTER);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(saveFile, constraints);



        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); //otwieranie na środku ekranu
        frame.setVisible(true);

    }
    public static void main(String[] args){
        new GUI();
    }

}
