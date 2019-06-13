<%@page import="br.uff.ic.devweb.CommentBean"%>
<%@page import="java.util.List"%>
<%@page import="br.uff.ic.devweb.PostBean"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <img src="data:image/jpeg;base64,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" />
                            <div class="info">
                                <p>Nome Do cara</p>
                                <span class="text-danger">@nickname</span>
                            </div> 
                        </div>
                        <form class="m-2">
                            <div class="form-group">
                                <textarea rows="3" cols="80"></textarea>
                            </div>
                            <div class="form-group">
                                <a href="#" class="ml-2 btn btn-primary">Postar</a>
                            </div>
                        </form>
                    </div>
                </div>
                <% List<PostBean> posts = (List<PostBean>) request.getAttribute("posts");
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
                            <button type="button" class="btn btn-outline-danger">Excluir</button>
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
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
    </body>
</html>
