package model;

import lombok.Data;

@Data // annotation de lombock pour replacer les getters et setters
public class EmployeeSrc{
	private String firstName;
	private String lastName;

	public void fromCsvLine(String csvLine){
		String[] values = csvLine.split(";");
		this.firstName = values[0];
		this.lastName = values[1];
	}
}
