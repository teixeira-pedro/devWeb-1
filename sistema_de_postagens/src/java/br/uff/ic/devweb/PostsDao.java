package br.uff.ic.devweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostsDao {

    private Connection conexao;

    public List<PostBean> getPosts(String nick_atual) {
        List<PostBean> posts = new ArrayList<>();
        Statement st;
        String qry = "select * from POST";
        ResultSet result;
        List<UserBean> users = new ArrayList<>();
        List<CommentBean> comentarios = new ArrayList<>();

        try {
            conexao = Database.getConnection();
            st = conexao.createStatement();
            result = st.executeQuery(qry);
            while (result.next()) {
                PostBean post = new PostBean();
                int id = result.getInt("ID");
                String title = result.getString("TITLE");
                String text = result.getString("TEXT");
                String nickname = result.getString("AUTHOR");
                post.setId(id);
                post.setTitle(title);
                post.setNickname(nickname);
                post.setText(text);

                System.out.println("Peguei dados do post e os setei [" + title + ';' + nickname + ']');
                System.out.println("pegando comentarios...");

                post.setComments(comentarios);

                System.out.println("recuperando user e foto dele ...");
                UserBean user = UsersDao.getUsuarioId(nickname);
                String avatar = user.getAvatar();
                String name = user.getName();
                System.out.println("usuario [" + avatar + ',' + name + "]");
                post.setAvatar(avatar);
                post.setName(name);

                System.out.println("verificando se o user é dono deste post");
                if (nick_atual.equals(nickname)) {
                    System.out.println("ele é dono, pode deletar");
                    post.setCanDelete(true);
                } else {
                    System.out.println("não é dono");
                    post.setCanDelete(false);
                }

                posts.add(post);

            }

        } catch (SQLException e) {
            System.out.println("erro ao recuperar posts do bd" + e.getMessage());
            return null;
        }
        System.out.println("tamanho da lista " + posts.size());
        Database.close(conexao);
        return posts;

    }

    public List<PostBean> oldGetPosts() {

        List<PostBean> posts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PostBean post = new PostBean();
            post.setTitle("Titulo da postagem " + i);
            post.setText("Texto" + i + " fake blabla bla");
            post.setAvatar("https://www.w3schools.com/w3images/avatar2.png");
            post.setName("Usuario " + i);
            post.setNickname("usuario_0" + i);
            post.setCanDelete(false);

            List<CommentBean> comments = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                CommentBean comment = new CommentBean();
                comment.setText("Comentario fake" + j + " blabla bla");
                comment.setAvatar("https://www.w3schools.com/w3images/avatar2.png");
                comment.setName("Usuario " + j);
                comment.setNickname("usuario_0" + j);

                comments.add(comment);
            }

            post.setComments(comments);

            posts.add(post);
        }

        posts.get(0).setCanDelete(true);

        return posts;
    }

    public void createPost(PostBean post) {
        try {
            conexao = Database.getConnection();
            PreparedStatement preparedStatement = conexao.prepareStatement("insert into POST(TITLE, TEXT, AUTHOR) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getNickname());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.close(conexao);
    }
    
    public void deletePost(PostBean post) {
        UserBean resp;
        Connection conexao = null;
        if (!post.getCanDelete()) {
            return;
        } else {

            try {

                Statement st;
                PreparedStatement qry;
                qry = conexao.prepareStatement("select * from POST where ID = ?;");
                qry.setInt(1, post.getId());

                ResultSet result = qry.executeQuery();
                if (!result.next()) {//nao achou
                    System.out.println("user nao existe");
                    return;
                } else {
                    System.out.println("apagando user");
                    qry = conexao.prepareStatement("delete from POST where ID=? ;");
                    qry.setInt(1, post.getId());
                    qry.executeUpdate();

                }

            } catch (SQLException e) {
                System.out.println("xibu ao apagar dados de post  " + e.getMessage());
            }

        }

    }

}
