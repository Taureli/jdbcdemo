package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Before;

import com.example.jdbcdemo.domain.Pick;
import com.example.jdbcdemo.domain.Zamowienie;

public class PickManagerTest {

	PickManager pickManager = new PickManager();
	ZamowienieManager zamowienieManager = new ZamowienieManager();

	private final static String COMPANY_1 = "Dunlop";
	private final static String NAME_1 = "Big Stubby";
	private final static double MILIMETERS_1 = 1.0;
	private final static double PRICE_1 = 2.38;

	private final static String COMPANY_2 = "Twin Picks";
	private final static String NAME_2 = "Acoustic Hard";
	private final static double MILIMETERS_2 = 0.71;
	private final static double PRICE_2 = 8.20;

	private final static String CUSTOMER_1 = "RocknRoll";
	private final static int AMOUNT_1 = 10;

	private final static String CUSTOMER_2 = "Riff";
	private final static int AMOUNT_2 = 15;

	Pick pick1 = new Pick(COMPANY_1, NAME_1, MILIMETERS_1, PRICE_1);
	Pick pick2 = new Pick(COMPANY_2, NAME_2, MILIMETERS_2, PRICE_2);
	Zamowienie zamowienie1 = new Zamowienie(CUSTOMER_1, AMOUNT_1);
	Zamowienie zamowienie2 = new Zamowienie(CUSTOMER_2, AMOUNT_2);

	@Before
	public void before() {
		Pick pick1 = new Pick("Pickboy", "Mega Grip", 0.5, 2.95);
		Pick pick2 = new Pick("Grover", "Performance Nylon", 0.65, 2.50);
		Pick pick3 = new Pick("Pickboy", "Carbon Nylon PRO", 0.60, 3.10);
		Pick pick4 = new Pick("Dunlop", "Ultex", 1.0, 2.05);

		Zamowienie zamowienie1 = new Zamowienie("Sklep Akustyk", 22);
		Zamowienie zamowienie2 = new Zamowienie("Riff", 16);
		Zamowienie zamowienie3 = new Zamowienie("Metallica", 45);
		Zamowienie zamowienie4 = new Zamowienie("Jan Kowalski", 3);

		pickManager.clearPicks();
		pickManager.addPicks(pick1);
		pickManager.addPicks(pick2);
		pickManager.addPicks(pick3);
		pickManager.addPicks(pick4);

		zamowienieManager.clearZamowienies();
		zamowienieManager.addZamowienies(zamowienie1);
		zamowienieManager.addZamowienies(zamowienie2);
		zamowienieManager.addZamowienies(zamowienie3);
		zamowienieManager.addZamowienies(zamowienie4);

	}

	// -----------CONNECTION TESTS--------------
	@Test
	public void checkConnectionPick() {
		assertNotNull(pickManager.getConnection());
	}

	@Test
	public void checkConnectionZamowienie() {
		assertNotNull(zamowienieManager.getConnection());
	}

	// ------------PICK TESTS----------------
	@Test
	public void checkAddReadPick() {

		pickManager.clearPicks();
		assertEquals(1, pickManager.addPicks(pick1));
		assertEquals(1, pickManager.addPicks(pick2));

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
	public void checkClearPick() {

		pickManager.clearPicks();
		List<Pick> picks = pickManager.getAllPicks();
		assertEquals(0, picks.size());

	}

	@Test
	public void checkIDRemovePick() {

		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);

		int pom = picks.size() - 1;

		assertEquals(1, pickManager.DeleteID(pickRetrieved.getId()));

		picks = pickManager.getAllPicks();
		assertEquals(pom, picks.size());
	}

	@Test
	public void checkUpdatePick() {

		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);

		assertEquals(1,
				pickManager.UpdateCompany("TEST", pickRetrieved.getId()));
	}

	@Test
	public void checkSelectIDPick() {

		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);

		assertNotNull(pickManager.SelectID(pickRetrieved.getId()));
	}

	// -----------ORDER TESTS-----------
	@Test
	public void checkAddReadOrder() {

		zamowienieManager.clearZamowienies();
		assertEquals(1, zamowienieManager.addZamowienies(zamowienie1));
		assertEquals(1, zamowienieManager.addZamowienies(zamowienie2));

		List<Zamowienie> zamowienies = zamowienieManager.getAllZamowienies();
		Zamowienie zamowienieRetrieved = zamowienies.get(0);

		assertEquals(CUSTOMER_1, zamowienieRetrieved.getCustomer());
		assertEquals(AMOUNT_1, zamowienieRetrieved.getAmount());

		zamowienieRetrieved = zamowienies.get(1);

		assertEquals(CUSTOMER_2, zamowienieRetrieved.getCustomer());
		assertEquals(AMOUNT_2, zamowienieRetrieved.getAmount());

	}

	@Test
	public void checkClearOrder() {

		zamowienieManager.clearZamowienies();
		List<Zamowienie> zamowienia = zamowienieManager.getAllZamowienies();
		assertEquals(0, zamowienia.size());

	}

	@Test
	public void checkSelectIDOrder() {

		List<Zamowienie> zamowienia = zamowienieManager.getAllZamowienies();
		Zamowienie zamowienieRetrieved = zamowienia.get(0);

		assertNotNull(zamowienieManager.SelectID(zamowienieRetrieved.getId()));
	}

	@Test
	public void checkAddPickToOrder() {

		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		int idPick = pickRetrieved.getId();

		List<Zamowienie> zamowienia = zamowienieManager.getAllZamowienies();
		Zamowienie zamowienieRetrieved = zamowienia.get(0);
		int idOrder = zamowienieRetrieved.getId();

		assertEquals(1, zamowienieManager.addPick(idPick, idOrder));
		//assertEquals(idPick, (zamowienieManager.SelectID(idOrder)).getIdPick());

	}

	@Test
	public void checkDeletePickFromOrder() {
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		int idPick = pickRetrieved.getId();

		List<Zamowienie> zamowienia = zamowienieManager.getAllZamowienies();
		Zamowienie zamowienieRetrieved = zamowienia.get(0);
		int idOrder = zamowienieRetrieved.getId();

		assertEquals(1, zamowienieManager.addPick(idPick, idOrder));
		assertEquals(1, zamowienieManager.deletePick(idPick));
		assertEquals(0, zamowienieManager.SelectID(idOrder).getIdPick());
		
	}

	@Test
	public void checkGetAllZamWherePick() {
		
		List<Pick> picks = pickManager.getAllPicks();
		Pick pickRetrieved = picks.get(0);
		int idPick = pickRetrieved.getId();

		List<Zamowienie> zamowienia = zamowienieManager.getAllZamowienies();
		Zamowienie zamowienieRetrieved = zamowienia.get(0);
		int idOrder1 = zamowienieRetrieved.getId();

		zamowienieRetrieved = zamowienia.get(1);
		int idOrder2 = zamowienieRetrieved.getId();

		zamowienieRetrieved = zamowienia.get(2);
		int idOrder3 = zamowienieRetrieved.getId();
		
		assertEquals(1, zamowienieManager.addPick(idPick, idOrder1));
		assertEquals(1, zamowienieManager.addPick(idPick, idOrder2));
		assertEquals(1, zamowienieManager.addPick(idPick, idOrder3));

		assertEquals(3, zamowienieManager.getAllZamWherePick(idPick).size());
		
	}

}
