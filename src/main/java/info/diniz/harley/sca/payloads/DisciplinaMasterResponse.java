package info.diniz.harley.sca.payloads;

import java.util.List;

public class DisciplinaMasterResponse {

	private String nome;
	private String matricula;
	private List<DisciplinaResponse> disciplinas;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public List<DisciplinaResponse> getDisciplinas() {
		return disciplinas;
	}
	
	public void setDisciplinas(List<DisciplinaResponse> disciplinas) {
		this.disciplinas = disciplinas;
	}	
}
