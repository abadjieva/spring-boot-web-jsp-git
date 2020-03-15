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


// Iva Abadjieva
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
//@DataJpaTest
//@ContextConfiguration(classes = { HelloWorldConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
//@TestPropertySource(locations="classpath:application.properties")

// public class ServiceTest extends AbstractJUnit4SpringContextTests{
public class ServiceTestMock {
	private final Logger log= Logger.getLogger(getClass().getName());
	//
	
	@Mock
	protected PartnerfirmaDAO partnerDao;
	
	@Autowired
	@InjectMocks
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
			
			StatusEnum ste = new StatusEnum();
			ste.setId(1L);
			ste.setStatus(Status.START);
			ste.setVersion(1);
			List<StatusEnum> listdef= new ArrayList<>();
			listdef.add(ste);	
			
			Mockito.when(partnerDao.getAllesAusStatusEnum()).thenReturn(listdef);
			List<StatusEnum> liste = partnerDao.getAllesAusStatusEnum();
			assertEquals("liste ist null", liste, null );
		//Integer ursprungsZahl = liste.size();
		//System.out.println( "Tr2--------- "+ ursprungsZahl );
		
		service.setzenStatusStop(Status.STOP);
		//Mockito.verify(partnerDao).setzenStatusStop(Status.STOP);
		
		partnerDao.setzenStatusStop(Status.START);
		Mockito.verify(partnerDao).setzenStatusStop(Status.STOP);
		System.out.println( "Richtig in die DB Runtime:---------");
		List<StatusEnum> liste1 = partnerDao.getAllesAusStatusEnum();
		//Integer ehoehungsZahl = liste1.size();
		//System.out.println( "Tr3--------- "+ehoehungsZahl);
		//Integer dif= ehoehungsZahl-ursprungsZahl;
		//assertTrue("TRUE", dif > 0 );
		
		
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
