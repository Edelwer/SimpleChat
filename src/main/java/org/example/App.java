package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "=====START=====" );
        new SimpleChatClient().go();
        new SimpleChatServer().go();
    }
}