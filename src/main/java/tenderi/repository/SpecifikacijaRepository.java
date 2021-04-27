package tenderi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tenderi.domain.Specifikacija;
import tenderi.domain.ViewVrednovanje;

/**
 * Spring Data SQL repository for the Specifikacija entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecifikacijaRepository extends JpaRepository<Specifikacija, Long>, JpaSpecificationExecutor<Specifikacija> {}
