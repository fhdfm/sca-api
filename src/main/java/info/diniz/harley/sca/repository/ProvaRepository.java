package info.diniz.harley.sca.repository;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import info.diniz.harley.sca.model.Prova;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

	@Query("select o from Prova o where o.aluno.id = :idAluno and o.disciplina.id = :idDisciplina order by o.data asc")
	List<Prova> recuperarPorAluno(@Param("idDisciplina") Long idDisciplina, @Param("idAluno") Long idAluno);

	@Query("select distinct(o.data) from Prova o where o.disciplina.id = :idDisciplina order by o.data asc")
	List<Date> recuperarDatasProva(@Param("idDisciplina") Long idDisciplina);
	
	@Query("select count(o.id) from Prova o where o.disciplina.id = :idDisciplina and o.data = :data and o.nota is not null")
	Long qtdNotasLancadas(@Param("idDisciplina") Long idDisciplina, @PathParam("data") Date data);
	
	@Query("select o from Prova o where o.disciplina.id = :idDisciplina and o.data = :data")
	List<Prova> recuperarAlunosPorDisciplinaAndProva(@Param("idDisciplina") Long idDisciplina, @PathParam("data") Date data);
	
}
