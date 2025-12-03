package fibrastar.service;

import fibrastar.entity.Cobro;
import fibrastar.repository.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CobroService {
    @Autowired
    private CobroRepository repository;

    public List<Cobro> filtrar(String cliente, String mes, String estado) {
        if (cliente != null && cliente.isEmpty()) cliente = null;
        if (mes != null && mes.equals("Todos")) mes = null;
        if (estado != null && estado.equals("Todos")) estado = null;
        return repository.buscarConFiltros(cliente, mes, estado);
    }

    public List<Cobro> listarTodos() { return repository.findAll(); }

    // --- GUARDAR CON BLOQUEO DE DUPLICADOS ---
    public String guardar(Cobro cobro) {
        cobro.calcularVencimiento();

        // Limpiamos espacios en blanco para evitar errores como "Juan " vs "Juan"
        String clienteLimpio = cobro.getNombreCliente().trim();
        String mesLimpio = cobro.getMesConcepto().trim();
        cobro.setNombreCliente(clienteLimpio);

        boolean existe;
        if (cobro.getId() == null) {
            // Caso: Nuevo Registro
            existe = repository.existsByNombreClienteAndMesConcepto(clienteLimpio, mesLimpio);
        } else {
            // Caso: Editar (Verificar que no choque con OTRO registro que no sea él mismo)
            existe = repository.existsByNombreClienteAndMesConceptoAndIdNot(clienteLimpio, mesLimpio, cobro.getId());
        }

        if (existe) {
            return "DUPLICADO"; // Devolvemos error si ya existe
        }

        repository.save(cobro);
        return "OK";
    }

    public void eliminar(Long id) { repository.deleteById(id); }

    @Scheduled(cron = "0 0 0 * * ?")
    public void verificarVencimientos() {
        List<Cobro> vencidos = repository.findByEstadoAndFechaVencimientoBefore("Pagado", LocalDate.now());
        for (Cobro c : vencidos) {
            c.setEstado("Pendiente");
            repository.save(c);
        }
    }
}