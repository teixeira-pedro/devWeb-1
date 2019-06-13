/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.devweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pedroteixeira
 */
class UsersDao {
    
    
    
    public static UserBean getUsuarioId(String nickname) {
        UserBean resp ;
        Connection conexao = null;
        try{
            

            
            Statement st ;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from Usr where nickname = ?;");
            qry.setString(1, nickname);

            ResultSet result=qry.executeQuery();
            if (result.next()){//achou
                System.out.println("achou user com nick "+nickname);
                String nome=result.getString("nome");
                String senha=result.getString("senha");
                String avatar=result.getString("avatar");
                resp = new UserBean();
                resp.setAvatar(avatar);
                resp.setName(nome);
                resp.setNickname(nickname);
                resp.setSenha(senha);
                return resp;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println("Xibu!!! ao executar consulta pra recuperar user"+e.getMessage());
            return null;
        }
        
    }
    
    

    public static void addUsuario(String nickname,String senha,String avatar,String nome){
        UserBean resp ;
        Connection conexao = null;
        try{
            

            
            Statement st ;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from Usr where nickname = ?;");
            qry.setString(1, nickname);

            ResultSet result=qry.executeQuery();
            if (result.next()){//achou
                System.out.println("user ja existe");
                return;
            }else{
                System.out.println("adicionando novo user");
                qry=conexao.prepareStatement("insert into usr(nickname,senha,avatar,nome) values (?,?,?,?);");
                qry.setString(1, nickname);
                qry.setString(2,senha);
                qry.setString(3,avatar);
                qry.setString(4,nome);
                qry.executeUpdate();
            
            }
            
        }catch (SQLException e){
            System.out.println("xibu ao inserir dados de user "+e.getMessage());
        }
        
    }
    public static void editUsuario(String nickname,String senha,String avatar,String nome){
        UserBean resp ;
        Connection conexao = null;
        try{
            

            
            Statement st ;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from Usr where nickname = ?;");
            qry.setString(1, nickname);

            ResultSet result=qry.executeQuery();
            if (! result.next()){//nao achou
                System.out.println("user nao existe");
                return;
            }else{
                System.out.println("editando user");
                qry=conexao.prepareStatement("update usr set senha=? ,avatar=? ,nome=? ;");
                qry.setString(1,senha);
                qry.setString(2,avatar);
                qry.setString(3,nome);
                qry.executeUpdate();
            
            }
            
        }catch (SQLException e){
            System.out.println("xibu ao editar dados de user "+e.getMessage());
        }
        
    }
    public static void deleteUsuario(String nickname){
        UserBean resp ;
        Connection conexao = null;
        try{
            

            
            Statement st ;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from Usr where nickname = ?;");
            qry.setString(1, nickname);

            ResultSet result=qry.executeQuery();
            if (! result.next()){//nao achou
                System.out.println("user nao existe");
                return;
            }else{
                System.out.println("apagando user");
                qry=conexao.prepareStatement("delete from usr where nickname=? ;");
                qry.setString(1,nickname);
                qry.executeUpdate();
            
            }
            
        }catch (SQLException e){
            System.out.println("xibu ao apagar dados de user "+e.getMessage());
        }
        
    }

    
}
