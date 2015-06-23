package dominioDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Mappings;
import org.hibernate.mapping.Set;

import persistencia.DAO.SessionManager;

import services.EmployeesService;


public class MainHibernate1 {
/**
 * método para mostrar el menú con todos los ejercicios
 */
	public static void mostrarMenu()
	{
		System.out.println("\n***************** MENÚ ********************************************");
		System.out.println("***** 1. EJ. 1: incrementarSalario");
		System.out.println("***** 2. mostrarTodos");
		System.out.println("***** 3. EJ. 2: obtenerEmpleadosMejorPagadosPorDepartamento1Query");
		System.out.println("***** 4. EJ. 2: obtenerEmpleadosMejorPagados");
		System.out.println("***** 5. EJ. 3: obtenerEmpleadosPorDepartamento");
		System.out.println("*******************************************************************");
	}
	
	/**
	 * OPCIÓN 3: método que llama al método de la clase de servicios de la clase empleados, para obtener
	 * una lista con los empleados mejor pagados por cada departamento.
	 * 
	 * @param employeesService objeto de la clase servicios de la clase empleado.
	 */
	public static void obtenerEmpleadosMejorPagadosPorDepartamento1Query(EmployeesService employeesService)
	{
		List<Employees> le = employeesService.obtenerEmpleadosMejorPagadosPorDepartamento1Query();
		Iterator<Employees> it = le.iterator();
		int count = 1;
		while (it.hasNext()) {
			Employees e = (Employees) it.next();
			System.out.println(count + " Empleado: "+ e.getFirstName() + " // Salario: " + e.getSalary());
			count++;
		}
	}
	
	/**
	 * OPCIÓN 4: método que hace la llamada al método de la clase de servicios para obtener una lista de los
	 * empleados mejor pagados.
	 * 
	 * @param employeesService objeto de la clase servicios de la clase empleado.
	 */
	public static void obtenerEmpleadosMejorPagados(EmployeesService employeesService)
	{
		List<Employees> le = employeesService.obtenerEmpleadosMejorPagados();
		Iterator<Employees> it = le.iterator();
		int count = 1;
		while (it.hasNext()) {
			Employees e = (Employees) it.next();
			System.out.println(count + " Empleado: "+ e.getFirstName() + " // Salario: " + e.getSalary());
			count++;
		}
	}
	
	/**
	 * OPCIÓN 5: 
	 * @param employeesService objeto de la clase servicios de la clase empleado.
	 */
	
	public static void obtenerEmpleadosPorDepartamento(EmployeesService employeesService)
	{
		Scanner sc_dpto = new Scanner(System.in);
		BigDecimal dpto = new BigDecimal(sc_dpto.nextInt());
		List<Employees> listaEmpleadosPorDepartamento = employeesService.obtenerEmpleadosPorDepartamento(dpto);
		Iterator<Employees> it2 = listaEmpleadosPorDepartamento.iterator();
		int count2 = 1;
		System.out.println("DEPARTAMENTO: "+ dpto);
		while (it2.hasNext()) {
			Employees e = (Employees) it2.next();
			System.out.println(count2 + " Empleado: "+ e.getFirstName() + " // Salario: " + e.getSalary());
			count2++;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeesService employeesService = new EmployeesService();
		Scanner sc = new Scanner(System.in);
		int opcion = 100;
		do
		{
			mostrarMenu();
			System.out.print("OPCION: ");
			opcion = sc.nextInt();
			switch (opcion) {
			case 0: break;			
			case 1: employeesService.incrementarSalario(new BigDecimal(0.9));				
				break;				
			case 2: employeesService.mostrarTodos();			
				break;				
			case 3: obtenerEmpleadosMejorPagados(employeesService);			
				break;
			case 4: obtenerEmpleadosMejorPagadosPorDepartamento1Query(employeesService);			
				break;
			case 5: obtenerEmpleadosPorDepartamento(employeesService);
				break;
			default: opcion = 100;
				break;
			}			
		}while (opcion!=0);
		
		SessionManager.getSessionFactory().close();
		
    	/**
    	 * TAM: recupero los empleados del departamento de ventas
    	 */
    	/*    	
    	try 
    	{
    		transaction = s.beginTransaction();
    		@SuppressWarnings("unchecked")    		
    		
    		//List<Employees> list = s.createSQLQuery("select * from employees where department_id=80").addEntity(Employees.class).list();
    		List<Regions> list = s.createSQLQuery("select * from regions").addEntity(Regions.class).list();
    		
    		Iterator<Regions> it = list.iterator();
    		Regions rg;
    		while (it.hasNext())
    		{
    			rg = it.next();
    			
    			//Operaciones.modificarSalario(emp, new BigDecimal(1.2));
    			System.out.println(rg.getRegionName());
    			HashSet c = new HashSet() ;
    			c.addAll(rg.getCountrieses());
    			Iterator i = c.iterator();
    			while(i.hasNext()){
    				  Countries pais = (Countries) i.next();
    				  System.out.println("---"+pais.getCountryName());
    				}
    			//System.out.println();
    			//s.saveOrUpdate(emp);
    		}    		
    		transaction.commit();//si todo ha ido bien, persisto los cambio, los hago de verdad, no en la copia de la BD
    		
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		transaction.rollback();//si algo ha ido mal, deshago la transacción
    	}
    	finally 
    	{
    		SessionManager.cerrarConexion(f, s);
    	}
    	*/
    	

	}

}
