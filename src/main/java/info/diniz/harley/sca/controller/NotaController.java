package info.diniz.harley.sca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.diniz.harley.sca.payloads.HistoricoAlunoResponse;
import info.diniz.harley.sca.security.JwtTokenProvider;
import info.diniz.harley.sca.service.HistoricoService;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

	@Autowired
	private JwtTokenProvider jwtTokenProvider; 
	
	@Autowired
	private HistoricoService historicoService;
	
	@GetMapping("/listar")
	@PreAuthorize("hasRole('ALUNO')")
	public ResponseEntity<HistoricoAlunoResponse> recuperarNotas(@RequestHeader (name="Authorization") String token) {
		Long idAluno = jwtTokenProvider.recuperarIdUsuario(token);
		HistoricoAlunoResponse result = historicoService.recuperarNotas(idAluno);
		return ResponseEntity.accepted().body(result);
	}
	
}
