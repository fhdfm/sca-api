package info.diniz.harley.sca.service;

import java.util.List;

import info.diniz.harley.sca.payloads.AlunoResponse;
import info.diniz.harley.sca.payloads.DataResponse;

public interface ProvaService {

	List<DataResponse> findDatas(Long idDisciplina);
	List<AlunoResponse> findAlunosPorDisciplinaAndProva(Long idDisciplina, String data);
	void lancarNotas(List<AlunoResponse> notas);
}
