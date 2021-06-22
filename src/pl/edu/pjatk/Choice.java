package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Choice {
    public static void window(boolean open, String user) {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new GridLayout(3,1));
        frame.setSize(600, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel buttons = new JPanel(new GridLayout(1,4,5,5));
        JButton add = new JButton("Dodaj");
        JButton modify = new JButton("Zmodyfikuj");
        JButton delete = new JButton("Usuń");
        JButton search = new JButton("Szukaj");
        JButton showAvatar= new JButton("Pokaż avatar");
        JTextArea result = new JTextArea();
        result.setEditable(false);
        buttons.add(add);
        buttons.add(search);
        buttons.add(modify);
        buttons.add(delete);
        buttons.add(showAvatar);
        JButton logout = new JButton("Wyloguj");
        main.add(result);
        main.add(buttons);
        main.add(logout);
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(open)
        {
            frame.setVisible(true);
        }
        else
        {
            frame.dispose();
        }
        Vector<Vector<String>> result_tmp=new Vector<>();
        result_tmp=searchSystem(user);
        for (int i = 0; i < result_tmp.size(); i++) {
            result.setText(result.getText()+String.valueOf(i+1));
            for (int j = 0; j < result_tmp.get(i).size(); j++) {
                result.setText(result.getText()+" "+result_tmp.get(i).get(j));
            }
            result.setText(result.getText()+"\n");
        }
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                WelcomeScr.window(true);
            }
        });
       add.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Add.window(true, user);
               frame.dispose();
           }
       });
       search.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Search.window(true, user);
               frame.dispose();
           }
       });
       modify.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Modify.window(true, user);
               frame.dispose();
           }
       });
       delete.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Delete.window(true,user);
               frame.dispose();
           }
       });
       showAvatar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Avatar.window(true, user);
           }
       });


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

