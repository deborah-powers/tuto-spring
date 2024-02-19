package tuto;

import lombok.Data;

/* équivalent de rhapi.EmployeeModel
 * certains éléments peuvent être supprimés, comme password
 */
@Data
public class EmployeeModel {
	private Integer id;
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
}
