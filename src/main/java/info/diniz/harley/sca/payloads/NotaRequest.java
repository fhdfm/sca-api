package info.diniz.harley.sca.payloads;

import java.util.List;

public class NotaRequest {
	private List<AlunoResponse> notas;

	public List<AlunoResponse> getNotas() {
		return notas;
	}

	public void setNotas(List<AlunoResponse> notas) {
		this.notas = notas;
	}
}
