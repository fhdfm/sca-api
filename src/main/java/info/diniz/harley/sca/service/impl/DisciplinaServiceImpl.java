package info.diniz.harley.sca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import info.diniz.harley.sca.model.Disciplina;
import info.diniz.harley.sca.payloads.DisciplinaResponse;
import info.diniz.harley.sca.repository.DisciplinaRepository;
import info.diniz.harley.sca.service.DisciplinaService;
import info.diniz.harley.sca.service.ProvaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ProvaService provaService;
	
	@Override
	public List<DisciplinaResponse> findAll() {
		
		List<DisciplinaResponse> result = new ArrayList<DisciplinaResponse>();
		
		List<Disciplina> disciplinas = disciplinaRepository.findAll(Sort.by(Order.asc("descricao")));
		if (disciplinas != null && !disciplinas.isEmpty()) {
			for (Disciplina disciplina : disciplinas) {
				DisciplinaResponse obj = new DisciplinaResponse();
				obj.setId(disciplina.getId());
				obj.setDescricao(disciplina.getDescricao());
				obj.setDatas(provaService.findDatas(disciplina.getId()));
				result.add(obj);
			}
		}
		
		return result;
	}

	@Override
	public Disciplina buscarPorId(Long id) {
		return disciplinaRepository.findById(id).get();
	}

}
