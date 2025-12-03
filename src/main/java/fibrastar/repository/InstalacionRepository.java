package fibrastar.repository;

import fibrastar.entity.Instalacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstalacionRepository extends JpaRepository<Instalacion, Long> {

    // 1. Estadísticas para Gráfico Circular (Zonas)
    @Query("SELECT i.zona, COUNT(i) FROM Instalacion i GROUP BY i.zona")
    List<Object[]> contarPorZona();

    // 2. Estadísticas para Gráfico de Barras Pequeño (Tipos)
    @Query("SELECT i.tipo, COUNT(i) FROM Instalacion i GROUP BY i.tipo")
    List<Object[]> contarPorTipo();

    // 3. Obtener solo las finalizadas para el Gráfico de Meses
    List<Instalacion> findByEstado(String estado);

    // 4. Búsqueda con Filtros (Inteligente: si un campo es null, no lo filtra)
    @Query("SELECT i FROM Instalacion i WHERE " +
           "(:numero IS NULL OR i.numeroInstalacion LIKE %:numero%) AND " +
           "(:cliente IS NULL OR i.nombreCliente LIKE %:cliente%) AND " +
           "(:zona IS NULL OR i.zona = :zona)")
    List<Instalacion> buscarConFiltros(String numero, String cliente, String zona);
}