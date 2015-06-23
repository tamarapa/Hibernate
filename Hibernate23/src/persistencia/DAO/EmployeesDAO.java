package persistencia.DAO;


import java.math.BigDecimal;
import java.util.List;

import dominioDTO.Employees;

/**
 * Clase del tipo empleado que define los métodos que acceden a la base de datos directamente.
 * @author alumno
 *
 */
public class EmployeesDAO extends SuperClassDAO implements EmployeesInterface {
	
	/**
	 * Método constructor de la clase.
	 */
	public EmployeesDAO()
	{
		
	}

	/**
	 * Método para obtener una lista con los distintos departamentos a los que pertenecen los empleados
	 * de la tabla empleados.
	 * 
	 * @return Lista del tipo BigDecimal que representa el id de cada departamento.
	 */
	public List<BigDecimal> listarDistinctDepartamentsFromEmployees()
	{
		@SuppressWarnings("unchecked")
		List<BigDecimal> listaDepartamentos = getS().createSQLQuery("SELECT DISTINCT DEPARTMENT_ID FROM Employees").list();
		return listaDepartamentos;
	}

	/**
	 * Método para obtener una lista con los empleados mejor pagados por cada departamento.
	 * 
	 * @return Devuelve una lista de objetos de tipo empleado.
	 */
	public List<Employees> readAllEmpleadosMejorPagadosPorDepartamento()
	{
		@SuppressWarnings("unchecked")
		List<Employees> listaEmpleados = getS().createSQLQuery("select * from employees where (salary, department_id) in (select max(salary), department_id from employees group by department_id)").addEntity(Employees.class).list();
		return listaEmpleados;
	}
	
	/**
	 * Método que devuelve una lista con todos los empleados.
	 * 
	 * @return Devuelve una lista de objetos de tipo empleado.
	 */
	public List<Employees> readAll()
	{
		@SuppressWarnings("unchecked")
		List<Employees> listaEmpleados = getS().createSQLQuery("select * from employees").addEntity(Employees.class).list();
		return listaEmpleados;
	}
	
	/**
	 * Método que devuelve los empleados que pertenecen a un departamento.
	 * 
	 * @param dpto_id Identificador del departamento .
	 * @return Devuelve una lista de objetos de tipo empleado.
	 */
	public List<Employees> readAllPorDepartamento(BigDecimal dpto_id)
	{
		@SuppressWarnings("unchecked")
		List<Employees> listaEmpleados = getS().createSQLQuery("select * from employees where department_id="+dpto_id).addEntity(Employees.class).list();
		return listaEmpleados;
	}

	/**
	 * Método para insertar un empleado.
	 * 
	 * @param emp Objeto de tipo empleado.
	 */
	public void insert(Employees emp) {
		// TODO Auto-generated method stub
		getS().save(emp);
	}

	/**
	 * Método que devuelve un empleado según el identificador pasado por parámetro.
	 * 
	 * @param i Recibe un entero que representa el identificador del empleado.
	 * @return Devuelve un objeto de tipo empleado.
	 */
	public Employees read(int i) {
		// TODO Auto-generated method stub
		Employees e = new Employees();
		e = (Employees) getS().get(Employees.class, i);
		//e = (Employees) getS().createSQLQuery("SELECT * FROM Employees where employee_id="+i).addEntity(Employees.class).uniqueResult();
		return e;
	}
}
