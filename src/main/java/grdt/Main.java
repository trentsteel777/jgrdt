package grdt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
  public static void main(String... argss) {
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");  
	  EntityManager em = emf.createEntityManager();                               
	  EntityTransaction tx = em.getTransaction();                                 

	  Test test = em.find(Test.class, 1);                                         
	  if (test == null) {                                                         
	    test = new Test();                                                        
	    test.setId(1);                                                              
	    test.setData("a");                                                          

	    tx.begin();                                                               
	    em.persist(test);                                                         
	    tx.commit();                                                              
	  }                                                                           

	  System.out.format("Test{id=%s, data=%s}\n", test.getId(), test.getData());            

	  em.close();                                                                 
	  emf.close(); 
  }
}
