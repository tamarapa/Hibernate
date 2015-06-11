package dominioDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeesService employeesService = new EmployeesService();
		
		employeesService.incrementarSalario(new BigDecimal(1.2));
		employeesService.mostrarTodos();
		
    	
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
