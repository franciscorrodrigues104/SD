/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */

import java.net.*;
import java.util.Scanner;

public class DatagramSend {
    
    public static void recebe(int porto, DatagramSocket socket){
        
        byte[] inbuf = new byte[1000];
            try {
                  socket.setSoTimeout(5000);
                DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
                     
                socket.receive(dp);
                
            String conteudo = new String(dp.getData(), 0, dp.getLength());
            System.out.println(conteudo);
}
catch (Exception e) {
System.out.println("Sem resposta!");
}
}
    
    
    
        public static void main(String[] args){
            
                         Scanner scann = new Scanner(System.in);

                 System.out.println("Insira o IP:");
                   String ip = scann.next();
                    
                    System.out.println("Insira o porto:");
                    int porto = scann.nextInt();
                    scann.nextLine();
                    
                   do{
                       
                     System.out.println("Qual a operacao que deseja fazer?");
                     String strOperacao = scann.nextLine();
                     
                     if(strOperacao.equals("quit")){
                     System.out.println("A sair.....");
                     System.exit(0);}

                      byte[] data = strOperacao.getBytes();
                
                try{
                  
                    InetAddress ia = InetAddress.getByName(ip);

                    DatagramPacket dp = new DatagramPacket(data, data.length, ia, porto);
                    
            DatagramSocket socket = new DatagramSocket();
                    socket.send(dp);
                                    recebe(porto, socket);

                }catch(Exception e){
                    System.out.println(e);
                }
                   }while(true);
            
            
        }
    
}
