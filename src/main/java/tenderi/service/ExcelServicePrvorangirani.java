package tenderi.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenderi.domain.Prvorangirani;
import tenderi.helper.ExcelHelperPrvorangirani;
import tenderi.repository.PrvorangiraniRepository;

@Service
public class ExcelServicePrvorangirani {

    @Autowired
    PrvorangiraniRepository prvorangiraniRepository;

    public ByteArrayInputStream loadPrvorangirani() {
        List<Prvorangirani> view_prvorangirani = prvorangiraniRepository.findAll();

        ByteArrayInputStream in = ExcelHelperPrvorangirani.tutorialsToExcelPrvorangirani(view_prvorangirani);
        return in;
    }
}
