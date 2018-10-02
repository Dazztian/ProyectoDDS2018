package proyectoDDSs;
import java.util.List;
import java.util.stream.Stream;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;



public class ManageZonas {
	  
	  
	  
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Session session;
	      
	      public static void main(String[] args) {
	          
	          try {
	        	  	        	  
	        	  //Esto es para checkear la Version de hibernate, tengo la 5.2
	        	  //Tenemos el jdbc4
	        	  System.out.println("Hibernate Version: "+ org.hibernate.Version.getVersionString());


	        	  sessionFactory = ManageZonas.createSessionFactory();
	        	 
	          }
	             catch (Throwable ex) { 
	    	         System.err.println("Failed to create sessionFactory object." + ex);
	    	         throw new ExceptionInInitializerError(ex); 
	    	      }
	    	      
	    	      ManageZonas ME = new ManageZonas();

	    	      /* Add few ZonaGeografica records in database */
	    	      Integer empID1 = ME.addZonaGeografica(4545.23, 4545.23, "Caballito");
	    	      Integer empID2 = ME.addZonaGeografica(6.761, 456.45, "Ramos mejia");
	    	      Integer empID3 = ME.addZonaGeografica(78.9789, 69.451, "Zona sur ATR");

	    	      /* List down all the ZonaGeograficas */
	    	      //ME.listZonaGeograficas();
	    	      
	    	      /* Update ZonaGeografica's records */
	    	      //ME.updateZonaGeografica(35, "Paysandu");

	    	      /* Delete an ZonaGeografica from the database */
	    	      //ME.deleteZonaGeografica(26);

	    	      /* List down new list of the ZonaGeograficas */
	    	      //ME.listZonaGeograficas();
	    	   }

	
		public static SessionFactory createSessionFactory()
		{
	    Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ZonaGeografica.class);
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
		}
		
		//Esto deberíamos ponerlo en una clase generica y luego importarlo
		protected void closeSession() {
			//session is null but i don't understand why
  	        if ( session != null &&  session.isOpen() ) 
  	        {
  	        	//Esto estaría bueno arreglarlo
	        	//session.close();
	        }
	    }
	   /* Method to CREATE an ZonaGeografica in the database */
	   public Integer addZonaGeografica(Double latitud, Double longitud, String nombreZona){
	      session = sessionFactory.openSession();
	      Transaction tx = null;
	      Integer ZonaGeograficaID = null;
	      
	      try {
	         tx = session.beginTransaction();
	         ZonaGeografica ZonaGeografica = new ZonaGeografica();
	         ZonaGeografica.setNombre(nombreZona);
	         ZonaGeografica.setLatitud(latitud);
	         ZonaGeografica.setLongitud(longitud);
	         ZonaGeograficaID = (Integer) session.save(ZonaGeografica); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	    	  closeSession();
	    	  //session.close(); 
	      }
	      return ZonaGeograficaID;
	   }
	   	   
	   /* Method to UPDATE salary for an ZonaGeografica */
	   public void updateZonaGeografica(Integer ZonaGeograficaID, String salary ){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         ZonaGeografica ZonaGeografica = (ZonaGeografica)session.get(ZonaGeografica.class, ZonaGeograficaID); 
	         ZonaGeografica.getNombre();
			 session.update(ZonaGeografica); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         //session.close();
	    	  closeSession();
	      }
	   }
	   
	   /* Method to DELETE an ZonaGeografica from the records */
	   public void deleteZonaGeografica(Integer ZonaGeograficaID){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         ZonaGeografica ZonaGeografica = (ZonaGeografica)session.get(ZonaGeografica.class, ZonaGeograficaID); 
	         session.delete(ZonaGeografica); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	    	  closeSession();
	         //session.close(); 
	      }
	   }
	   /* Method to  READ all the ZonaGeograficas */
	   //Esto tambien hay que arreglarlo
	   public void listZonaGeograficas( ){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         //Aca esta el error, no le gusta la HQL "From ZonaGeografica"
	         List ZonaGeograficas = session.createQuery("FROM ZonaGeografica").list();
	         
	         for (Iterator iterator = ZonaGeograficas.iterator(); iterator.hasNext();){
	            ZonaGeografica ZonaGeografica = (ZonaGeografica) iterator.next(); 
	            System.out.print("Latitud: " + ZonaGeografica.getLatitud()); 
	            System.out.print("  Longitud: " + ZonaGeografica.getLongitud()); 
	            System.out.println("  Nombre: " + ZonaGeografica.getNombre()); 
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	    	  
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	    	  closeSession();
	         //session.close(); 
	      }
	   }
	}