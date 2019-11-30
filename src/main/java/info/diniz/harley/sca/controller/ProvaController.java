package info.diniz.harley.sca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.diniz.harley.sca.model.Disciplina;
import info.diniz.harley.sca.model.Usuario;
import info.diniz.harley.sca.payloads.AlunoMasterResponse;
import info.diniz.harley.sca.payloads.AlunoResponse;
import info.diniz.harley.sca.payloads.NotaRequest;
import info.diniz.harley.sca.security.JwtTokenProvider;
import info.diniz.harley.sca.service.DisciplinaService;
import info.diniz.harley.sca.service.ProvaService;
import info.diniz.harley.sca.service.impl.UsuarioService;

@RestController
@RequestMapping("/api/provas")
public class ProvaController {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProvaService provaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@GetMapping("/alunosPorProva")
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<AlunoMasterResponse> listarAlunosPorProva(@RequestHeader(name="Authorization") String token, @RequestParam("idDisciplina") Long idDisciplina, @RequestParam("dataProva") String dataProva) {
		Long idProfessor = jwtTokenProvider.recuperarIdUsuario(token);
		Optional<Usuario> usuario = usuarioService.findById(idProfessor);
		
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		List<AlunoResponse> alunos = provaService.findAlunosPorDisciplinaAndProva(idDisciplina, dataProva);
		
		AlunoMasterResponse result = new AlunoMasterResponse();
		result.setAlunos(alunos);
		
		if (usuario.isPresent()) {
			result.setIdDisciplina(idDisciplina);
			result.setMatricula(usuario.get().getMatricula());
			result.setNome(usuario.get().getNome());
			result.setNomeDisciplina(disciplina.getDescricao());
		}
		
		return ResponseEntity.accepted().body(result);
	}
	
	@RequestMapping(value = "/publicar", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PROFESSOR')")
	public int lancarNotas(@RequestBody NotaRequest notasRequest) {
		try {
			provaService.lancarNotas(notasRequest.getNotas());
			return 1;
		} catch (Exception e) {
			
		}
		return 0;
	}
	
}
