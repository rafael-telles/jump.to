package to.jump.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import to.jump.utils.Security;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "firstName")
	@NotEmpty(message = "{user.firstName.empty}")
	private String firstName;

	@Column(name = "surName")
	@NotEmpty(message = "{user.surName.empty}")
	private String surName;

	@Column(name = "email")
	@NotEmpty(message = "{user.email.empty}")
	@Pattern(message = "{user.email.pattern}", regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	@Column(name = "password")
	@NotEmpty(message = "{user.password.empty}")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void encryptPassword() {
		this.password = Security.encrypt(password);
	}
}
