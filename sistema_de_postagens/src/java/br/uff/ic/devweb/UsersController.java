package br.uff.ic.devweb;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersController extends HttpServlet {

    private static String LIST_POSTS = "/timeline.jsp";
    private static String LOGIN = "/login.jsp";
    //private static String CADASTRO ="/cadastro.jsp";
    private static String ERRO = "/erro.jsp";
    private UsersDao usersDao;

    public UsersController() {
        this.usersDao = new UsersDao();
        /* Instancia um DAO de user
            nesta classe

         */
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        trata as requests
        
         */
        System.out.println("Tratando Requests...");

        String forward = "";
        String action = "";
        try {
            action = request.getParameter("action");

            System.out.println("Pegou a action com sucesso :" + (String) action);
        } catch (Exception e) {
            System.out.println("sem action");
        }

        if (action == null || action.equals("")) {
            //Se não tem ação , vai passar pra frente (pra pagina de login)

            System.out.println("Se não tem ação , vai passar pra frente (pra pagina da " + LOGIN + ")");
            forward = LOGIN;

            System.out.println("entrou no action");
            //request.setAttribute("posts", this.postsDao.getPosts());

            //System.out.println("pega os posts pelo dao aqui instanciado ");
            //System.out.println("e salva como atributo \'posts\' na request ");
        } else if (request.getParameter("botao_login") != null) {
            System.out.println("caso a ação seja \'LOGar\' , pega o parametro \'nick\'  e senha ");
            String nickname = request.getParameter("nickname");
            String senha = request.getParameter("senha");

            UserBean user = usersDao.getUsuarioId(nickname);
            if (user != null && (user.getSenha().equals(senha))) {

                System.out.println("logou " + nickname +  "redir pra posts");
                request.setAttribute("nickname", nickname);
                System.out.println("mandando pra \"/timeline.jsp\" o atributo com o logado");
                forward = LIST_POSTS;
                //repassar para tela de pagina
            } else {
                System.out.println("erro no login, retornando");
                forward = ERRO;
                //repassar para tela de erro
            }
        } else if (request.getParameter("botao_cadastro") != null) {
            String nickname = request.getParameter("nickname");
            String senha = request.getParameter("senha");
            String avatar = request.getParameter("avatar");
            String nome = request.getParameter("nome");
            usersDao.addUsuario(nickname, senha, avatar, nome);
            forward = LOGIN;
            System.out.println("pegou o s atributos nickname, avatar, senha e nome, da pagina jsp e foi feito o cadastro forward pra login");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        System.out.println("despachando  request para : " + LIST_POSTS);
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
