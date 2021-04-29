package tenderi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.jhipster.web.util.ResponseUtil;
import tenderi.domain.HvalePonude;

/**
 * Spring Data SQL repository for the HvalePonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HvalePonudeRepository extends JpaRepository<HvalePonude, Long> {
    @Query(
        value = "SELECT \n" +
        "  specifikacija.id, \n" +
        "  specifikacija.broj_partije, \n" +
        "  specifikacija.inn, \n" +
        "  specifikacija.farmaceutski_oblik_lijeka, \n" +
        "  specifikacija.pakovanje, \n" +
        "  specifikacija.trazena_kolicina, \n" +
        "  specifikacija.trazena_jedinicna_cijena, \n" +
        "  specifikacija.procijenjena_vrijednost, \n" +
        "  specifikacija.sifra_postupka \n" +
        "FROM specifikacija \n" +
        "WHERE\n" +
        "  (\n" +
        "    (specifikacija.sifra_postupka = :sifra) AND\n" +
        "    (\n" +
        "      NOT (\n" +
        "        specifikacija.broj_partije IN ( SELECT \n" +
        "          view_prvorangirani.broj_partije \n" +
        "        FROM view_prvorangirani \n" +
        "        WHERE\n" +
        "          (view_prvorangirani.sifra_postupka = :sifra))\n" +
        "      )\n" +
        "    )\n" +
        "  )",
        nativeQuery = true
    )
    List<HvalePonude> HvalePonude(@Param("sifra") Integer sifra);
}
