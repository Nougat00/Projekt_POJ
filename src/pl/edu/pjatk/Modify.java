package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Modify {
    public static void window(boolean open, String user) {
        JFrame nameSearch = new JFrame("Organizer");
        nameSearch.setSize(500, 180);
        nameSearch.setResizable(false);
        nameSearch.setLocationRelativeTo(null);
        JPanel main = new JPanel(new BorderLayout());
        JButton logout = new JButton("Wyloguj");
        JButton back = new JButton("Cofnij");
        JButton modify = new JButton("Modyfikuj");
        JTextField text = new JTextField();
        JTextArea result = new JTextArea();
        result.setEditable(false);
        JPanel upPanel = new JPanel(new GridLayout(1, 2));
        JPanel downPanel = new JPanel(new GridLayout(1, 2));
        upPanel.add(text);
        upPanel.add(modify);
        downPanel.add(back);
        downPanel.add(logout);
        main.add(upPanel, BorderLayout.NORTH);
        main.add(result, BorderLayout.CENTER);
        main.add(downPanel, BorderLayout.SOUTH);
        nameSearch.add(main);
        nameSearch.setVisible(true);
        nameSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (searchSystem(user).size() > 0) {
            for (int i = 0; i < searchSystem(user).size(); i++) {
                result.setText(result.getText() + String.valueOf(i + 1));
                for (int j = 0; j < searchSystem(user).get(i).size(); j++) {
                    result.setText(result.getText() + " " + searchSystem(user).get(i).get(j));
                }
                result.setText(result.getText() + "\n");
            }
        }
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText().equals("")) {
                    result.setText("Wpisz rekord");
                }
                modifier(user, Integer.parseInt(text.getText()));
                nameSearch.dispose();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameSearch.dispose();
                Choice.window(true, user);
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

    public static void modifier(String user, int record) {
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
        frame.setVisible(true);
        namebox.setText(searchSystem(user).get(record - 1).get(0));
        datebox.setText(searchSystem(user).get(record - 1).get(1));
        adresbox.setText(searchSystem(user).get(record - 1).get(2));
        avatarbox.setText(searchSystem(user).get(record - 1).get(3));
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
                                message.setText("Nic nie zmodyfikowałeś..");
                            }
                        }
                    }
                    if (flag) {
                        if ((!namebox.getText().equals("") || !datebox.getText().equals("") || !adresbox.getText().equals("") || !avatarbox.getText().equals("")) && flag) {
                            dataModify(user, namebox.getText(), datebox.getText(), adresbox.getText(), avatarbox.getText(), record);
                            message.setText("Dane zostały pomyślnie dodane!");
                        } else {
                            message.setText("Czegoś brakuje gościu...");
                        }
                    }
                } catch (FileNotFoundException f) {
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
                Modify.window(true, user);
            }
        });
    }

    protected static void dataModify(String user, String name, String date, String adres, String avatar, int record) throws IOException {
        PrintWriter writer = new PrintWriter("tmp.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("tmp.txt", true));
        writer.print("");
        writer.close();
        try (Scanner in = new Scanner(new File("./data.txt"))) {
            int help = 0;
            while (in.hasNextLine()) {
                String tmp = in.nextLine();
                String[] fromFile = tmp.split(",");
                if (fromFile[0].equals(user)) {
                    help++;
                    if (help == record) {
                        bw.append(user + "," + name + "," + date + "," + adres + "," + avatar + ",");
                        bw.append("\n");
                    } else {
                        bw.append(tmp);
                        bw.append("\n");
                    }
                } else {
                    bw.append(tmp);
                    bw.append("\n");
                }
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter dataClear = new PrintWriter("data.txt");
        dataClear.print("");
        dataClear.close();
        BufferedWriter dataReplace = new BufferedWriter(new FileWriter("data.txt", true));
        try (Scanner out = new Scanner(new File("./tmp.txt"))) {
            while (out.hasNextLine()) {
                String tmp = out.nextLine();
                System.out.println(tmp);
                dataReplace.append(tmp);
                dataReplace.append("\n");
            }
            dataReplace.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}