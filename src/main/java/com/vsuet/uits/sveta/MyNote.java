package com.vsuet.uits.sveta;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.awt.print.*;



class MyNote extends JFrame implements ActionListener {
    private JTextArea ta;
    private JMenuBar mb;
    private Scanner s = null;
    private JMenu file, edit, help;
    private JMenuItem new1, open, save, close, cut, copy, paste, print, about;
    private JFileChooser fc;
    private Font f;
    JScrollPane jsp;

    MyNote() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        f = new Font("Arial", Font.BOLD, 18);
        fc = new JFileChooser();
        setFont(f);
        ta = new JTextArea();
        mb = new JMenuBar();
        mb.setBounds(0, 0, 800, 30);
        ta.setBounds(0, 30, 800, 770);
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        new1 = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print...");
        close = new JMenuItem("Close");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        about = new JMenuItem("About");

        file.add(new1);
        file.addSeparator();
        file.add(open);
        file.addSeparator();
        file.add(save);
        file.addSeparator();
        file.add(print);
        file.addSeparator();
        file.add(close);
        file.addSeparator();

        edit.add(cut);
        edit.addSeparator();
        edit.add(copy);
        edit.addSeparator();
        edit.add(paste);
        edit.addSeparator();
        help.add(about);

        mb.add(file);
        mb.add(edit);
        add(mb);
        add(ta);
        mb.add(help);
        help.addSeparator();


        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        new1.addActionListener(this);
        open.addActionListener(this);

        save.addActionListener(this);
        print.addActionListener(this);
        close.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == new1) {
            ta.setText("");
        }
        if(ae.getSource() == close)
        {
            System.exit(0);
        }
        if (ae.getSource() == open)
        {
            ta.setText("");
            int n = fc.showOpenDialog(this);
            if( n == JFileChooser.APPROVE_OPTION) {
                try {
                    Scanner sc = new Scanner(fc.getSelectedFile());
                    while (sc.hasNext()) {
                        String str = sc.nextLine();
                        ta.append(str +"\n");
                    }
                } catch (FileNotFoundException aie) {
                    System.out.println(aie);
                }
            }
        }


        if (ae.getSource() == save) {
            try  (FileWriter f1 = new FileWriter("New Text Document.txt" ))
            {
                BufferedWriter br = new BufferedWriter(f1);
                s = new Scanner(ta.getText());
                while (s.hasNext()) {
                    br.write(s.nextLine());
                    br.newLine();
                }
                br.close();

            } catch (IOException ie) {
                System.out.println(ie);
            }

        }

        if (ae.getSource() == cut)
        {
            ta.cut();

        }
        if (ae.getSource() == copy)
        {
            ta.copy();
        }
        if (ae.getSource() == paste)
        {
            ta.paste();
        }
        if (ae.getSource() == print)
        {
            try
            {
                ta.print();
            }
            catch (PrinterException ex)
            {
                System.out.println(ex);
            }



        }
    };

    public static void main(String args[]) {
        MyNote mn = new MyNote();
        mn.setSize(800, 800);
        mn.setVisible(true);
        mn.setTitle("Notepad");
    }
}
