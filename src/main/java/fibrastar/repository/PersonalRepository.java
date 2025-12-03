package fibrastar.repository;

import fibrastar.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    
    @Query("SELECT p FROM Personal p WHERE " +
           "(:busqueda IS NULL OR p.nombres LIKE %:busqueda% OR p.dni LIKE %:busqueda%)")
    List<Personal> buscar(String busqueda);
}