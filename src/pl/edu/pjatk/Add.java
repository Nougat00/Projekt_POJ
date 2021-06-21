package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Add {
    public static void window(boolean open, String user) {
        JFrame frame = new JFrame("Organizer");
        JPanel main = new JPanel(new BorderLayout());
        frame.setSize(500, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
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
        message.setMaximumSize(new Dimension(15, 5));
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
        text.add(message);
        buttons.add(add);
        buttons.add(back);
        JButton logout = new JButton("Wyloguj");
        main.add(logout, BorderLayout.SOUTH);
        main.add(text, BorderLayout.NORTH);
        main.add(buttons, BorderLayout.CENTER);
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (open) {
            frame.setVisible(true);
        } else {
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
                try (Scanner in = new Scanner(new File("./data.txt"))) {
                    boolean flag = true;
                    while (in.hasNextLine()) {
                        String[] fromFile = in.nextLine().split(",");
                        for (int i = 0; i < fromFile.length; i += 5) {

                            if (user.equals(fromFile[i]) && namebox.getText().equals(fromFile[i + 1]) && datebox.getText().equals(fromFile[i + 2]) && adresbox.getText().equals(fromFile[i + 3]) && avatarbox.getText().equals(fromFile[i + 4])) {
                                flag = false;
                                message.setText("Takie dane zostały już dodane z twojego konta...");
                            }
                        }
                    }
                    if (flag) {
                        if ((!namebox.getText().equals("") || !datebox.getText().equals("") || !adresbox.getText().equals("") || !avatarbox.getText().equals("")) && flag) {
                            dataAdd(user, namebox.getText(), datebox.getText(), adresbox.getText(), avatarbox.getText());
                            message.setText("Dane zostały pomyślnie dodane!");
                        } else {
                            message.setText("Czegoś brakuje gościu...");
                        }
                    }
                }
                catch (FileNotFoundException f) {
                    f.printStackTrace();
                } catch (IOException f) {
                    f.printStackTrace();
                }
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



    protected static void dataAdd(String user, String name, String date, String adres, String avatar) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true));
            bw.append(user + "," + name + "," + date + "," + adres + "," + avatar+",");
            bw.append("\n");
            bw.close();
    }
}
