package com.mkyong.dao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * AVS_MAPPIS<br>
 * de.avs.mappis.integration.persistence.partnerfirma PartnerfirmaDAOImpl.java <br>
 * DAO zum Partnerfirma-Entity
 * 
 * @author Ludwig Leuschner, AVS GmbH<br>
 *         Modifikations: Iva Abadjieva, AVS GmbH, April 2012
 * @version 1.0<br>
 */

@Repository
public class PartnerfirmaDAOImpl extends AbstractDAO<StatusEnum> implements PartnerfirmaDAO {

	public static final int PFIMPORTER_VS = 1;
	private final Logger log = Logger.getLogger(getClass().getName());
	

	public UserEntity getUserByName(String username){
		String temp= "select user from UserEntity user where user.username = :name";
		Query query = entityManager.createQuery(temp);
		query.setParameter("name", username);
		UserEntity user = (UserEntity)query.getSingleResult();
		System.out.println("* "+user.getUsername());
		return user;
	}
	//@PersistenceContext
    	//private EntityManager entityManager;

	PartnerfirmaDAOImpl(){
		super(StatusEnum.class);
	}
	
	public List<StatusEnum> getAllesAusStatusEnum() throws Exception{
		String frage = "select ste from StatusEnum ste";
		Query query = entityManager.createQuery(frage);
		List<StatusEnum> liste= query.getResultList();
		return liste;
	}

	// /////////////// Status
	@Transactional
	public void setzenStatusStop(Status status) throws Exception {
////////////////////////////////////////////////////////
//this.sessionFactory.getCurrentSession().get(Partnerfirma.class, 1, LockOptions.UPGRADE);
////////////////////////////////////////////////////////
		
		StatusEnum ste= new StatusEnum();
		ste.setStatus(status);
		ste.setVersion(1);
		
		//entityManager.persist(ste);
		System.out.println("******* A");
		save(ste);
		System.out.println("******* B");
		entityManager.flush();
		System.out.println("******* C");
		//long id = 26;
		//StatusEnum sta = find(id);
		System.out.println("******* D");
		//System.out.println("------------------ "+sta.getStatus().name());
		
//		StatusEnum ste1=entityManager.find(StatusEnum.class, 1L);
//		
//		log.log(Level.INFO, "aus der DB Runtime:-Ende--------  "+ ste1.getStatus().name());
//		
//		String hql;
//		Query query;
//		hql = "update StatusEnum p set p.status = :status where p.id = 3 and p.version = 1";
//		query = entityManager.createQuery(hql);
//		query.setParameter("status", status);
//
//		int result = query.executeUpdate();
//
//		if (result == 0)
//			throw new Exception("Update nicht moeglich.");
//		
	}

}