import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class DatagramReceive {
    
     public static void enviarDados(String texto, int porto, DatagramSocket socket, InetAddress ip){

            try{
                   
                    byte[] buffer = texto.getBytes();
         DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ip, porto);
                    
                    socket.send(dp);
                    System.out.println("Resposta enviada: '" +texto + "'");
                    
                   int numBytes = dp.getLength();
                    System.out.println("Numero de bytes enviados: " + numBytes);
                    
            }catch(Exception e){
                    System.out.println(e);
                }
          }

    
        public static void main(String[] args) {
            byte[] inbuf = new byte[1000];
                try {
                    Scanner scann = new Scanner(System.in);
                    System.out.println("Insira o porto:");
                    int porto = scann.nextInt();
                   DatagramSocket socket = new DatagramSocket(porto);
                   
                   
                   ArrayList<User> listaUsers = new ArrayList<>();

            do{
                 DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
                 socket.receive(dp);
                InetAddress ipCliente = dp.getAddress();
                int portoCliente = dp.getPort();
                int numBytes = dp.getLength();

                System.out.println("Ip do cliente: " + ipCliente);
                System.out.println("Porto do cliente: " + portoCliente);
                System.out.println("Numero de bytes recebidos: " + numBytes);


                    String conteudo = new String(dp.getData(), 0, dp.getLength());
                    String[] palavras = conteudo.split(" ");
                    
                    System.out.println("Comando recebido: " + conteudo);
                    
                String metodo = palavras[0].toLowerCase();
        
            switch(metodo){
         
//System.out.println(metodo);     
             case "add":System.out.println("Adicionar");
             if(palavras.length != 3){
                        enviarDados("Tem que ter exatamente 3 palavras!",portoCliente, socket, ipCliente);
                        break;
             }
             
             
             boolean existe = false;
            Iterator<User> itadd = listaUsers.iterator();

        while (itadd.hasNext()) {

            User u = itadd.next();

            if (u.getUser(palavras[1]).equalsIgnoreCase(palavras[1])) {
                System.out.println("Nome ja utilizado, tente novamente!");
              enviarDados("Nome '" + palavras[1] + "' ja utilizado, tente novamente! com outro nome!",portoCliente, socket, ipCliente);

                existe = true;  
            }
        }
        
        if(!existe){
        User user = new User(palavras[1], palavras[2]);
                listaUsers.add(user);
        enviarDados("Utilizador '" + palavras[1] + "' com o numero de telefone '" + palavras[2] + "' registado com sucesso!",portoCliente, socket, ipCliente);
        
        }
             break;
                
             case "get":System.out.println("Obter telefone");
             if(palavras.length != 2){
                        enviarDados("Tem que ter exatamente palavras!",portoCliente, socket, ipCliente);
                        break;
             }
             
             
              Iterator<User> it = listaUsers.iterator();
 boolean encontroutel = false;
        while (it.hasNext()) {

            User u = it.next();
           

            if (u.getUser(palavras[1]).equalsIgnoreCase(palavras[1])) {
                System.out.println("Telefone: " + u.getTelefone());
                enviarDados("Telefone do utilizador" + " '"+ palavras[1]+ "': " + u.getTelefone(), portoCliente, socket, ipCliente);
                encontroutel = true;
            }
           
        }
         if(!encontroutel){
                System.out.println("Nao tem user com o nome '" + palavras[1] + "'");
                enviarDados("Nao tem user com o nome '" + palavras[1] + "'", portoCliente, socket, ipCliente);
        }
        
             break;
             case "del":System.out.println("Delete");
             if(palavras.length != 2){
                        enviarDados("Tem que ter exatamente 2 palavras!",portoCliente, socket, ipCliente);
                        break;
             }
             
             Iterator<User> itdel = listaUsers.iterator();
boolean encontrado = false;
        while (itdel.hasNext()) {

            User u = itdel.next();
            if(u.getUser(palavras[1]).equalsIgnoreCase(palavras[1])){
            itdel.remove();
            enviarDados("Utilizador '" + palavras[1] + "' removido com sucesso!", portoCliente, socket, ipCliente);
            encontrado = true;
            }
          
        }
          if(!encontrado){
                System.out.println("Nao tem user com o nome '" + palavras[1] + "'");
                enviarDados("Nao tem user com o nome '" + palavras[1] + "'", portoCliente, socket, ipCliente);
            }
             
             break;
             case "list":System.out.println("Listagem");
             if(palavras.length != 1){
                        enviarDados("Tem que ter exatamente 1 palavra!",portoCliente, socket, ipCliente);break;}
                 StringBuilder listaStr = new StringBuilder();

             Iterator<User> itlis = listaUsers.iterator();
                boolean encontroulist = false;
        while (itlis.hasNext()) {

            User u = itlis.next();
             listaStr.append(u.toString()).append("\n");
            System.out.println(u.toString());
            encontroulist = true;
            
        }
        if(encontroulist){
             enviarDados(listaStr.toString(), portoCliente, socket, ipCliente); 
        }else{
                 System.out.println("Sem utilizadores!");
                 enviarDados("Sem utilizadores!",portoCliente, socket, ipCliente);
             }
             
             break;
             default: enviarDados("A primeira palavra tem de ser add, get, del ou list!", portoCliente, socket, ipCliente);
             break;
                    }
            
            
                    }while(true);
                        }catch (Exception e) {
                           System.out.println(e);
                            }

            }
        
       
       
       
}



