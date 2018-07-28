package proyectoDDSs;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.ProcessBuilder.Redirect.Type;
import java.lang.reflect.ParameterizedType;

import json.*;

//Solo hay que cambiar nombre de clase y la lista que recibe ( de que tipo es la clase de objetos que la componen)
public class ParserZonasGeograficas {
	public List<ZonaGeografica> load(File file) throws IOException {
        final String json = new JsonFile(file.getAbsolutePath()).read();
        //Aca tambien hay que meter mano
        final java.lang.reflect.Type listType = new TypeToken<ArrayList<ZonaGeografica>>(){}.getType();
        return  new GsonBuilder().create().fromJson(json, listType);
        //Me construirá un obj basado en el json que reciba

}
}
