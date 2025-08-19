package br.com.github.gustavossobral.educational_api.domain.aluno;

import br.com.github.gustavossobral.educational_api.domain.aluno.dto.ListarAlunosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    Page<AlunoEntity> findByAtivoTrue(Pageable pageable);
}
