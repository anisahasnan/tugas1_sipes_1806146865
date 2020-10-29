package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long>{
    Optional<PesawatModel> findById(Long id);
}
