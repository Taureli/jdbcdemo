package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Before;

import com.example.jdbcdemo.domain.Pick;
import com.example.jdbcdemo.domain.Order;

public class PickManagerTest {
	
	
	PickManager pickManager = new PickManager();
	OrderManager orderManager = new OrderManager();
	
	private final static String COMPANY_1 = "Dunlop";
	private final static String NAME_1 = "Big Stubby";
	private final static double MILIMETERS_1 = 1.0;
	private final static double PRICE_1 = 2.38;
	
	private final static String COMPANY_2 = "Twin Picks";
	private final static String NAME_2 = "Acoustic Hard";
	private final static double MILIMETERS_2 = 0.71;
	private final static double PRICE_2 = 8.20;
	
	private final static String CUSTOMER_1 = "RocknRoll";
	private final static long IDPICK_1 = 1;
	private final static int AMOUNT_1 = 10;
	
	private final static String CUSTOMER_2 = "Riff";
	private final static long IDPICK_2 = 2;
	private final static int AMOUNT_2 = 15;
	
	Pick pick1 = new Pick(COMPANY_1, NAME_1, MILIMETERS_1, PRICE_1);
	Pick pick2 = new Pick(COMPANY_2, NAME_2, MILIMETERS_2, PRICE_2);
	Order order1 = new Order(CUSTOMER_1, IDPICK_1, AMOUNT_1);
	Order order2 = new Order(CUSTOMER_2, IDPICK_2, AMOUNT_2);
	
	@Before
	public void before(){
		Pick pick1 = new Pick("Pickboy", "Mega Grip", 0.5, 2.95);
		Pick pick2 = new Pick("Grover", "Performance Nylon", 0.65, 2.50);
		Pick pick3 = new Pick("Pickboy", "Carbon Nylon PRO", 0.60, 3.10);
		Pick pick4 = new Pick("Dunlop", "Ultex", 1.0, 2.05);
		
		Order order1 = new Order("Sklep Akustyk", 1, 22);
		Order order2 = new Order("Riff", 1, 16);
		Order order3 = new Order("Metallica", 3, 45);
		Order order4 = new Order("Jan Kowalski", 2, 3);
		
		pickManager.clearPicks();
		pickManager.addPicks(pick1);
		pickManager.addPicks(pick2);
		pickManager.addPicks(pick3);
		pickManager.addPicks(pick4);
		
		orderManager.clearOrders();
		orderManager.addOrders(order1);
		orderManager.addOrders(order2);
		orderManager.addOrders(order3);
		orderManager.addOrders(order4);
		
	}
	
	@Test
	public void checkConnectionPick(){
		assertNotNull(pickManager.getConnection());
	}
	
	@Test
	public void checkConnectionOrder(){
		assertNotNull(orderManager.getConnection());
	}
	
	@Test
	public void checkAddReadPick(){
		
		pickManager.clearPicks();
		assertEquals(1,pickManager.addPicks(pick1));
		assertEquals(1,pickManager.addPicks(pick2));
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		
		assertEquals(COMPANY_1, pickRetrieved.getCompany());
		assertEquals(NAME_1, pickRetrieved.getName());
		assertEquals(MILIMETERS_1, pickRetrieved.getMilimeters(), 5);
		assertEquals(PRICE_1, pickRetrieved.getPrice(), 5);
		
		pickRetrieved = picks.get(1);
		
		assertEquals(COMPANY_2, pickRetrieved.getCompany());
		assertEquals(NAME_2, pickRetrieved.getName());
		assertEquals(MILIMETERS_2, pickRetrieved.getMilimeters(), 5);
		assertEquals(PRICE_2, pickRetrieved.getPrice(), 5);
		
	}
	
	@Test
	public void checkAddReadOrder(){
		
		orderManager.clearOrders();
		assertEquals(1,orderManager.addOrders(order1));
		assertEquals(1,orderManager.addOrders(order2));
		
		List<Order> orders = orderManager.getAllOrders();
		Order orderRetrieved = orders.get(0);
		
		assertEquals(CUSTOMER_1, orderRetrieved.getCustomer());
		assertEquals(IDPICK_1, orderRetrieved.getIdPick());
		assertEquals(AMOUNT_1, orderRetrieved.getAmount());
		
		orderRetrieved = orders.get(1);
		
		assertEquals(CUSTOMER_2, orderRetrieved.getCustomer());
		assertEquals(IDPICK_2, orderRetrieved.getIdPick());
		assertEquals(AMOUNT_2, orderRetrieved.getAmount());
		
	}
	
	@Test
	public void checkClear(){
		
		pickManager.clearPicks();
		List<Pick> picks = pickManager.getAllPicks();
		assertEquals(0, picks.size());
		
	}
	
	@Test
	public void checkIDRemove(){
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		
		int pom = picks.size() - 1;
		
		assertEquals(1, pickManager.DeleteID(pickRetrieved.getId()));
		
		picks = pickManager.getAllPicks();
		assertEquals(pom, picks.size());
	}
	
	@Test
	public void checkUpdate(){
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		
		assertEquals(1, pickManager.UpdateCompany( "TEST", pickRetrieved.getId()));
	}

	@Test
	public void checkSelectID(){
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		
		assertNotNull(pickManager.SelectID(pickRetrieved.getId()));
	}
	
}
