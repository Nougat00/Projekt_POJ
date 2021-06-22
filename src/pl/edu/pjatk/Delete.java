package pl.edu.pjatk;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Delete {
    public static void window(boolean open, String user) {
        JFrame nameSearch = new JFrame("Organizer");
        nameSearch.setSize(500, 180);
        nameSearch.setResizable(false);
        nameSearch.setLocationRelativeTo(null);
        JPanel main = new JPanel(new BorderLayout());
        JButton logout = new JButton("Wyloguj");
        JButton back = new JButton("Cofnij");
        JButton delete = new JButton("Usuń");
        JTextField text = new JTextField();
        JTextArea result = new JTextArea();
        result.setEditable(false);
        JPanel upPanel = new JPanel(new GridLayout(1, 2));
        JPanel downPanel = new JPanel(new GridLayout(1, 2));
        upPanel.add(text);
        upPanel.add(delete);
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
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataDeleter(user, Integer.parseInt(text.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                result.setText("");
                if (searchSystem(user).size() > 0) {
                    for (int i = 0; i < searchSystem(user).size(); i++) {
                        result.setText(result.getText() + String.valueOf(i + 1));
                        for (int j = 0; j < searchSystem(user).get(i).size(); j++) {
                            result.setText(result.getText() + " " + searchSystem(user).get(i).get(j));
                        }
                        result.setText(result.getText() + "\n");
                    }
                }
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\barte\\Documents\\PJAKT\\OneDrive - Polsko-Japońska Akademia Technik Komputerowych\\POJ\\Projekt_POJ\\src\\pl\\edu\\pjatk\\trash.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (UnsupportedAudioFileException f) {
                    f.printStackTrace();
                } catch (IOException f) {
                    f.printStackTrace();
                } catch (LineUnavailableException f) {
                    f.printStackTrace();
                }
                nameSearch.dispose();
                Choice.window(true, user);
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

    protected static void dataDeleter(String user, int record) throws IOException {
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
                    if (help != record) {
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
                String tmp=out.nextLine();
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


