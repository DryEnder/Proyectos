package mx.unam.aragon.service.XLS;

import mx.unam.aragon.model.entity.InventarioEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Service
public class ExcelService {
    public byte[] generarExcel(List<InventarioEntity> inventarios) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        // Ordenar los inventarios por stock disponible (de menor a mayor)
        inventarios.sort(Comparator.comparing(InventarioEntity::getStock));
        Sheet sheet = workbook.createSheet("Employees");

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nombre articulo");
        headerRow.createCell(1).setCellValue("categoria");
        headerRow.createCell(2).setCellValue("almacen");

        headerRow.createCell(3).setCellValue("stock disponible");

        // Data
        int rowNum = 1;
        for (InventarioEntity inv : inventarios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(inv.getIdInventario().getArticulo().getNombre());
            row.createCell(1).setCellValue(inv.getIdInventario().getArticulo().getCategoria().getNombre());
            row.createCell(2).setCellValue(inv.getIdInventario().getAlmacen().getNombre());
            row.createCell(3).setCellValue(inv.getStock());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
