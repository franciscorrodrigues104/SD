package ex2;


import java.net.ServerSocket;
import java.net.*;
import java.util.Scanner;

public class ServidorTCP2{
    
    
    public static void main(String[] args) {
        
        Scanner scann = new Scanner(System.in);
        System.out.println("Insira o porto:");
        int porto = scann.nextInt();
        

        try {
            ServerSocket serversocket = new ServerSocket(porto);
            
            
            String[] citacoes = {
                    "Music expresses that which cannot be put into words and that which cannot remain silent - Victor Hugo",
                    "I love you as certain dark things are to be loved,in secret, between the shadow and the soul. - Pablo Neruda, 100 Love Sonnets",
                    "Only the very weak-minded refuse to be influenced by literature and poetry. - Cassandra Clare, Clockwork Angel",
                    "I have loved the stars too fondly to be fearful of the night. - Sarah Williams"
                };
            
             int[] views = new int[citacoes.length];
             int[] dislikes = new int[citacoes.length];
             int[] likes = new int[citacoes.length];



            while (true) {
                Socket socket = serversocket.accept();                                        

            ClienteHandler thread = new ClienteHandler(socket, citacoes, views, likes, dislikes);
            thread.start();
            }
            //
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
    }
}
