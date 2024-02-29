package model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data // annotation de lombock pour replacer les getters et setters
public class EmployeeDstList{
	private List<EmployeeDst> employees;

	public void add(EmployeeDst employee){ employees.add(employee); }
	public EmployeeDst get(int pos) { return employees.get(pos); }
	public int size() { return employees.size(); }
	public EmployeeDstList() {
		employees = new ArrayList<EmployeeDst>();
	}
}
