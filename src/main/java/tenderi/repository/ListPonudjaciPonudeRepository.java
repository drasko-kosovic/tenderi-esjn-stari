package tenderi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tenderi.domain.ListPonudjaciPonude;

/**
 * Spring Data SQL repository for the ListPonudjaciPonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListPonudjaciPonudeRepository extends JpaRepository<ListPonudjaciPonude, Long> {}
