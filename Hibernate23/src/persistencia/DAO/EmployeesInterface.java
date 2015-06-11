package persistencia.DAO;


import java.util.List;

import dominioDTO.Employees;

public interface EmployeesInterface {
	
	public List<Employees> readAll();
	
}
