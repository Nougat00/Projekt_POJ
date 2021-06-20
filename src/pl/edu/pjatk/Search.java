package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search {
    public static void window(boolean open, String user)
    {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        frame.setSize(500, 180);
        JPanel buttons = new JPanel(new GridLayout(1,4,5,5));
        JButton name = new JButton("Imie i nazwisko");
        JButton date = new JButton("Data urodzenia");
        JButton adres = new JButton("Adres");
        JButton back = new JButton("Cofnij");
        buttons.add(name);
        buttons.add(date);
        buttons.add(adres);
        buttons.add(back);
        JButton logout = new JButton("Wyloguj");
        JLabel notification=new JLabel("Zastosuj wyszukiwanie po:");
        main.add(notification, BorderLayout.NORTH);
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
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Choice.window(true, user);
            }
        });
    }
}
