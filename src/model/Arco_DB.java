package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Arco;
import entity.Nodo;
import entity.PesoArco;

public class Arco_DB extends Model{
	
	public static final String TBL_NAME = "ARCO";
	public static final String FIELD_ID = "id";
    public static final String FIELD_NODOPARTENZA = "nodoPartenzaId";
    public static final String FIELD_NODOARRIVO = "nodoArrivoId";   
    public static final String FIELD_MAPPAID = "mappaId"; 
    
    public Arco_DB() {
    	
    	super();  
    	
    }
    
    
    
    public ArrayList<Arco> findArchiByPiano(int mappaId){
    	
         ArrayList<Arco> Archi = new ArrayList<Arco>();
         
         Nodo_DB ndb = new Nodo_DB();
		 PesoArco_DB padb = new PesoArco_DB();
    	 
    	 try {
         	
 	    	String query = "select * from "+TBL_NAME+" where "+FIELD_MAPPAID+" = "+mappaId;
 	    	System.out.println(query);
 	    	
 	    	OpenConnessione();
 		    ResultSet rs = selectQuery(query);
 		    
 		    while(rs.next()) {
 		    
 			  Nodo nodoPartenza = ndb.FindNodoById(rs.getInt(FIELD_NODOPARTENZA));
 			  Nodo nodoArrivo = ndb.FindNodoById(rs.getInt(FIELD_NODOARRIVO));
 			  ArrayList<PesoArco> pesi = padb.findPesiById(rs.getInt(FIELD_ID));
 			  
 			  Arco arco = new Arco( rs.getInt(FIELD_ID),
 					                nodoPartenza,
 					                nodoArrivo,
 					                pesi
 					               );
 			  
 			  Archi.add(arco);
 			  
 		    }
 		    
 		    CloseConnessione();
 		    st.close();
 			
     	} catch (SQLException e) {
 			e.printStackTrace();	
 		}
    	 
    	 return Archi;
    }
    	
    
   public void method(){
    	
	   //VUOTO
    	
    }
}