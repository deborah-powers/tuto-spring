package model;

import lombok.Data;

@Data	// annotation de lombock pour replacer les getters et setters
public class EmployeeDst{
    private String firstName;
    private String lastName;
    private String mail;
    private String password;

	public String toCsvLine() {
		return firstName +";"+ lastName +";"+ mail +";"+ password +"\n";
	}
}
