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
                JLabel result = new JLabel();
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
                        if(searchResult(user, text.getText()).size()>0){
                            for (int i = 0; i <searchResult(user, text.getText()).size(); i++) {
                                result.setText(result.getText()+String.valueOf(i)+". ");
                                for (int j = 0; j < searchResult(user, text.getText()).get(i).size(); j++) {
                                    result.setText(result.getText()+" "+searchResult(user,text.getText()).get(i).get(j));
                                }
                                result.setText("\n");
                            }
                        }
                        else result.setText("Nie znaleziono nikogo");
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
        Vector<Vector<String>> result = null;
        try (Scanner in = new Scanner(new File("./data.txt"))) {
            Vector<String> str = null;
            while (in.hasNext()) {
                String[] fromFile = in.nextLine().split(",");
                if (fromFile[0].equals(user)) {
                    for (int i = 1; i < fromFile.length; i++) {
                        System.out.println(fromFile[i]);
                        str.add(fromFile[i]);
                    }
                    result.add(str);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}

