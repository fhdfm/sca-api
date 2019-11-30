package info.diniz.harley.sca.payloads;

import java.util.List;

public class AlunoMasterResponse {

	private String matricula;
	private String nome;
	private Long idDisciplina;
	private String nomeDisciplina;
	private List<AlunoResponse> alunos;
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public List<AlunoResponse> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<AlunoResponse> alunos) {
		this.alunos = alunos;
	}	
}
