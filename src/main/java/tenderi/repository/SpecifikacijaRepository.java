package tenderi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tenderi.domain.Specifikacija;

/**
 * Spring Data SQL repository for the Specifikacija entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecifikacijaRepository extends JpaRepository<Specifikacija, Long>, JpaSpecificationExecutor<Specifikacija> {}
