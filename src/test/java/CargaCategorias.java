import java.io.File;
import java.io.IOException;

import proyectoDDSs.Categoria;
import proyectoDDSs.ParserCategoria;
import java.util.*;

import modelsPersistencia.CategoriaModel;

public class CargaCategorias {

	public static void main(String[] args) {
	//Cargo las categorias, igual tiene un error porque el campo 'tipo' lo sube en null y el campo 'cargoVariable' en 0
		
		File archivoCategorias = new File("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\categorias.json"); 
		try {
		List<Categoria> listCategorias = new ParserCategoria().load(archivoCategorias);
		
		CategoriaModel m_cat = new CategoriaModel();
		
		listCategorias.forEach(categoria -> m_cat.agregar(categoria));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
