package info.diniz.harley.sca.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.diniz.harley.sca.model.Usuario;
import info.diniz.harley.sca.repository.UsuarioRepository;
import info.diniz.harley.sca.security.UserPrincipal;


@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByMatricula(matricula).orElseThrow(
				() -> new UsernameNotFoundException("Usuário não encontrado: " + matricula));

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		Usuario user = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + id));

		return UserPrincipal.create(user);
	}
	
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
}
