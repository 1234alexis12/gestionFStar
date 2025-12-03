package fibrastar.repository;

import fibrastar.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    // Filtro inteligente: Si el nombre está vacío, trae todo.
    @Query("SELECT p FROM Plan p WHERE (:nombre IS NULL OR p.nombre LIKE %:nombre%)")
    List<Plan> buscarPorNombre(String nombre);
}