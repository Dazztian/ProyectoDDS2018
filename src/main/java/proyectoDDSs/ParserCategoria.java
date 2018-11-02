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

public class ParserCategoria {
	
	public List<Categoria> load(File file) throws IOException {
        final String json = new JsonFile(file.getAbsolutePath()).read();
        final java.lang.reflect.Type listType = new TypeToken<ArrayList<Categoria>>(){}.getType();
        return  new GsonBuilder().create().fromJson(json, listType);
        //Me construirá un obj basado en el json que reciba
    }

}
