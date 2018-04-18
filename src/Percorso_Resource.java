
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import services.Mappa_service;
import services.Percorso_service;

@Path("percorso")
public class Percorso_Resource {


	@POST
	@Path("getPercorsoMinimo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String downloadPercorso(String messaggio) {

		System.out.println("mac"+messaggio);

		// Estrazione dal Json in entrata
		JsonObject jobj = new Gson().fromJson(messaggio, JsonObject.class);
		String mac = jobj.get("posUtente").getAsString();  //relativo al MAC del beacon
		int piano = jobj.get("piano").getAsInt();

		Percorso_service percorsoService = new Percorso_service();
		Mappa_service mappaService = new Mappa_service();

		Mappa mappa = mappaService.CostruzioneMappa(piano);

		Nodo posU = percorsoService.CreaNodoPosUtente(mac);


		ArrayList<Arco> percorso = percorsoService.calcolaPercorso(mappa , posU);



		// Costruisco il Json da inviare all App
		Gson gson = new Gson();
		String esito = gson.toJson(percorso);

		System.out.println(esito);

		return esito;	//essendo un Json il ritorno sar� di tipo String	
	}

}
