<%@page import="br.uff.ic.devweb.CommentBean"%>
<%@page import="java.util.List"%>
<%@page import="br.uff.ic.devweb.UserBean"%>
<%@page import="br.uff.ic.devweb.UsersController"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" />
        <link href="css/timeline.css" rel="stylesheet" />
        <script src="js/bootstrap.min.js"></script>
        <title>Login/Cadastro</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <form action="UserController" method="post">
            Faça login ou cadastre-se
            Nickname : <input type="text" name= "nickname">
            <br>
            Senha :<input type="password" name= "senha">
            <br>
            Avatar (insira o caminho do arquivo da imagem) : <input type="text" name= "avatar">
            <br>
            Nome Completo : <input type="text" name= "nome">
            <br>
            <br>
            <input type="submit" name="botao_login" value="login">
            <input type="submit" name="botao_cadastro" value="cadastro">

        </form>
    </body>
</html>
