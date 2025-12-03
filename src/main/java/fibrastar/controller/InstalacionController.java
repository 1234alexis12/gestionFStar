package fibrastar.controller;

import fibrastar.entity.Instalacion;
import fibrastar.service.ExcelService;
import fibrastar.service.InstalacionService;
import fibrastar.service.PlanService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionService service;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private PlanService planService; // <--- NUEVO: Inyectamos Planes

    @GetMapping
    public String listar(
            @RequestParam(required = false) String numero,
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String zona,
            Model model) {

        model.addAttribute("activePage", "instalaciones");
        model.addAttribute("instalacion", new Instalacion());
        model.addAttribute("listaInstalaciones", service.filtrar(numero, cliente, zona));
        
        // --- ENVIAR PLANES AL COMBOBOX ---
        model.addAttribute("listaPlanes", planService.listarTodos());

        return "instalaciones/index";
    }

    @GetMapping("/exportar")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Reporte_Instalaciones.xlsx");
        excelService.exportarInstalaciones(response, service.listarTodas());
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Instalacion instalacion) {
        if ("Culminada".equals(instalacion.getEstado()) && instalacion.getFechaFinalizada() == null) {
            instalacion.setFechaFinalizada(LocalDate.now());
        }
        service.guardar(instalacion);
        return "redirect:/instalaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/instalaciones";
    }
}