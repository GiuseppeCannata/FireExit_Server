package servlet;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Peso;
import model.Peso_DB;

/**
 * Servlet implementation class InserisciNuovoPeso
 */
@WebServlet("/InserimentoPeso")
public class InserimentoPeso extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Peso_DB pdb;
	private Peso peso;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoPeso() {
        super();
        // TODO Auto-generated constructor stub
        
        pdb = new Peso_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
      
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoPesoView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String Descrizione = (String) request.getParameter("Descrizione");
        int p = Integer.parseInt(request.getParameter("peso"));
        
        boolean hasError = false;
        String errorString = null;
        
        peso = new Peso(0,Descrizione,p);
        
        //1^ controllo
        if(Descrizione.length() == 0) {
        	hasError = true;
        	errorString = "Alcuni campi sembrano essere vuoti";
        }else {
	        try {
	        	
				if(pdb.inserimentoPeso(peso))
				   response.sendRedirect(request.getContextPath() + "/ListPesi");
				
			} catch (SQLException e) {
				e.printStackTrace();
				
				if(e.getErrorCode() == 30000) {
					hasError = true;
		            errorString = "Peso gi� presente nel DB";
				}
			} 
        }
        
        if (hasError) {		
	       
		    request.setAttribute("errorString", errorString);
		    request.setAttribute("peso", peso);
		    
		    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoPesoView.jsp");
		    dispatcher.forward(request, response);		
	   }
	}

}
