package org.example;

import javax.swing.*;
import java.awt.*;

public class SimpleChatClient {
    private JTextArea outgoing;
    public void go(){

        outgoing = new JTextArea(10,30);
        outgoing.setLineWrap(true);
        outgoing.setWrapStyleWord(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JFrame frame = new JFrame("Chat Client");
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(750,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
