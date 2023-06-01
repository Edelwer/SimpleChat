package org.example;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClient {
    private JTextArea outgoing;
    private JTextArea incoming;
    private JButton sendButton;
    private BufferedReader reader;
    private PrintWriter writer;
    private ExecutorService executor;
    private InetAddress clientIP;

    public void go(){
        JScrollPane scroller = createScrollableTextArea();

        outgoing = new JTextArea(10,30);
        outgoing.setLineWrap(true);
        outgoing.setWrapStyleWord(true);
        JScrollPane scroller1 = new JScrollPane(outgoing);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sendButton = new JButton("Send");
        //sendButton.addActionListener(e -> sendMessage());

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
        outgoing.addKeyListener(new myKeyAdapter());
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
            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));

            executor = Executors.newSingleThreadExecutor();
            executor.execute(new IncomingReader());

            System.out.println("Network established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(){
        writer.println(outgoing.getText().substring(0,outgoing.getText().length()- 1));
        writer.flush();
        outgoing.setText("");
        outgoing.requestFocus();
    }

    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class myKeyAdapter extends KeyAdapter {
        private boolean isShift = false;
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if (isShift){
                    outgoing.append("\n");
                }else{
                    //sendMessage();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SHIFT){
                isShift = false;
            }
        }
    }

}
