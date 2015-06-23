package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistencia.DAO.EmployeesDAO;
import persistencia.DAO.SessionManager;
import persistencia.DAO.SuperClassDAO;

import dominioDTO.Employees;
import dominioDTO.Regions;


public class EmployeesService {
	EmployeesDAO eDAO = null;
	private final static Logger log = Logger.getLogger("logTami");
	public EmployeesService ()
	{			
		this.eDAO = new EmployeesDAO();
		log.trace("EmployeesService - employeesService()");
	}
	
	// aqui creo métodos que no accedan directamente a la bd
	
	public List<Employees> mostrarTodos()
	{
		log.trace("EmployeesService - mostrarTodos() - INICIO");
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		List<Employees> list = null;
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		list = eDAO.readAll();
    		Iterator<Employees> it = list.iterator();
			Employees emp;
			log.info("Mostrando empleados...");
			while (it.hasNext())
			{
				emp = it.next();
				System.out.println(emp.getFirstName()+" "+ emp.getLastName());
			}
			
    		//transaction.commit();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		log.error("Employee");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("mostrarTodos() FIN");
    	}
		return list;
	}
	
	public List<Employees> obtenerEmpleadosPorDepartamento(BigDecimal dep_id)
	{
		log.trace("EmployeesService - obtenerEmpleadosPorDepartamento() - INICIO");
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		List<Employees> list = null;
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		list = eDAO.readAllPorDepartamento(dep_id);
			log.info("obtenerEmpleadosPorDepartamento...");
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		log.error("obtenerEmpleadosPorDepartamento");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("obtenerEmpleadosPorDepartamento() FIN");
    	}
		return list;
	}
	
	public boolean incrementarSalario(BigDecimal incr)
	{
		log.trace("EmployeesService - incrementarSalario() INICIO");
		boolean todoOK = false;
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
			List<Employees> list = eDAO.readAll();
			Iterator<Employees> it = list.iterator();
			Employees emp;
			log.info("Incrementando el salario de los empleados...");
			while (it.hasNext())
			{
				emp = it.next();
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
    		log.error("EmployeesService - incrementarSalario() ERROR");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("EmployeesService - incrementarSalario() FIN");
    	}
		return todoOK;
	}	

	//lista de empleados con el empleado mejor parado de cada departameto
	public List<Employees> obtenerEmpleadosMejorPagados()
	{
		//departamentos a los que pertenecen los empleados (select distinct department_id from employees)
		//recorrer esta lista y utilizar ese department_id para seleccionar el salario máximo de cada departamento
		log.trace("EmployeesService - listarEmpleadosMejorPagados() - INICIO");
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		List<Employees> listaEmpleadosMejorPagados = new ArrayList<Employees>();
		List<Employees> listaEmpleadosPorDepartamento = null;
		List<BigDecimal> list = null;
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		list = eDAO.listarDistinctDepartamentsFromEmployees();
    		
    		Iterator<BigDecimal> it = list.iterator();
    		BigDecimal dep_id;
			log.info("Listando a los empleados mejor pagados...");
			while (it.hasNext())
			{
				dep_id = it.next();
				if (!(dep_id == null))
				{
					log.trace("EmployeesService - listarEmpleadosMejorPagados() - DPTO:"+dep_id.intValue());
					
					listaEmpleadosPorDepartamento = eDAO.readAllPorDepartamento(dep_id);
					Iterator<Employees> it_e = listaEmpleadosPorDepartamento.iterator();
					BigDecimal salarioMaximo = new BigDecimal(0);
					Employees empleadoMejorPagado = null;
					while (it_e.hasNext()) {						
						Employees employee = (Employees) it_e.next();
						if (employee.getSalary().intValue()>salarioMaximo.intValue())
						{
							salarioMaximo = employee.getSalary();	
							empleadoMejorPagado = employee;
						}				
					}
					listaEmpleadosMejorPagados.add(empleadoMejorPagado);
				}
			}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		log.error("ERROR al obtener empleados mejor pagados por departamento");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("obtenerEmpleadosMejorPagados() FIN");
    	}
		return listaEmpleadosMejorPagados;
	}
	
	public List<Employees> obtenerEmpleadosMejorPagadosPorDepartamento1Query()
	{
		log.trace("EmployeesService - obtenerEmpleadosMejorPagadosPorDepartamento1Query() INICIO");
		List<Employees> listaEmpleadosMejorPagadosPorDepartamento = null;
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
			listaEmpleadosMejorPagadosPorDepartamento = eDAO.readAllEmpleadosMejorPagadosPorDepartamento();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		log.error("EmployeesService - obtenerEmpleadosMejorPagadosPorDepartamento1Query() ERROR");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("EmployeesService - obtenerEmpleadosMejorPagadosPorDepartamento1Query() FIN");
    	}
		return listaEmpleadosMejorPagadosPorDepartamento;
	}

	public Employees obtenerEmpleado(int id)
	{
		Employees e1 = null;
		log.trace("EmployeesService - obtenerEmpleado() - INICIO");
		Transaction transaction = null;
		Session s = SessionManager.obtenerSesionNueva();
		try 
    	{			
			eDAO.setS(s);
    		transaction = s.beginTransaction();
    		e1 = eDAO.read(id);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    		log.error("obtenerEmpleado");
    	}
    	finally 
    	{
    		SessionManager.cerrarSession(s);
    		log.trace("obtenerEmpleado() FIN");
    	}
		return e1;
		
	}
}
