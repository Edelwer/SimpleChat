package org.example;

import javax.swing.*;
import java.awt.*;

public class SimpleChatClient {
    private JTextArea outgoing;
    private JTextArea incoming;
    private JButton sendButton;
    public void go(){
        JScrollPane scroller = createScrollableTextArea();

        outgoing = new JTextArea(10,30);
        outgoing.setLineWrap(true);
        outgoing.setWrapStyleWord(true);
        JScrollPane scroller1 = new JScrollPane(outgoing);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sendButton = new JButton("Send");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, scroller);
        mainPanel.add(BorderLayout.SOUTH, scroller1);
        mainPanel.add(sendButton);

        JFrame frame = new JFrame("Chat Client");
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(750,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JScrollPane createScrollableTextArea(){
        incoming = new JTextArea(30,60);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

}
