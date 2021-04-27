package tenderi.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tenderi.service.ExcelServicePrvorangirani;

@CrossOrigin(origins = ("*"))
@Controller
@RequestMapping("/api")
public class ExcelControllerPrvorangirani {

    @Autowired
    ExcelServicePrvorangirani excelServicePrvorangirani;

    @GetMapping(path = "/excel-prvorangirani/download")
    public ResponseEntity<Resource> getFilePrvorangirani() {
        String filename = "prvorangirani.xlsx";
        InputStreamResource file = new InputStreamResource(excelServicePrvorangirani.loadPrvorangirani());

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }

    @GetMapping(path = "/excel-prvorangirani/download/sifra-postupka/{sifra}")
    public ResponseEntity<Resource> getFileBySifraPostupkaPrvorangirani(@PathVariable Integer sifra) {
        String filename = "prvorangirani.xlsx";
        InputStreamResource file = new InputStreamResource(excelServicePrvorangirani.loadBySifraPostupka(sifra));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }

    @GetMapping(path = "/excel-prvorangirani/download/sifra-ponude/{sifra}")
    public ResponseEntity<Resource> getFileBySifraPonudePrvorangirani(@PathVariable Integer sifra) {
        String filename = "prvorangirani.xlsx";
        InputStreamResource file = new InputStreamResource(excelServicePrvorangirani.loadBySifraPonude(sifra));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
