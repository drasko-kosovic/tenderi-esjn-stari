package tenderi.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenderi.domain.ViewVrednovanje;
import tenderi.helper.ExcelHelper;
import tenderi.repository.ViewVrednovanjeRepository;

@Service
public class ExcelService {

    @Autowired
    ViewVrednovanjeRepository repository;

    public ByteArrayInputStream load() {
        List<ViewVrednovanje> view_vrednovanje = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(view_vrednovanje);
        return in;
    }

    public ByteArrayInputStream loadBySifraPostupka(Integer sifra) {
        List<ViewVrednovanje> vrednovanje = repository.findBySifraPotupka(sifra);

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(vrednovanje);
        return in;
    }

    public ByteArrayInputStream SifraPonude(Integer sifra) {
        List<ViewVrednovanje> vrednovanje = repository.findBySifraPonude(sifra);

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(vrednovanje);
        return in;
    }

    public ByteArrayInputStream loadByPostupakPonuda(Integer sifraPostupka, Integer sifraPonude) {
        List<ViewVrednovanje> vrednovanje = repository.findBySifraPostupkaPonude(sifraPostupka, sifraPonude);

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(vrednovanje);
        return in;
    }
}
