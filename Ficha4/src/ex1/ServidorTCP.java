package ex1;


import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.*;
import java.util.Random;

public class ServidorTCP {

    public static void main(String[] args) {

        try {
            ServerSocket serversocket = new ServerSocket(17);

            while (true) {
                Socket socket = serversocket.accept();
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                //String mensagem = input.readUTF();
                //System.out.println("Mensagem: " + mensagem);

                String[] citacoes = {
                    "Music expresses that which cannot be put into words and that which cannot remain silent - Victor Hugo",
                    "I love you as certain dark things are to be loved,in secret, between the shadow and the soul. - Pablo Neruda, 100 Love Sonnets",
                    "Only the very weak-minded refuse to be influenced by literature and poetry. - Cassandra Clare, Clockwork Angel",
                    "I have loved the stars too fondly to be fearful of the night. - Sarah Williams"
                };

                Random rand = new Random();
                String citacaoEscolhida = citacoes[rand.nextInt(citacoes.length)];

                output.write((citacaoEscolhida).getBytes());
                System.out.println("IP Local: " + socket.getLocalAddress().getHostAddress() + "\nPorto Local: " + socket.getLocalPort());
                System.out.println("IP Remoto: " + socket.getInetAddress().getHostAddress() + "\nPorto Remoto: " + socket.getPort());

                output.close();
                socket.close();

            }
            //serversocket.close();
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
    }
}
