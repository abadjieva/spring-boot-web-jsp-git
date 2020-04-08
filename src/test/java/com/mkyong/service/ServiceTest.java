package com.mkyong.service;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;

import com.mkyong.HelloWorldConfiguration;
import com.mkyong.SpringBootWebApplication;
import com.mkyong.User;
import com.mkyong.dao.PartnerfirmaDAO;
import com.mkyong.dao.Status;
import com.mkyong.dao.StatusEnum;


//Load Spring contexte

//@ActiveProfiles("testing")

//TEST 08.04.2020

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
//@DataJpaTest
//@ContextConfiguration(classes = { HelloWorldConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
//@TestPropertySource(locations="classpath:application.properties")

// public class ServiceTest extends AbstractJUnit4SpringContextTests{
public class ServiceTest {
	private final Logger log= Logger.getLogger(getClass().getName());
	//
	
	@Autowired
	protected PartnerfirmaDAO partnerDao;
	
	

	@Autowired
	UserService service;
	
	
	
	
	private static int a=4;

	@BeforeClass
	public static void testAllg(){
		a = 5;
	
	}
	//@Ignore("Before ignoriert")
	@Before
	public void vorTest(){
		a = 5;
	}
	
	@Test
	public void testProcess1() {
		//int a = 6;
		try{
			System.out.println( "Tr1---------");
			
			List<StatusEnum> liste = partnerDao.getAllesAusStatusEnum();
			assertNotEquals("liste ist null", liste, null );
			Integer ursprungsZahl = liste.size();
			System.out.println( "Tr2--------- "+ ursprungsZahl );
		
			service.setzenStatusStop(Status.START);
			partnerDao.setzenStatusStop(Status.START);
		
			System.out.println( "Richtig in die DB Runtime:---------");
			List<StatusEnum> liste1 = partnerDao.getAllesAusStatusEnum();
			Integer ehoehungsZahl = liste1.size();
			System.out.println( "Tr3--------- "+ehoehungsZahl);
			Integer dif= ehoehungsZahl-ursprungsZahl;
			assertTrue("TRUE", dif > 0 );

		
		}catch(Exception e){
			System.out.println("Fehler DB Runtime:---------");
		}
		assertTrue("NICHT TRUE", a == 5);

	}

	//@Ignore
	@Test
	public void testProcess2() {
		List<User> lis = null;
		
		try {
	
			lis=service.findAllUsers();
			System.out.println("---------------OK "+lis.size());
			//System.out.println(lis.size());
			assertNotNull("LISTE IST not LEER!", lis);
			
			
		} catch (Exception e) {

		}
	}

	
}