package dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.epam.yalexeyenko.validation.PasswordMatches;
import com.epam.yalexeyenko.validation.ValidEmail;

@PasswordMatches
public class UserDTO implements Dto {
	private Long id;

	@NotNull(message = "NotNull.userDTO.firstName")
	@NotEmpty(message = "NotEmpty.userDTO.firstName")
	private String firstName;

	@NotNull(message = "NotNull.userDTO.lastName")
	@NotEmpty(message = "NotEmpty.userDTO.lastName")
	private String lastName;

	@ValidEmail(message = "ValidEmail.userDTO.email")
	@NotNull(message = "NotNull.userDTO.email")
	@NotEmpty(message = "NotEmpty.userDTO.email")
	private String email;

	@NotNull(message = "NotNull.userDTO.password")
	@NotEmpty(message = "NotEmpty.userDTO.password")
	private String password;
	private String matchingPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
