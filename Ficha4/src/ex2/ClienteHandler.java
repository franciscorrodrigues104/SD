package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
class ClienteHandler extends Thread {
    private Socket socket;
    private String[] citacoes;
    private int[] views, likes, dislikes;
    private int i;

    public ClienteHandler(Socket socket, String[] citacoes, int[] views, int[] likes, int[] dislikes) {
        this.socket = socket;
        this.citacoes = citacoes;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.i = new java.util.Random().nextInt(citacoes.length);
    }

    @Override
    public void run() {
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

             views[i]++; 
            
            enviarResposta(output);

            boolean ativo = true;
boolean like = true;  
            while (ativo) {
                String mensagem = input.readUTF();
                System.out.println("Pedido do cliente: " + socket.getInetAddress().getHostAddress() +":"+ socket.getPort()+" - " + mensagem);
              
                    switch (mensagem) {
                        case "like":
                             likes[i]++;
                             like = true;
                            break;
                        case "dislike":
                            dislikes[i]++;
                            like = false;
                            break;
                        case "cancel":
                            if (like == true) likes[i]--;
                            else if (like == false) dislikes[i]--;
                            break;
                        case "end":
                            
                            ativo = false;
                            break;
                    }
                
                
                if (ativo) enviarResposta(output);
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Ligação terminada com um cliente.");
        }
    }

    private void enviarResposta(DataOutputStream output) throws IOException {
        String res = citacoes[i] + " - Views: " + views[i] + 
                     " Likes: " + likes[i] + " Dislikes: " + dislikes[i];
        output.writeUTF(res);
    }
}