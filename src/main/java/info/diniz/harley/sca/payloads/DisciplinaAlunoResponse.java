package info.diniz.harley.sca.payloads;

import java.util.List;

public class DisciplinaAlunoResponse {

	private Long id;
	
	private String disciplina;
	
	private List<NotaAlunoResponse> notas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public List<NotaAlunoResponse> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaAlunoResponse> notas) {
		this.notas = notas;
	}
		
}
