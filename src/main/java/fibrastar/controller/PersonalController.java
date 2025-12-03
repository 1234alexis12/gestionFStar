package fibrastar.controller;

import fibrastar.entity.Personal;
import fibrastar.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService service;

    @GetMapping
    public String listar(@RequestParam(required = false) String busqueda, Model model) {
        model.addAttribute("activePage", "personal");
        model.addAttribute("personal", new Personal());
        model.addAttribute("listaPersonal", service.filtrar(busqueda));
        return "personal/index";
    }
    
    // ... guardar y eliminar se mantienen igual ...
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Personal personal) {
        service.guardar(personal);
        return "redirect:/personal";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/personal";
    }
}