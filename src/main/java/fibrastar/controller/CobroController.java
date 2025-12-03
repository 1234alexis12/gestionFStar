package fibrastar.controller;

import fibrastar.entity.Cobro;
import fibrastar.service.CobroService;
import fibrastar.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/cobros")
public class CobroController {

    @Autowired
    private CobroService service;
    @Autowired
    private InstalacionService instalacionService;

    @GetMapping
    public String listar(
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String mes,
            @RequestParam(required = false) String estado,
            Model model) {

        model.addAttribute("activePage", "cobros");
        model.addAttribute("cobro", new Cobro());
        model.addAttribute("listaCobros", service.filtrar(cliente, mes, estado));
        model.addAttribute("listaInstalaciones", instalacionService.listarTodas());
        
        return "cobros/index";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cobro cobro, RedirectAttributes redirectAttributes) {
        if (cobro.getFechaPago() == null) cobro.setFechaPago(LocalDate.now());
        
        // Intentamos guardar
        String resultado = service.guardar(cobro);

        if (resultado.equals("DUPLICADO")) {
            // Mensaje de Error
            redirectAttributes.addFlashAttribute("error", 
                "¡Error! El cliente " + cobro.getNombreCliente() + " ya tiene un pago registrado para " + cobro.getMesConcepto());
        } else {
            // Mensaje de Éxito
            redirectAttributes.addFlashAttribute("success", "Pago registrado correctamente.");
        }

        return "redirect:/cobros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/cobros";
    }
}