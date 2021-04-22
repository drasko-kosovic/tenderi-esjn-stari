package tenderi.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tenderi.service.ExcelService;

@CrossOrigin(origins = ("*"))
@Controller
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    ExcelService fileService;

    @GetMapping(path = "/excel/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "vrednovanje.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }

    @GetMapping(path = "/excel/download/sifra-postupka/{sifra}")
    public ResponseEntity<Resource> getFileBySifraPostupka(@PathVariable Integer sifra) {
        String filename = "vrednovanje.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.loadBySifraPostupka(sifra));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }

    @GetMapping(path = "/excel/download/sifra-ponude/{sifra}")
    public ResponseEntity<Resource> getFileBySifraPonude(@PathVariable Integer sifra) {
        String filename = "vrednovanje.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.SifraPonude(sifra));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }

    @GetMapping(path = "/excel/download/postupakponuda")
    public ResponseEntity<Resource> getFileByPonudaPostupak(@RequestParam Integer sifraPostupka, Integer sifraPonude) {
        String filename = "vrednovanje.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.loadByPostupakPonuda(sifraPostupka, sifraPonude));

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(file);
    }
}
