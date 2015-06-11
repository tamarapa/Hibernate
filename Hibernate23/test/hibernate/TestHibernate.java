package hibernate;

import static org.junit.Assert.*;
import junitPaq.Operaciones;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistencia.DAO.EmployeesDAO;


public class TestHibernate {

	public static EmployeesDAO tester;

	@AfterClass
	public static void finClase ()
	{
		tester = null;
	}
	@BeforeClass
	public static void iniciaClase ()
	{
		tester = new EmployeesDAO();
	}
	
	@Test
	public void testSuma() {
		assertEquals("5 MAS 3 = 8", 8, tester.suma(5, 3));
	}

	@Test
	public void testMultiplicacion() {
		assertEquals("5 POR 3 = 15", 15, tester.multiplicacion(5, 3));
	}

	@Test
	public void testDivision() {
		assertEquals("10 ENTRE 2 = 5", 5, tester.division(10, 2));
	}

	@Test
	public void testResta() {
		assertEquals("5 MENOS 3 = 2", 2, tester.resta(5, 3));
	}

}
