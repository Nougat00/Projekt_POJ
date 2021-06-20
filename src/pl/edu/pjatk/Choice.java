package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice {

    public static void window(boolean open) {
        JFrame Loged = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        Loged.setSize(500, 350);
        JPanel text = new JPanel(new GridLayout(8,1,5,5));
        JPanel buttons = new JPanel(new GridLayout(1,4,5,5));
        JLabel name = new JLabel("Imie i nazwisko");
        JTextField namebox= new JTextField();
        JLabel date = new JLabel("Data urodzenia");
        JTextField datebox= new JTextField();
        JLabel adres = new JLabel("Adres zamieszkania");
        JTextField adresbox= new JTextField();
        JLabel avatar = new JLabel("Avatar");
        JTextField avatarbox= new JTextField();
        JButton add = new JButton("Dodaj");
        JButton modify = new JButton("Zmodyfikuj");
        JButton delete = new JButton("Usuń");
        JButton search = new JButton("Szukaj");
        text.add(name);
        text.add(namebox);
        text.add(date);
        text.add(datebox);
        text.add(adres);
        text.add(adresbox);
        text.add(avatar);
        text.add(avatarbox);
        buttons.add(add);
        buttons.add(modify);
        buttons.add(delete);
        buttons.add(search);
        JButton logout = new JButton("Wyloguj");
        main.add(logout, BorderLayout.SOUTH);
        main.add(text, BorderLayout.NORTH);
        main.add(buttons, BorderLayout.CENTER);
        Loged.add(main);
        if(open)
        {
            Loged.setVisible(true);
        }
        else
        {
            Loged.dispose();
        }
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loged.dispose();
                WelcomeScr.window(true);
            }
        });

    }

}
