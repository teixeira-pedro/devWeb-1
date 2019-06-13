<%@page import="br.uff.ic.devweb.CommentBean"%>
<%@page import="java.util.List"%>
<%@page import="br.uff.ic.devweb.PostBean"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! PostBean criarPost(String title, String text) {
    PostBean newPost = new PostBean();
    newPost.setNickname("");
    newPost.setText(text);
    newPost.setTitle(title);
    
    return newPost;
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" />
        <link href="css/timeline.css" rel="stylesheet" />
        <script src="js/bootstrap.min.js"></script>
        <title>Timeline</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container timeline">
            <div class="row">
                <div class="card post">
                    <div class="card-body">
                        <div class="avatar">
                            <img src="https://www.w3schools.com/w3images/avatar2.png" />
                            <div class="info">
                                <p>Nome Do cara</p>
                                <span class="text-danger">@nickname</span>
                            </div> 
                        </div>
                        <form class="m-2">
                            <div class="form-group">
                                <textarea style="max-width: 100%;" rows="3" cols="80"></textarea>
                            </div>
                            <div class="form-group">
                                <a href="#" class="ml-2 btn btn-primary">Postar</a>
                            </div>
                        </form>
                    </div>
                </div>
                <% List<PostBean> posts = (List<PostBean>) request.getAttribute("posts");
                    if (posts == null || posts.isEmpty()) {
                %> <h4>Nenhum post encontrado</h4><%
                } else {
                    for (PostBean post : posts) {%>

                <div class="card post">
                    <div class="card-body">
                        <div class="avatar">
                            <img src="<%= post.getAvatar()%>"/>
                            <div class="info">
                                <p><%= post.getName()%></p>
                                <span class="text-danger">@<%= post.getNickname()%></span>
                            </div> 
                        </div>
                        <% if (post.getCanDelete()) { %>
                        <div class="float-right botao-excuir">
                            <% request.setAttribute("post", post); %>
                            <a href="PostsController?action=delete" class="btn btn-outline-danger">Excluir</a>
                        </div>
                        <% }%>
                        <h5 class="card-title"><%= post.getTitle()%></h5>
                        <p class="card-text"><%= post.getText()%></p>

                        <div class="comentarios">
                            <form>
                                <input type="text" size="65" name="comment" />
                                <a href="#" class="ml-2 btn btn-primary">Comentar</a>
                            </form>

                            <p>Comentarios (<%= post.getComments().size()%>)</p>
                            <div class="comentarios-lista">
                                <% List<CommentBean> comments = post.getComments();
                                    if (comments == null || comments.isEmpty()) {
                                %> <h4>Nenhum Comentario encontrado</h4><%
                                } else {
                                    for (CommentBean comment : comments) {%>
                                <div class="avatar">
                                    <img src="<%= comment.getAvatar()%>" />
                                    <div class="info">
                                        <p><%= comment.getName()%></p>
                                        <span class="text-danger">@<%= comment.getNickname()%></span>
                                    </div>
                                </div>
                                <div class="comentario">
                                    <p><%= comment.getText()%></p>
                                </div>
                                <% }
                                    }%>
                            </div>
                        </div>
                    </div>
                </div>
                <% }
                    }%>
            </div>
        </div>
    </body>
</html>
