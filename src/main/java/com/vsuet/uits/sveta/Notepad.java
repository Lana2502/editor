package com.vsuet.uits.sveta;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

// Вообще хорошо разделять наследование:
// Отдельно ActionListener, отдельно JFrame, отдельно Main
// Но в твоем случае, все окей. Просто на будущее
class Notepad extends JFrame implements ActionListener {
    // Constants
    private Font DEFAULT_FONT = new Font("Sans", Font.BOLD, 32);

    private JTextArea textArea = new JTextArea();
    private JMenuBar menuBar = new JMenuBar();

    // Правильно делать по элементу на каждую строчку.
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    // Переименуй остальные так же
    private JMenuItem newMenuItem;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem close;
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem about;
    private JFileChooser fileChooser;

    private Notepad() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        final JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaScrollPane.setPreferredSize(new Dimension(textArea.getWidth(), textArea.getHeight()));
        textAreaScrollPane.createVerticalScrollBar();
        textAreaScrollPane.createHorizontalScrollBar();

        fileChooser = new JFileChooser();
        setFont(DEFAULT_FONT);
        menuBar = new JMenuBar();

        // todo Остальные пункты меню переименуй также
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        // todo: Переименовать остальные так же как newMenuItem
        newMenuItem = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        close = new JMenuItem("Close");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        about = new JMenuItem("About");

        fileMenu.add(newMenuItem);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(close);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // todo: Ну а где окошко-то? с диалогом "О программе"?
        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        add(textAreaScrollPane, BorderLayout.CENTER);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        newMenuItem.addActionListener(this);
        open.addActionListener(this);

        save.addActionListener(this);
        close.addActionListener(this);
    }

    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == newMenuItem) {
            textArea.setText("");
        } else if (ae.getSource() == close) {
            System.exit(0);
        } else if (ae.getSource() == open) {
            textArea.setText("");
            int n = fileChooser.showOpenDialog(this);
            if (n == JFileChooser.APPROVE_OPTION) {
                try {
                    Scanner sc = new Scanner(fileChooser.getSelectedFile());
                    while (sc.hasNext()) {
                        String str = sc.nextLine();
                        textArea.append(str +"\n");
                    }
                } catch (final FileNotFoundException fnfException) {
                    System.out.println(fnfException);
                }
            }

        } else if (ae.getSource() == save) {
            try  (final FileWriter fileWriter = new FileWriter("New Text Document.txt" )) {
                final BufferedWriter br = new BufferedWriter(fileWriter);
                final Scanner s = new Scanner(textArea.getText());
                while (s.hasNext()) {
                    br.write(s.nextLine());
                    br.newLine();
                }
                br.close();
            } catch (final IOException ie) {
                System.out.println(ie);
            }

        } else if (ae.getSource() == cut) {
            textArea.cut();
        } else if (ae.getSource() == copy) {
            textArea.copy();
        } else if (ae.getSource() == paste) {
            textArea.paste();
        }
    }

    public static void main(final String args[]) {
        final Notepad window = new Notepad();
        window.setBounds(200, 200, 640, 480);
        window.setVisible(true);
        window.setTitle("Notepad");
    }
}
