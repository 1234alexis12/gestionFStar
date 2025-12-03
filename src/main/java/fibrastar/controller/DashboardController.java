package fibrastar.controller;

import fibrastar.entity.Instalacion;
import fibrastar.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping({"/", "/dashboard"})
public class DashboardController {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activePage", "dashboard");

        // --- GRÁFICO 1: ZONAS (Pastel) ---
        List<Object[]> zonasData = instalacionRepository.contarPorZona();
        String[] labelsZonas = new String[zonasData.size()];
        Integer[] dataZonas = new Integer[zonasData.size()];
        for (int i = 0; i < zonasData.size(); i++) {
            labelsZonas[i] = (String) zonasData.get(i)[0]; 
            dataZonas[i] = ((Number) zonasData.get(i)[1]).intValue();
        }

        // --- GRÁFICO 2: TIPOS (Barras) ---
        List<Object[]> tiposData = instalacionRepository.contarPorTipo();
        String[] labelsTipos = new String[tiposData.size()];
        Integer[] dataTipos = new Integer[tiposData.size()];
        for (int i = 0; i < tiposData.size(); i++) {
            labelsTipos[i] = (String) tiposData.get(i)[0];
            dataTipos[i] = ((Number) tiposData.get(i)[1]).intValue();
        }

        // --- GRÁFICO 3: MESES (Línea de tiempo) ---
        Integer[] dataMeses = new Integer[12];
        Arrays.fill(dataMeses, 0); // Llenar de ceros
        
        List<Instalacion> finalizadas = instalacionRepository.findByEstado("Culminada");
        for (Instalacion inst : finalizadas) {
            if (inst.getFechaFinalizada() != null) {
                // getMonthValue devuelve 1 para Enero, restamos 1 para el índice del array 0
                int mesIndex = inst.getFechaFinalizada().getMonthValue() - 1;
                dataMeses[mesIndex]++;
            }
        }

        // Enviar todo a la vista
        model.addAttribute("labelsZonas", labelsZonas);
        model.addAttribute("dataZonas", dataZonas);
        model.addAttribute("labelsTipos", labelsTipos);
        model.addAttribute("dataTipos", dataTipos);
        model.addAttribute("dataMeses", dataMeses);

        return "dashboard/index";
    }
}