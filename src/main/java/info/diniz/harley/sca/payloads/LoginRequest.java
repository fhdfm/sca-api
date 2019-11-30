package info.diniz.harley.sca.payloads;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String matricula;

	@NotBlank
	private String password;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
