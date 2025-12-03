package fibrastar.controller;

import fibrastar.entity.Plan;
import fibrastar.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanService service;

    @GetMapping
    public String listar(@RequestParam(required = false) String nombre, Model model) {
        model.addAttribute("activePage", "planes");
        model.addAttribute("plan", new Plan());
        // Usamos el método filtrar en lugar de listarTodos
        model.addAttribute("listaPlanes", service.filtrar(nombre));
        return "planes/index";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Plan plan) {
        service.guardar(plan);
        return "redirect:/planes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/planes";
    }
}