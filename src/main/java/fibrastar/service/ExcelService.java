package fibrastar.service;

import fibrastar.entity.Instalacion;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void exportarInstalaciones(HttpServletResponse response, List<Instalacion> lista) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Instalaciones");

        // Estilo Header
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        // Encabezados
        Row headerRow = sheet.createRow(0);
        String[] columnas = {"ID", "Numero", "Cliente", "Caja NAP", "Zona", "Tipo", "Estado", "Fecha Prog."};
        
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }

        // Datos
        int rowIdx = 1;
        for (Instalacion inst : lista) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(inst.getId());
            row.createCell(1).setCellValue(inst.getNumeroInstalacion());
            row.createCell(2).setCellValue(inst.getNombreCliente());
            row.createCell(3).setCellValue(inst.getCajaNap());
            row.createCell(4).setCellValue(inst.getZona());
            row.createCell(5).setCellValue(inst.getTipo());
            row.createCell(6).setCellValue(inst.getEstado());
            row.createCell(7).setCellValue(inst.getFechaProgramada() != null ? inst.getFechaProgramada().toString() : "");
        }

        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}