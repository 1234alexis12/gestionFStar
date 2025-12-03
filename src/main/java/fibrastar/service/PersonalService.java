package fibrastar.service;

import fibrastar.entity.Personal;
import fibrastar.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonalService {
    @Autowired
    private PersonalRepository repository;

    public List<Personal> filtrar(String busqueda) {
        if (busqueda != null && busqueda.isEmpty()) busqueda = null;
        return repository.buscar(busqueda);
    }

    public void guardar(Personal personal) { repository.save(personal); }
    public void eliminar(Long id) { repository.deleteById(id); }
}