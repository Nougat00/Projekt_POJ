package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice {

    public static void window(boolean open, String user) {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        frame.setSize(500, 180);
        JPanel buttons = new JPanel(new GridLayout(1,4,5,5));
        JButton add = new JButton("Dodaj");
        JButton modify = new JButton("Zmodyfikuj");
        JButton delete = new JButton("Usuń");
        JButton search = new JButton("Szukaj");
        buttons.add(add);
        buttons.add(search);
        buttons.add(modify);
        buttons.add(delete);
        JButton logout = new JButton("Wyloguj");
        main.add(logout, BorderLayout.SOUTH);
        main.add(buttons, BorderLayout.CENTER);
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


    }

}
