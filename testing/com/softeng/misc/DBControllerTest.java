package com.softeng.misc;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBControllerTest {
	

	

		@BeforeClass
		public static void testSetup(){
			System.out.println("Starting...");
		}
		
		
		@AfterClass 
		public static void testCleanup(){
			System.out.println("Done!"); 
		}
		
		@Test
		public void testgetRoom() {
			DBController test = new DBController();
			
			
			assertEquals("Result",1,test.getRoomWithId(1, 1).getId());
		}
		@Test
		public void testaddReservation() {
			DBController test = new DBController();
			Reservation[] resDate = test.getDate();
			 
				
			
			
			assertEquals("Result",67,test.addReservation(1, resDate[1].getDateStart(), resDate[1].getDateEnd(), 67,31, "active").getCustomerId());
		}
		
		
	@Test
	public void testSignupCustomer() {
		DBController test = new DBController();
		
		String staff = test.authenticate("stelios", "ntou").getUsername();
		
		assertEquals("Result",staff,test.authenticate("stelios", "ntou").getUsername());
	}

}
