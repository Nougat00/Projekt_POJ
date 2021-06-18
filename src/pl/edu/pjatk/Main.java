package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Organizer");
        frame.setSize(288, 350);
        JPanel firstScreen = new JPanel(new GridLayout(5,1,5,5));
        JTextField login=new JTextField();
        JTextField pass=new JTextField();
        JButton log = new JButton("Login");
        JButton exit=new JButton("EXIT");
        JLabel mess=new JLabel();
        firstScreen.add(login);
        firstScreen.add(pass);
        firstScreen.add(mess);
        firstScreen.add(log);
        firstScreen.add(exit);
        frame.add(firstScreen);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Login(login.getText(),pass.getText()))
                {
                    mess.setText("Git");
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
                    main.add(text, BorderLayout.NORTH);
                    main.add(buttons, BorderLayout.CENTER);
                    Loged.add(main);
                    Loged.setVisible(true);
                    frame.setVisible(false);
                }
                else
                {
                    mess.setText("Złe hasło lub login");
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
    private static boolean Login(String login, String pass)
    {
        try (Scanner in=new Scanner(new File("C:\\Users\\barte\\Documents\\PJAKT\\OneDrive - Polsko-Japońska Akademia Technik Komputerowych\\POJ\\Projekt_POJ\\src\\login.txt")))
        {
            String[] fromFile = in.nextLine().split(" ");
            if(fromFile[0].equals(login))
            {
                if(fromFile[1].equals(pass))
                {
                    return true;
                }
                else return false;
            }
            else return false;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
