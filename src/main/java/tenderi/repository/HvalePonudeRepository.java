package tenderi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tenderi.domain.HvalePonude;

/**
 * Spring Data SQL repository for the HvalePonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HvalePonudeRepository extends JpaRepository<HvalePonude, Long> {}
