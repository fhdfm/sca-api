package info.diniz.harley.sca.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.diniz.harley.sca.model.Prova;
import info.diniz.harley.sca.payloads.AlunoResponse;
import info.diniz.harley.sca.payloads.DataResponse;
import info.diniz.harley.sca.repository.ProvaRepository;
import info.diniz.harley.sca.service.ProvaService;

@Service
public class ProvaServiceImpl implements ProvaService {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private ProvaRepository provaRepository;
	
	@Override
	public List<DataResponse> findDatas(Long idDisciplina) {
		
		
		List<DataResponse> result = new ArrayList<DataResponse>();
		
		List<Date> datas = provaRepository.recuperarDatasProva(idDisciplina);
		if (datas != null && !datas.isEmpty()) {
			for (Date data : datas) {
				DataResponse dataResponse = new DataResponse();
				dataResponse.setData(sdf.format(data));
				dataResponse.setQtdNotas(provaRepository.qtdNotasLancadas(idDisciplina, data));
				result.add(dataResponse);
			}
		}
		
		return result;
	}
	
	@Override
	public void lancarNotas(List<AlunoResponse> notas) {
		if (notas != null && !notas.isEmpty()) {
			for (AlunoResponse nota : notas) {
				if (nota.getNota() != null && !nota.getNota().trim().isEmpty()) {
					Double notaValendo = new Double(nota.getNota().replace(",", "."));
					Prova prova = provaRepository.getOne(nota.getId());
					if (prova != null) {
						prova.setNota(notaValendo);
						provaRepository.save(prova);
					}
				}
			}
		}
	}

	@Override
	public List<AlunoResponse> findAlunosPorDisciplinaAndProva(Long idDisciplina, String dataStr) {
		
		List<AlunoResponse> result = new ArrayList<AlunoResponse>();
		
		Date data = null;
		try {
			data = sdf.parse(dataStr);
		} catch (ParseException e) {}
		
		List<Prova> provas = provaRepository.recuperarAlunosPorDisciplinaAndProva(idDisciplina, data);
		if (provas != null && !provas.isEmpty()) {
			for (Prova prova : provas) {
				AlunoResponse obj = new AlunoResponse();
				obj.setId(prova.getId());
				obj.setNota(prova.getNota() != null ? prova.getNota().toString().replace(".", ",") : "");
				obj.setNome(prova.getAluno().getNome());
				obj.setMatricula(prova.getAluno().getMatricula());
				result.add(obj);
			}
		}
		
		return result;
	}
}
