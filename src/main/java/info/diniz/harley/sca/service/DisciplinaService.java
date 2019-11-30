package info.diniz.harley.sca.service;

import java.util.List;

import info.diniz.harley.sca.model.Disciplina;
import info.diniz.harley.sca.payloads.DisciplinaResponse;


public interface DisciplinaService {

	List<DisciplinaResponse> findAll();
	Disciplina buscarPorId(Long id);
}
