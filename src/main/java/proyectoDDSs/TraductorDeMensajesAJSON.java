package proyectoDDSs;

import com.google.gson.Gson;

public class TraductorDeMensajesAJSON implements Traductor{

	@Override
	public Object traduccion(String accion) 
	{
     	Gson gson = new Gson();
     	return  gson.toJson(accion);
   	}

}
