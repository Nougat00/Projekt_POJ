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
