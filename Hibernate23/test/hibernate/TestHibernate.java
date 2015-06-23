package hibernate;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dominioDTO.Employees;

import persistencia.DAO.EmployeesDAO;
import persistencia.DAO.SessionManager;

import services.EmployeesService;


public class TestHibernate {

	public static EmployeesService tester;

	@AfterClass
	public static void finClase ()
	{
		tester = null;
	}
	@BeforeClass
	public static void iniciaClase ()
	{
		tester = new EmployeesService();
	}
	
	@Test
	public void testMostrarTodos() {
		assertNotNull("Error - Mostrar Todos: ", tester.mostrarTodos());
	}
	
	@Test
	public void testIncrementarSalario() {
		//crear un método en employee service para obtener un empleado
		EmployeesService es = new EmployeesService();	
		
		//lee un registro de empleado y lo guarda en e1
		Employees e1 = es.obtenerEmpleado(100);
		
		//llamo a incrementarSalario			
		es.incrementarSalario(new BigDecimal(1.2));	
		
		//lee el mismo registro y lo guarda en e2
		Employees e2 = es.obtenerEmpleado(100);
		//compara que el salario de e1 * el incremento es = al salario de e2
		assertTrue("Error - Incrementar Salario: ", (e1.getSalary().intValue()*1.2)==e2.getSalary().intValue());
	}

	

}
