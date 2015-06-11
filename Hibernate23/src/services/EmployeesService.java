package services;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistencia.DAO.EmployeesDAO;
import persistencia.DAO.SessionManager;
import persistencia.DAO.SuperClassDAO;

import dominioDTO.Employees;
import dominioDTO.Regions;


public class EmployeesService {
	EmployeesDAO eDAO = null;
	public EmployeesService ()
	{	
		
		this.eDAO = new EmployeesDAO();
	}
	
	// aqui creo métodos que no accedan directamente a la bd
	
	public void mostrarTodos()
	{
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		List<Employees> list = eDAO.readAll();
    		Iterator<Employees> it = list.iterator();
			Employees emp;
			while (it.hasNext())
			{
				emp = it.next();
				//incremento el salario
				System.out.println(emp.getFirstName()+" "+ emp.getLastName());
			}
    		//transaction.commit();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    	}
	}
	public boolean insertarEmpleado(Employees emp)
	{
		boolean todoOK = false;
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		System.out.println("Voy a insertar a : " + emp.getFirstName() + " " + emp.getLastName());
    		eDAO.insert(emp);
    		transaction.commit();
			todoOK= true;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		todoOK = false;
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    	}
		return todoOK;
		
	}
	
	public boolean incrementarSalario(BigDecimal incr)
	{
		
		boolean todoOK = false;
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
			//recupero todos los empleados
			List<Employees> list = eDAO.readAll();
			Iterator<Employees> it = list.iterator();
			Employees emp;
			while (it.hasNext())
			{
				emp = it.next();
				//incremento el salario
				BigDecimal salarioNuevo = emp.getSalary().multiply(incr);
				emp.setSalary(salarioNuevo);
				System.out.println(emp.getSalary());
			}
			transaction.commit();
			todoOK= true;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		todoOK = false;
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    	}
		return todoOK;
		
		//finalizo la transacción
	}

}
