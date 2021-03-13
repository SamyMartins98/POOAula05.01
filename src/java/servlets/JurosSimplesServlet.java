/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samantamartins
 */
@WebServlet(name = "JurosSimplesServlet", urlPatterns = {"/juros-simples.html"})
public class JurosSimplesServlet extends HttpServlet {
    
    private double valorPresente = 0.00;
    private double juros = 0.00;
    private int tempo = 0;
    private double valorFuturo = 0.00;    
    private double jurosConvertido = 0.00;    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Juros Simples Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado do cálculo</h1>");
            try {
                if (request.getParameter("enviar") != null) {
                    calcularJurosSimples(request);

                    out.println("<b>Valor anterior R$: </b>" + valorPresente + "<br><br>");
                    out.println("<b>Juros: </b>" + juros + "<br><br>");
                    out.println("<b>Tempo: </b>" + tempo + "<br><br>");
                    out.println("<b>Valor Final R$: </b>" + valorFuturo);
                    out.println("<hr>");
                    out.println("<a href='index.html'>Início</a>");
                }
            } catch (Exception ex) {
                out.println("Erro ao fazer o calculo: " + ex.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private double calcularJurosSimples(HttpServletRequest request) {
        valorPresente = Double.parseDouble(request.getParameter("valorPresente"));
        juros = Double.parseDouble(request.getParameter("juros"));
        tempo = Integer.parseInt(request.getParameter("tempo"));

        jurosConvertido = juros / 100;
        valorFuturo = valorPresente * (1 + (jurosConvertido * tempo));
        return valorFuturo;
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
