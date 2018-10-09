import javax.persistence.EntityManager;

import modelsPersistencia.ModelHelperPersistencia;

public class TestDB {

	public static void main(String[] args) {
		
		EntityManager e = ModelHelperPersistencia.getEntityManager();
					
	}

}
