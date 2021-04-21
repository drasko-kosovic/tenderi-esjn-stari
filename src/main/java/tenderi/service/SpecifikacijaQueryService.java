package tenderi.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tenderi.domain.*; // for static metamodels
import tenderi.domain.Specifikacija;
import tenderi.repository.SpecifikacijaRepository;
import tenderi.service.criteria.SpecifikacijaCriteria;

/**
 * Service for executing complex queries for {@link Specifikacija} entities in the database.
 * The main input is a {@link SpecifikacijaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Specifikacija} or a {@link Page} of {@link Specifikacija} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SpecifikacijaQueryService extends QueryService<Specifikacija> {

    private final Logger log = LoggerFactory.getLogger(SpecifikacijaQueryService.class);

    private final SpecifikacijaRepository specifikacijaRepository;

    public SpecifikacijaQueryService(SpecifikacijaRepository specifikacijaRepository) {
        this.specifikacijaRepository = specifikacijaRepository;
    }

    /**
     * Return a {@link List} of {@link Specifikacija} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Specifikacija> findByCriteria(SpecifikacijaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Specifikacija> specification = createSpecification(criteria);
        return specifikacijaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Specifikacija} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Specifikacija> findByCriteria(SpecifikacijaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Specifikacija> specification = createSpecification(criteria);
        return specifikacijaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SpecifikacijaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Specifikacija> specification = createSpecification(criteria);
        return specifikacijaRepository.count(specification);
    }

    /**
     * Function to convert {@link SpecifikacijaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Specifikacija> createSpecification(SpecifikacijaCriteria criteria) {
        Specification<Specifikacija> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Specifikacija_.id));
            }
            if (criteria.getSifraPostupka() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSifraPostupka(), Specifikacija_.sifraPostupka));
            }
            if (criteria.getBrojPartije() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBrojPartije(), Specifikacija_.brojPartije));
            }
            if (criteria.getAtc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAtc(), Specifikacija_.atc));
            }
            if (criteria.getInn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInn(), Specifikacija_.inn));
            }
            if (criteria.getFarmaceutskiOblikLijeka() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getFarmaceutskiOblikLijeka(), Specifikacija_.farmaceutskiOblikLijeka)
                    );
            }
            if (criteria.getJacinaLijeka() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJacinaLijeka(), Specifikacija_.jacinaLijeka));
            }
            if (criteria.getKolicina() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKolicina(), Specifikacija_.kolicina));
            }
            if (criteria.getPakovanje() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPakovanje(), Specifikacija_.pakovanje));
            }
            if (criteria.getProcijenjenaVrijednost() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getProcijenjenaVrijednost(), Specifikacija_.procijenjenaVrijednost));
            }
        }
        return specification;
    }
}
