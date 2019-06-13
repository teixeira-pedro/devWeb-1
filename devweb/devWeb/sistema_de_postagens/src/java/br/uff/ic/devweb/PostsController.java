package br.uff.ic.devweb;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostsController extends HttpServlet {

    private static String LIST_POSTS = "/timeline.jsp";
    private PostsDao postsDao;

    public PostsController() {
        this.postsDao = new PostsDao();
        /* Instancia um DAO de post
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

            System.out.println("Pegou a action com sucesso :"+(String)action);
        } catch (Exception e) {
            System.out.println("sem action");
        }

        if (action == null || action.equals("")) {
            //Se não tem ação , vai passar pra frente (pra pagina da TIMELINE.jsp)
            
            System.out.println("Se não tem ação , vai passar pra frente (pra pagina da "+LIST_POSTS+")");
            forward = LIST_POSTS;
            
            System.out.println("entrou no action");
            request.setAttribute("posts", this.postsDao.getPosts());
            
            System.out.println("pega os posts pelo dao aqui instanciado ");
            System.out.println("e salva como atributo \'posts\' na request ");
            
        } else if (action.equalsIgnoreCase("delete")) {
            System.out.println("caso a ação seja \'apagar\' , pega o parametro \'id\' ");
            int id = Integer.parseInt(request.getParameter("id"));
            
//            dao.deleteUser(id);
            forward = LIST_POSTS;
//            request.setAttribute("postsDao", dao.getAllUsers()); ;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        System.out.println("despachando  request para : "+LIST_POSTS);
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
