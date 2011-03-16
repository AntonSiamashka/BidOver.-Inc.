/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.daemon;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Администратор
 */
public class MainFrame extends JFrame {

    private Document doc;

    public MainFrame(String title) throws HeadlessException {
        super(title);
        this.setLocation(100, 100);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JTextPane textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        c.add(scrollPane);
        doc = textPane.getDocument();
        try {
            doc.insertString(doc.getLength(), "start", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }

    public void insertString(String str){
        try {
            doc.insertString(doc.getLength(), str+"\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
