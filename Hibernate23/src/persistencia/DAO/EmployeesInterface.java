package persistencia.DAO;


import java.math.BigDecimal;
import java.util.List;

import dominioDTO.Employees;
/**
 * Interface para la clase empleado que define los métodos que la clase que la implemente
 * deberá sobreescribir.
 * 
 * @author alumno
 *
 */
public interface EmployeesInterface {

	public List<BigDecimal> listarDistinctDepartamentsFromEmployees();
	public List<Employees> readAllEmpleadosMejorPagadosPorDepartamento();	
	public List<Employees> readAll();
	public List<Employees> readAllPorDepartamento(BigDecimal dpto_id);
	public void insert(Employees emp);
	public Employees read(int i);
}
