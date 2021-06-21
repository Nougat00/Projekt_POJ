package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Search {
    public static void window(boolean open, String user) {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        frame.setSize(500, 180);
        JPanel buttons = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton name = new JButton("Imie i nazwisko");
        JButton date = new JButton("Data urodzenia");
        JButton adres = new JButton("Adres");
        JButton back = new JButton("Cofnij");
        buttons.add(name);
        buttons.add(date);
        buttons.add(adres);
        buttons.add(back);
        JButton logout = new JButton("Wyloguj");
        JLabel notification = new JLabel("Zastosuj wyszukiwanie po:");
        main.add(notification, BorderLayout.NORTH);
        main.add(logout, BorderLayout.SOUTH);
        main.add(buttons, BorderLayout.CENTER);
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (open) {
            frame.setVisible(true);
        } else {
            frame.dispose();
        }
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame nameSearch = new JFrame("Organizer");
                nameSearch.setSize(500, 180);
                JPanel main = new JPanel(new BorderLayout());
                JButton logout = new JButton("Wyloguj");
                JButton back = new JButton("Cofnij");
                JButton search = new JButton("Szukaj");
                JTextField text = new JTextField();
                JTextArea result = new JTextArea();
                result.setEditable(false);
                JPanel upPanel = new JPanel(new GridLayout(1, 2));
                JPanel downPanel = new JPanel(new GridLayout(1, 2));
                upPanel.add(text);
                upPanel.add(search);
                downPanel.add(back);
                downPanel.add(logout);
                main.add(upPanel, BorderLayout.NORTH);
                main.add(result, BorderLayout.CENTER);
                main.add(downPanel, BorderLayout.SOUTH);
                nameSearch.add(main);
                nameSearch.setVisible(true);
                nameSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<Vector<String>> result_tmp=new Vector<>();
                        if(searchResult(user, text.getText()).size()>0){
                            result.setText("");
                            result_tmp=searchResult(user, text.getText());
                            int record=0;
                            for (int i = 0; i <result_tmp.size(); i++) {
                                if(!result_tmp.get(i).isEmpty()) {
                                    record++;
                                    result.setText(result.getText() + String.valueOf(record) + ". ");
                                    for (int j = 0; j < result_tmp.get(i).size(); j++) {
                                        result.setText(result.getText() + " " + result_tmp.get(i).get(j));
                                    }
                                    result.setText(result.getText() + "\n");
                                }
                            }
                        }
                        else {
                            result.setText("");
                            result.setText("Nie znaleziono nikogo");
                        }
                    }
                });
                logout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nameSearch.dispose();
                        WelcomeScr.window(true);
                    }
                });
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        Choice.window(true, user);
                    }
                });


            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                WelcomeScr.window(true);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Choice.window(true, user);
            }
        });
    }

    private static Vector<Vector<String>> searchResult(String user, String name) {
        Vector<Vector<String>> result = new Vector<>();
        if(!name.equals("")) {
            try (Scanner in = new Scanner(new File("./data.txt"))) {
                while (in.hasNextLine()) {
                    Vector<String> str = new Vector<>();
                    String tmp = in.nextLine();
                    String[] fromFile = tmp.split(",");
                    if (fromFile[0].equals(user)) {
                        if (fromFile[1].equals(name)) {
                            str.addElement(fromFile[1]);
                            str.addElement(fromFile[2]);
                            str.addElement(fromFile[3]);
                            str.addElement(fromFile[4]);
                        }
                        result.add(str);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return result;
        }
        else return result;
    }
}

