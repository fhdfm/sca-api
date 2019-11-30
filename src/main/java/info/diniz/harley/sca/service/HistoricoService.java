package info.diniz.harley.sca.service;

import info.diniz.harley.sca.payloads.HistoricoAlunoResponse;

public interface HistoricoService {

	HistoricoAlunoResponse recuperarNotas(Long id);
	
}
