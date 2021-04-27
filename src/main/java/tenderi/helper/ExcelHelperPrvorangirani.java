package tenderi.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tenderi.domain.Prvorangirani;

public class ExcelHelperPrvorangirani {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {
        "",
        "sifra postupka",
        "sifra ponude",
        "broj partije",
        "naziv ponudjaca",
        "naziv",
        "procijenjena vrijednost",
        "ponudjena vrijednost",
        "atc",
        "inn",
        "farmaceutski oblik lijeka",
        "jacina lijeka",
        "kolicina",
        "pakovanje",
        "rok isporuke",
    };
    static String SHEET = "Prvorangirani";

    public static ByteArrayInputStream tutorialsToExcel(List<Prvorangirani> prvorangirani) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Prvorangirani view_prvorangirani : prvorangirani) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(1).setCellValue(view_prvorangirani.getSifraPostupka());
                row.createCell(2).setCellValue(view_prvorangirani.getSifraPonude());
                row.createCell(3).setCellValue(view_prvorangirani.getBrojPartije());
                row.createCell(4).setCellValue(view_prvorangirani.getNazivPonudjaca());
                row.createCell(5).setCellValue(view_prvorangirani.getInn());
                row.createCell(6).setCellValue(view_prvorangirani.getProcijenjenaVrijednost());
                row.createCell(7).setCellValue(view_prvorangirani.getPonudjenaVrijednost());
                row.createCell(8).setCellValue(view_prvorangirani.getAtc());
                row.createCell(9).setCellValue(view_prvorangirani.getInn());

                row.createCell(10).setCellValue(view_prvorangirani.getFarmaceutskiOblikLijeka());
                row.createCell(11).setCellValue(view_prvorangirani.getJacinaLijeka());
                row.createCell(13).setCellValue(view_prvorangirani.getPakovanje());
                row.createCell(16).setCellValue(view_prvorangirani.getRokIsporuke());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
