package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Avatar {
    public static void window(boolean open, String user)
    {
        JFrame nameSearch = new JFrame("Organizer");
        nameSearch.setSize(500, 180);
        nameSearch.setResizable(false);
        nameSearch.setLocationRelativeTo(null);
        JPanel main = new JPanel(new BorderLayout());
        JButton logout = new JButton("Wyloguj");
        JButton back = new JButton("Cofnij");
        JButton show = new JButton("Modyfikuj");
        JTextField text = new JTextField();
        JTextArea result = new JTextArea();
        result.setEditable(false);
        JPanel upPanel = new JPanel(new GridLayout(1, 2));
        JPanel downPanel = new JPanel(new GridLayout(1, 2));
        upPanel.add(text);
        upPanel.add(show);
        downPanel.add(back);
        downPanel.add(logout);
        main.add(upPanel, BorderLayout.NORTH);
        main.add(result, BorderLayout.CENTER);
        main.add(downPanel, BorderLayout.SOUTH);
        nameSearch.add(main);
        nameSearch.setVisible(true);
        nameSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (searchSystem(user).size() > 0) {
            for (int i = 0; i < searchSystem(user).size(); i++) {
                result.setText(result.getText() + String.valueOf(i + 1));
                for (int j = 0; j < searchSystem(user).get(i).size(); j++) {
                    result.setText(result.getText() + " " + searchSystem(user).get(i).get(j));
                }
                result.setText(result.getText() + "\n");
            }
        }
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText().equals(""))
                {
                    result.setText("Wpisz rekord");
                }
                showImage(user, Integer.parseInt(text.getText()), true);
                nameSearch.dispose();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameSearch.dispose();
                Choice.window(true, user);
            }
        });
    }

    private static void showImage(String user, int record, boolean open)
    {
        JFrame nameSearch = new JFrame("Organizer");
        nameSearch.setSize(500, 180);
        nameSearch.setResizable(false);
        nameSearch.setLocationRelativeTo(null);
        JPanel main = new JPanel(new BorderLayout());
        JFrame image = new JFrame();
        JButton back = new JButton();
        Vector<Vector<String>> result = new Vector<>();
        result=searchSystem(user);
        ImagePanel panel = new ImagePanel(new ImageIcon(result.get(record-1).get(3)).getImage());
        image.getContentPane().add(panel);
        image.setSize(panel.w+15, panel.h+30);
        image.setLocationRelativeTo(null);
        image.setVisible(open);
    }

    private static Vector<Vector<String>> searchSystem(String user) {
        Vector<Vector<String>> result = new Vector<>();
        try (Scanner in = new Scanner(new File("./data.txt"))) {
            while (in.hasNextLine()) {
                Vector<String> str = new Vector<>();
                String tmp = in.nextLine();
                String[] fromFile = tmp.split(",");
                if (fromFile[0].equals(user)) {
                    str.addElement(fromFile[1]);
                    str.addElement(fromFile[2]);
                    str.addElement(fromFile[3]);
                    str.addElement(fromFile[4]);
                    result.add(str);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}

