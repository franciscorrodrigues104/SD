package ex1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.DataInputStream;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class clienteTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner scann = new Scanner(System.in);

        System.out.println("Insira o ip/hostname:");
        String ip = scann.next();

        try {

            Socket socket = new Socket(ip, 17);
            DataInputStream input = new DataInputStream(socket.getInputStream());

            byte[] buffer = new byte[1024];
            int nymBytes = input.read(buffer);

            if (nymBytes > 0) {
                String resposta = new String(buffer, 0, nymBytes);
                System.out.println("Resposta: " + resposta);
            } else {
                //as vezes não retorna nada
                System.out.println("Sem resposta do servidor!");
            }

            input.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
