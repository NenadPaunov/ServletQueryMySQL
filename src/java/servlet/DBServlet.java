/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.DB;

/**
 *
 * @author Nenad
 */
public class DBServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html>"
                + "<head>"
                + "<style>"
                + "div{"
                + "background:#DDDDDD;}"
                + "table{margin-left:auto;margin-right:auto;text-align:center;}"
                + "table, th, td {border:4px solid #CCCCCC; border-collapse: collapse; padding:4px;}"
                + "th{background:#ECE7D7;}"
                + "td{background:#F5F4F2;}"
                + "h1{text-align:center;}"
                + "a:link,a:visited {color:#000000;}"
                + "a:hover {color:#C51417;}"
                + "a:active {color:#CCCCCC;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<h1>Prikaz studenata iz pretrage"
                + "</h1>"
                + "<table>"
                + "<tr>"
                + "<th>ID"
                + "</th>"
                + "<th>Prezime"
                + "</th>"
                + "<th>Ime"
                + "</th>"
                + "<th>Broj indeksa"
                + "</th>"
                + "<th>Godina indeksa"
                + "</th>"
                + "<th>Smer"
                + "</th>"
        );
        String upit = "select * from skola.Studenti where godinaindeksa=? and smer=?";

        int godina = Integer.parseInt(request.getParameter("godina"));
        int index = Integer.parseInt(request.getParameter("index"));
        Statement stmt = null;
        Connection con = null;
        System.out.println("Usao sam");
        try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                return;
            }
            System.out.println("Konektovao sam se");

            stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement(upit);
            ps.setInt(1, index);
            ps.setInt(2, godina);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Studenti(ID_S, Prezime, Ime, Brojindeksa, GodinaIndeksa, Smer)

                int id = rs.getInt(1);
                String prezime = rs.getString(2);
                String ime = rs.getString(3);
                int brojIndexa = rs.getInt(4);
                int godinaIndexa = rs.getInt(5);
                int smer = rs.getInt(6);

                System.out.println(id + " " + prezime + " " + ime + " " + brojIndexa + " " + godinaIndexa + " " + smer);

                out.write("<tr>"
                        + "<td>"
                        + id
                        + "</td>"
                        + "<td>"
                        + prezime
                        + "</td>"
                        + "<td>"
                        + ime
                        + "</td>"
                        + "<td>"
                        + brojIndexa
                        + "</td>"
                        + "<td>"
                        + godinaIndexa
                        + "</td>"
                        + "<td>"
                        + smer
                        + "</td>"
                        + "</tr>"
                );
            }
            //con.close();
            out.write("</table>"
                    + "<p>"
                    + "<a href=\"index.html\">"
                    + "Povratak na prvu stranu</a>"
                    + "</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>"
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
