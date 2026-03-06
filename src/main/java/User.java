/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
public class User{
            String nome;
            String telefone;
        
        
        public User(String aNome, String aTelefone){
        
        this.nome = aNome;
        this.telefone = aTelefone;
        }
        
        public void adduser(String aNome, String aTelefone){
            this.nome = aNome;
            this.telefone = aTelefone;
            
        }
        
        public String getUser(String aNome){
        
            return nome;
        }
        
        public String getTelefone(){
            return telefone;
        }
        
        public String deleteUser(String aNome){
            return telefone;
        }
        
        public String toString(){
        return "Nome: "+ nome + "\n Telefone:" + telefone;
        }
}