package fibrastar.service;

import fibrastar.entity.Plan;
import fibrastar.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    private PlanRepository repository;

    public List<Plan> listarTodos() { return repository.findAll(); }

    public List<Plan> filtrar(String nombre) {
        if (nombre != null && nombre.isEmpty()) nombre = null;
        return repository.buscarPorNombre(nombre);
    }

    public void guardar(Plan plan) { repository.save(plan); }
    public void eliminar(Long id) { repository.deleteById(id); }
}