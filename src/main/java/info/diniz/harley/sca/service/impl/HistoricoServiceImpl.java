package info.diniz.harley.sca.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import info.diniz.harley.sca.model.Disciplina;
import info.diniz.harley.sca.model.Prova;
import info.diniz.harley.sca.model.Usuario;
import info.diniz.harley.sca.payloads.DisciplinaAlunoResponse;
import info.diniz.harley.sca.payloads.HistoricoAlunoResponse;
import info.diniz.harley.sca.payloads.NotaAlunoResponse;
import info.diniz.harley.sca.repository.DisciplinaRepository;
import info.diniz.harley.sca.repository.ProvaRepository;
import info.diniz.harley.sca.repository.UsuarioRepository;
import info.diniz.harley.sca.service.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProvaRepository provaRepository;
	
	public HistoricoAlunoResponse recuperarNotas(Long id) {
		
		HistoricoAlunoResponse historicoAlunoResponse = new HistoricoAlunoResponse();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			historicoAlunoResponse.setNome(usuario.get().getNome());
			historicoAlunoResponse.setMatricula(usuario.get().getMatricula());
		}
		
		List<Disciplina> disciplinas = disciplinaRepository.findAll(Sort.by(Order.asc("descricao")));
		if (disciplinas != null && !disciplinas.isEmpty()) {
			List<DisciplinaAlunoResponse> disciplinasList = new ArrayList<DisciplinaAlunoResponse>();
			for (Disciplina disciplina : disciplinas) {
				DisciplinaAlunoResponse disciplinaAlunoResponse = new DisciplinaAlunoResponse();
				disciplinaAlunoResponse.setId(disciplina.getId());
				disciplinaAlunoResponse.setDisciplina(disciplina.getDescricao());
				
				List<NotaAlunoResponse> notasAluno = new ArrayList<NotaAlunoResponse>();
				
				List<Prova> provas = provaRepository.recuperarPorAluno(disciplina.getId(), id);
				if (provas != null && !provas.isEmpty()) {
					for (Prova prova : provas) {
						NotaAlunoResponse notaAlunoResponse = new NotaAlunoResponse();
						notaAlunoResponse.setId(prova.getId());
						notaAlunoResponse.setNota(prova.getNota() != null ? prova.getNota().toString().replace(".", ",") : "N/D");
						notaAlunoResponse.setData(simpleDateFormat.format(prova.getData()));
						notasAluno.add(notaAlunoResponse);
					}
				}
				
				disciplinaAlunoResponse.setNotas(notasAluno);
				disciplinasList.add(disciplinaAlunoResponse);
			}
		
			historicoAlunoResponse.setDisciplinas(disciplinasList);
		}
		
		
		return historicoAlunoResponse;
	}
	
}
