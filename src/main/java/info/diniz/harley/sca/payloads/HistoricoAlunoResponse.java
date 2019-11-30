package info.diniz.harley.sca.payloads;

import java.util.List;

public class HistoricoAlunoResponse {

	private String nome;
	private String matricula;
	
	private List<DisciplinaAlunoResponse> disciplinas;

	public List<DisciplinaAlunoResponse> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaAlunoResponse> disciplinas) {
		this.disciplinas = disciplinas;
	}

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
}
