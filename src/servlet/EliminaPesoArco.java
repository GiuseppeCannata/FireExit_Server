package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PesoArco_DB;

/**
 * Servlet implementation class EliminaPesoArco
 */
@WebServlet("/EliminaPesoArco")
public class EliminaPesoArco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private PesoArco_DB padb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaPesoArco() {
        super();
        // TODO Auto-generated constructor stub
        
        padb = new PesoArco_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int Id = Integer.parseInt(request.getParameter("id"));
			int piano = Integer.parseInt(request.getParameter("piano"));
			
			padb.delete(Id);
			
			response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
			
		} catch(Exception e) {
			System.out.println("INPUT ERRATO");
			response.sendRedirect(request.getContextPath() + "/ListMappe");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
