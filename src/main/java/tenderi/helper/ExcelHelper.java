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
import tenderi.domain.ViewVrednovanje;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {
        "",
        "sifra postupka",
        "sifra ponude",
        "broj partije",
        "atc",
        "inn",
        "zasticeni naziv",
        "farmaceutski oblik lijeka",
        "jacina lijeka",
        "kolicina",
        "pakovanje",
        "naziv proizvodjaca",
        "naziv ponudjaca",
        "procijenjena vrijednost",
        "procijenjena jedim. vrijednost",
        "ponudjena vrijednost",
        "ponudjena jedin. vrijednost",
        "rok isporuke",
        "bod isporuka",
        "bod cijena",
        "bod ukupno",
    };
    static String SHEET = "Vrednovanje";

    public static ByteArrayInputStream tutorialsToExcel(List<ViewVrednovanje> vrednovanje) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ViewVrednovanje view_vrednovanje : vrednovanje) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(1).setCellValue(view_vrednovanje.getSifraPostupka());
                row.createCell(2).setCellValue(view_vrednovanje.getSifraPonude());
                row.createCell(3).setCellValue(view_vrednovanje.getBrojPartije());
                row.createCell(4).setCellValue(view_vrednovanje.getAtc());
                row.createCell(5).setCellValue(view_vrednovanje.getInn());
                row.createCell(6).setCellValue(view_vrednovanje.getZastceniNaziv());
                row.createCell(7).setCellValue(view_vrednovanje.getFarmaceutskiOblikLijeka());
                row.createCell(8).setCellValue(view_vrednovanje.getJacinaLijeka());
                row.createCell(9).setCellValue(view_vrednovanje.getTrazanaKolicina());
                row.createCell(10).setCellValue(view_vrednovanje.getPakovanje());
                row.createCell(11).setCellValue(view_vrednovanje.getNazivProizvodjaca());
                row.createCell(12).setCellValue(view_vrednovanje.getNazivPonudjaca());
                row.createCell(13).setCellValue(view_vrednovanje.getProcijenjenaVrijednost());
                row.createCell(14).setCellValue(view_vrednovanje.getTrazenaJedinicnaCijena());
                row.createCell(15).setCellValue(view_vrednovanje.getPonudjenaVrijednost());
                row.createCell(16).setCellValue(view_vrednovanje.getPonudjenaJedinicnaCijena());
                row.createCell(17).setCellValue(view_vrednovanje.getRokIsporuke());
                row.createCell(18).setCellValue(view_vrednovanje.getBodRok());
                row.createCell(19).setCellValue(view_vrednovanje.getBodCijena());
                row.createCell(20).setCellValue(view_vrednovanje.getBodUkupno());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
