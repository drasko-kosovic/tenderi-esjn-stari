package tenderi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tenderi.domain.Prvorangirani;
import tenderi.domain.ViewVrednovanje;

/**
 * Spring Data SQL repository for the Prvorangirani entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrvorangiraniRepository extends JpaRepository<Prvorangirani, Long>, JpaSpecificationExecutor<Prvorangirani> {
    @Query("select p from Prvorangirani p where p.sifraPostupka=:sifraPostupka")
    List<Prvorangirani> findBySifraPotupka(@Param("sifraPostupka") Integer sifra);

    @Query("select p from Prvorangirani p where p.sifraPonude=:sifraPonude")
    List<Prvorangirani> findBySifraPonude(@Param("sifraPonude") Integer sifra);
}
