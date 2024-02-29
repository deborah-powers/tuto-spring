package model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data // annotation de lombock pour replacer les getters et setters
public class EmployeeSrcList{
	private List<EmployeeSrc> employees;

	public void add(EmployeeSrc employee){ employees.add(employee); }
	public EmployeeSrc get(int pos) { return employees.get(pos); }
	public int size() { return employees.size(); }
	public EmployeeSrcList() {
		employees = new ArrayList<EmployeeSrc>();
	}
}
