package framework;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrameworkServelet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'URL demandée
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Éviter la récursion pour les ressources statiques
        if (requestURI.startsWith(contextPath + "/views/") ||
                requestURI.endsWith(".html") ||
                requestURI.endsWith(".css") ||
                requestURI.endsWith(".js") ||
                requestURI.endsWith(".png") ||
                requestURI.endsWith(".jpg")) {

            // Laisser le conteneur servir les ressources statiques
            RequestDispatcher defaultDispatcher = request.getServletContext()
                    .getNamedDispatcher("default");
            if (defaultDispatcher != null) {
                defaultDispatcher.forward(request, response);
            }
            return;
        }

        // Pour toutes les autres URLs, rediriger vers la page d'erreur
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}