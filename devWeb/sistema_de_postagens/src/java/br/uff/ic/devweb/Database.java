/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devweb;

/**
 *
 * @author pedroteixeira
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/projeto;create=true;user=APP;password=APP";
            Connection c=DriverManager.getConnection(url);
            System.out.println("Conectado ao bd em "+url);
            return c;
        }catch (Exception e){
            System.out.println("ERRO DE CONEXÃO AO BD : "+e.getMessage());
            return null;
        }
    }
    public static void close(Connection c){
        try{
            c.close();
        }catch (Exception e){
            System.out.println("ERRO AO FECHAR CONEXAO AO BD:"+e.getMessage());
        }
    }
    
}
