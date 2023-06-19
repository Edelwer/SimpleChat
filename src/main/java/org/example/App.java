package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "=====START=====" );
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> new SimpleChatClient().go());
        new SimpleChatServer().go();
    }
}
