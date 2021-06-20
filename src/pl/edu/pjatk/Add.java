package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add {
    public static void window(boolean open) {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        frame.setSize(500, 350);
        JPanel text = new JPanel(new GridLayout(8, 1, 5, 5));
        JPanel buttons = new JPanel(new GridLayout(1, 4, 5, 5));
        JLabel name = new JLabel("Imie i nazwisko");
        JTextField namebox = new JTextField();
        JLabel date = new JLabel("Data urodzenia");
        JTextField datebox = new JTextField();
        JLabel adres = new JLabel("Adres zamieszkania");
        JTextField adresbox = new JTextField();
        JLabel avatar = new JLabel("Avatar");
        JTextField avatarbox = new JTextField();
        JLabel message = new JLabel();
        JButton add = new JButton("Dodaj");
        JButton back = new JButton("Cofnij");
        text.add(name);
        text.add(namebox);
        text.add(date);
        text.add(datebox);
        text.add(adres);
        text.add(adresbox);
        text.add(avatar);
        text.add(avatarbox);
        buttons.add(add);
        buttons.add(back);
        JButton logout = new JButton("Wyloguj");
        main.add(message, BorderLayout.NORTH);
        main.add(logout, BorderLayout.SOUTH);
        main.add(text, BorderLayout.NORTH);
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
                if(!namebox.getText().equals("") && !datebox.getText().equals("") && !adresbox.getText().equals("") && !avatarbox.getText().equals("")) {

                }
                else {
                    message.setText("Czegoś brakuje gościu...");
                }
            }
        });
    }
}
