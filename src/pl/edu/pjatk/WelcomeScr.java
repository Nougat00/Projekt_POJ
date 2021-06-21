package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WelcomeScr {

    private static Object args;

    public static void window(boolean open)
    {
        JFrame frame = new JFrame("Organizer");
        frame.setSize(288, 350);
        JPanel firstScreen = new JPanel(new GridLayout(5,1,5,5));
        JTextField login=new JTextField();
        JTextField pass=new JTextField();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
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
                if(login(login.getText(),pass.getText()))
                {
                    mess.setText("Git");
                    frame.setVisible(false);
                    Choice.window(true, login.getText());
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

    protected static boolean login(String login, String pass)
    {
        try (Scanner in=new Scanner(new File("C:\\Users\\barte\\Documents\\PJAKT\\OneDrive - Polsko-Japońska Akademia Technik Komputerowych\\POJ\\Projekt_POJ\\src\\login.txt")))
        {
            String[] fromFile = in.nextLine().split(" ");
            boolean flag=false;

            for (int i = 0; i < fromFile.length; i=i+2) {
                if(fromFile[i].equals(login))
                {
                    if(fromFile[i+1].equals(pass))
                    {
                        return true;
                    }
                    else flag=false;
                }
                else flag=false;
            }
            return flag;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
