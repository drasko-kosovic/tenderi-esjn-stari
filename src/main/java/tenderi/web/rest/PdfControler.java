package tenderi.web.rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tenderi.domain.ViewVrednovanje;
import tenderi.repository.ViewVrednovanjeRepository;

@CrossOrigin(origins = ("*"))
@RestController
@RequestMapping("/report")
public class PdfControler {

    @Autowired
    ApplicationContext context;

    @Autowired
    ViewVrednovanjeRepository viewVrednovanjeRepository;

    @GetMapping(path = "/vrednovanje/{sifraPostupka}")
    @ResponseBody
    public void getPdfPonude(HttpServletResponse response, @PathVariable Integer sifraPostupka) throws Exception {
        Resource resource = context.getResource("classpath:reports/ponude/Cherry_Landscape.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();

        List<ViewVrednovanje> vrednovanje = (List<ViewVrednovanje>) viewVrednovanjeRepository.findBySifraPotupka(sifraPostupka);

        JRDataSource dataSource = new JRBeanCollectionDataSource(vrednovanje);
        params.put("datasource", dataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
