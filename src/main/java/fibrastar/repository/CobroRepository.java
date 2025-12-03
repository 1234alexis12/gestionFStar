package fibrastar.repository;

import fibrastar.entity.Cobro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface CobroRepository extends JpaRepository<Cobro, Long> {

    @Query("SELECT c FROM Cobro c WHERE " +
           "(:cliente IS NULL OR c.nombreCliente LIKE %:cliente%) AND " +
           "(:mes IS NULL OR c.mesConcepto = :mes) AND " +
           "(:estado IS NULL OR c.estado = :estado)")
    List<Cobro> buscarConFiltros(String cliente, String mes, String estado);

    // 1. Validar duplicados (Para crear nuevo)
    boolean existsByNombreClienteAndMesConcepto(String cliente, String mes);

    // 2. Validar duplicados (Para editar, ignorando su propio ID)
    boolean existsByNombreClienteAndMesConceptoAndIdNot(String cliente, String mes, Long id);

    // 3. Buscar cobros que ya vencieron y siguen "Pagado"
    List<Cobro> findByEstadoAndFechaVencimientoBefore(String estado, LocalDate hoy);
}