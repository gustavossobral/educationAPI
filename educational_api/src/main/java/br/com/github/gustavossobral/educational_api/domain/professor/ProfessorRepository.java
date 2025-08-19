package br.com.github.gustavossobral.educational_api.domain.professor;

import br.com.github.gustavossobral.educational_api.domain.professor.dto.ListarProfessoresDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Page<ListarProfessoresDTO> findByAtivoTrue(Pageable pageable);
}
