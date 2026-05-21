package cl.dgac.seguros.repository;

import cl.dgac.seguros.model.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {

    Optional<Seguro> findByNumeroPoliza(String numeroPoliza);

    List<Seguro> findByEstado(String estado);

    List<Seguro> findByDroneId(Long droneId);

    List<Seguro> findByEmpresaId(Long empresaId);

    @Query("SELECT s FROM Seguro s WHERE LOWER(s.aseguradora) LIKE LOWER(CONCAT('%', :aseguradora, '%'))")
    List<Seguro> buscarPorAseguradora(String aseguradora);
}