package fibrastar.service;

import fibrastar.entity.Instalacion;
import fibrastar.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository repository;

    public List<Instalacion> listarTodas() {
        return repository.findAll();
    }

    public List<Instalacion> filtrar(String numero, String cliente, String zona) {
        // Lógica para convertir vacíos en nulos
        if (numero != null && numero.isEmpty()) numero = null;
        if (cliente != null && cliente.isEmpty()) cliente = null;
        if (zona != null && zona.equals("Seleccione")) zona = null;
        
        return repository.buscarConFiltros(numero, cliente, zona);
    }

    public void guardar(Instalacion instalacion) {
        repository.save(instalacion);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    
    public Optional<Instalacion> buscarPorId(Long id) {
        return repository.findById(id);
    }
}