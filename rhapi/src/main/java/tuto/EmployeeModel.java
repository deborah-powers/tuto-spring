package tuto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data	// annotation de lombock pour replacer les getters et setters. cette classe est un bean.
@Entity	// lien entre cette classe et la bdd
@Table(name = "employee")	// nom de la table associée à ce bean
public class EmployeeModel {
	@Id	// clef primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)	// id est auto-incrémenté dans la bdd
    private Long id;
    @Column(name="first_name")	// la colonne et le champ ont des noms différents
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String mail;	// la colonne et le champ ont des noms identiques
    private String password;
}
