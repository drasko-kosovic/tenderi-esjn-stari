package tenderi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tenderi.domain.ViewVrednovanje;

/**
 * Spring Data SQL repository for the ViewVrednovanje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewVrednovanjeRepository extends JpaRepository<ViewVrednovanje, Long>, JpaSpecificationExecutor<ViewVrednovanje> {
    @Query("select v from ViewVrednovanje v where v.sifraPostupka=:sifraPostupka ")
    List<ViewVrednovanje> findBySifraPotupka(@Param("sifraPostupka") Integer sifraPostupka);

    @Query("select v from ViewVrednovanje v where v.sifraPonude=:sifraPonude ")
    List<ViewVrednovanje> findBySifraPonude(@Param("sifraPonude") Integer sifraPonude);

    @Query("select v from ViewVrednovanje v where v.sifraPostupka=:sifraPostupka and v.sifraPonude=:sifraPonude")
    List<ViewVrednovanje> findBySifraPostupkaPonude(
        @Param("sifraPostupka") Integer sifraPostupka,
        @Param("sifraPonude") Integer sifraPonude
    );
}
