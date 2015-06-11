package persistencia.DAO;


import java.util.List;

import dominioDTO.Employees;


public class EmployeesDAO extends SuperClassDAO implements EmployeesInterface {

	public EmployeesDAO()
	{
		
	}
	
	public List<Employees> readAll()
	{
		@SuppressWarnings("unchecked")
		List<Employees> listaEmpleados = getS().createSQLQuery("select * from employees").addEntity(Employees.class).list();
		return listaEmpleados;
	}

	public void insert(Employees emp) {
		// TODO Auto-generated method stub
		getS().save(emp);
	}
}
