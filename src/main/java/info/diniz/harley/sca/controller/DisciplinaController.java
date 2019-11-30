package info.diniz.harley.sca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.diniz.harley.sca.model.Usuario;
import info.diniz.harley.sca.payloads.DisciplinaMasterResponse;
import info.diniz.harley.sca.payloads.DisciplinaResponse;
import info.diniz.harley.sca.security.JwtTokenProvider;
import info.diniz.harley.sca.service.DisciplinaService;
import info.diniz.harley.sca.service.impl.UsuarioService;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

	@Autowired
	private JwtTokenProvider jwtTokenProvider; 
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@GetMapping("/listar")
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<DisciplinaMasterResponse> listarDisciplinas(@RequestHeader (name="Authorization") String token) {
		Long idProfessor = jwtTokenProvider.recuperarIdUsuario(token);
		Optional<Usuario> usuario = usuarioService.findById(idProfessor);
		List<DisciplinaResponse> disciplinas = disciplinaService.findAll();
		DisciplinaMasterResponse result = new DisciplinaMasterResponse();
		if (usuario.isPresent()) {
			result.setDisciplinas(disciplinas);
			result.setMatricula(usuario.get().getMatricula());
			result.setNome(usuario.get().getNome());
		}
		return ResponseEntity.accepted().body(result);
	}
	
}
