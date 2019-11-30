package info.diniz.harley.sca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.diniz.harley.sca.model.Perfil;
import info.diniz.harley.sca.model.PerfilEnum;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findByNome(PerfilEnum nome);
}
