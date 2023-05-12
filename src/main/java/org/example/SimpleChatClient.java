package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClient {
    private JTextArea outgoing;
    private JTextArea incoming;
    private JButton sendButton;
    private BufferedReader reader;
    private InetAddress clientIP;

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

    private void setUpNetworking(){
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("",5000); // you MUST add necessary IP and port of server
            SocketChannel socketChannel = SocketChannel.open(serverAddress);
            clientIP = InetAddress.getLocalHost();

            reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));

            System.out.println("Network established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
