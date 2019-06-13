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

    private static Connection conexao;

    public static UserBean getUsuarioId(String NICKNAME) {
        UserBean resp;
        try {

            conexao = Database.getConnection();
            Statement st;
            PreparedStatement qry;
            System.out.println("NICKNAME passado: " + NICKNAME);
            qry = conexao.prepareStatement("select * from USR where NICKNAME = ?");
            qry.setString(1, NICKNAME);

            ResultSet result = qry.executeQuery();
            if (result.next()) {//achou
                System.out.println("achou user com nick " + NICKNAME);
                String nome = result.getString("NAME");
                String PASSWORD = result.getString("PASSWORD");
                String avatar = result.getString("AVATAR");
                resp = new UserBean();
                resp.setAvatar(avatar);
                resp.setName(nome);
                resp.setNickname(NICKNAME);
                resp.setSenha(PASSWORD);
                return resp;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Xibu!!! ao executar consulta pra recuperar user" + e.getMessage());
            return null;
        } finally {
            Database.close(conexao);
        }

    }

    public static void addUsuario(String NICKNAME, String PASSWORD, String avatar, String nome) {
        UserBean resp;
        Connection conexao = null;
        try {

            Statement st;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from USR where NICKNAME = ?;");
            qry.setString(1, NICKNAME);

            ResultSet result = qry.executeQuery();
            if (result.next()) {//achou
                System.out.println("user ja existe");
                return;
            } else {
                System.out.println("adicionando novo user");
                qry = conexao.prepareStatement("insert into USR(NICKNAME,PASSWORD,AVATAR,NAME) values (?,?,?,?);");
                qry.setString(1, NICKNAME);
                qry.setString(2, PASSWORD);
                qry.setString(3, avatar);
                qry.setString(4, nome);
                qry.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println("xibu ao inserir dados de user " + e.getMessage());
        }

    }

    public static void editUsuario(String NICKNAME, String PASSWORD, String avatar, String nome) {
        UserBean resp;
        Connection conexao = Database.getConnection();
        try {

            Statement st;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from USR where NICKNAME = ?;");
            qry.setString(1, NICKNAME);

            ResultSet result = qry.executeQuery();
            if (!result.next()) {//nao achou
                System.out.println("user nao existe");
                return;
            } else {
                System.out.println("editando user");
                qry = conexao.prepareStatement("update USR set PASSWORD=? ,avatar=? ,nome=? ;");
                qry.setString(1, PASSWORD);
                qry.setString(2, avatar);
                qry.setString(3, nome);
                qry.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println("xibu ao editar dados de user " + e.getMessage());
        }

    }

    public static void deleteUsuario(String NICKNAME) {
        UserBean resp;
        Connection conexao = null;
        try {

            Statement st;
            PreparedStatement qry;
            qry = conexao.prepareStatement("select * from USR where NICKNAME = ?;");
            qry.setString(1, NICKNAME);

            ResultSet result = qry.executeQuery();
            if (!result.next()) {//nao achou
                System.out.println("user nao existe");
                return;
            } else {
                System.out.println("apagando user");
                qry = conexao.prepareStatement("delete from USR where NICKNAME=? ;");
                qry.setString(1, NICKNAME);
                qry.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println("xibu ao apagar dados de user " + e.getMessage());
        }

    }

}
