package com.vsuet.uits.sveta;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class Notepad extends JFrame implements ActionListener {
    // Constants
    private Font DEFAULT_FONT = new Font("Sans", Font.BOLD, 32);

    private JTextArea textArea = new JTextArea();
    private JMenuBar menuBar = new JMenuBar();

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem closeMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem aboutMenuItem;
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

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        closeMenuItem = new JMenuItem("Close");
        cutMenuItem = new JMenuItem("Cut");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        aboutMenuItem = new JMenuItem("About");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(closeMenuItem);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        add(textAreaScrollPane, BorderLayout.CENTER);

        cutMenuItem.addActionListener(this);
        copyMenuItem.addActionListener(this);
        pasteMenuItem.addActionListener(this);

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);

        saveMenuItem.addActionListener(this);
        closeMenuItem.addActionListener(this);

        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "About"));
    }

    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == newMenuItem) {
            textArea.setText("");
        } else if (ae.getSource() == closeMenuItem) {
            System.exit(0);
        } else if (ae.getSource() == openMenuItem) {
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

        } else if (ae.getSource() == saveMenuItem) {
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

        } else if (ae.getSource() == cutMenuItem) {
            textArea.cut();
        } else if (ae.getSource() == copyMenuItem) {
            textArea.copy();
        } else if (ae.getSource() == pasteMenuItem) {
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