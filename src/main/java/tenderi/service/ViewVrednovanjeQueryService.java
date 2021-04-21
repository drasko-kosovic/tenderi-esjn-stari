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
import tenderi.domain.ViewVrednovanje;
import tenderi.repository.ViewVrednovanjeRepository;
import tenderi.service.criteria.ViewVrednovanjeCriteria;

/**
 * Service for executing complex queries for {@link ViewVrednovanje} entities in the database.
 * The main input is a {@link ViewVrednovanjeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ViewVrednovanje} or a {@link Page} of {@link ViewVrednovanje} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ViewVrednovanjeQueryService extends QueryService<ViewVrednovanje> {

    private final Logger log = LoggerFactory.getLogger(ViewVrednovanjeQueryService.class);

    private final ViewVrednovanjeRepository viewVrednovanjeRepository;

    public ViewVrednovanjeQueryService(ViewVrednovanjeRepository viewVrednovanjeRepository) {
        this.viewVrednovanjeRepository = viewVrednovanjeRepository;
    }

    /**
     * Return a {@link List} of {@link ViewVrednovanje} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ViewVrednovanje> findByCriteria(ViewVrednovanjeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ViewVrednovanje> specification = createSpecification(criteria);
        return viewVrednovanjeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link ViewVrednovanje} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ViewVrednovanje> findByCriteria(ViewVrednovanjeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ViewVrednovanje> specification = createSpecification(criteria);
        return viewVrednovanjeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ViewVrednovanjeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ViewVrednovanje> specification = createSpecification(criteria);
        return viewVrednovanjeRepository.count(specification);
    }

    /**
     * Function to convert {@link ViewVrednovanjeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ViewVrednovanje> createSpecification(ViewVrednovanjeCriteria criteria) {
        Specification<ViewVrednovanje> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ViewVrednovanje_.id));
            }
            if (criteria.getSifraPostupka() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSifraPostupka(), ViewVrednovanje_.sifraPostupka));
            }
            if (criteria.getSifraPonude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSifraPonude(), ViewVrednovanje_.sifraPonude));
            }
            if (criteria.getBrojPartije() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBrojPartije(), ViewVrednovanje_.brojPartije));
            }
            if (criteria.getNazivPonudjaca() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNazivPonudjaca(), ViewVrednovanje_.nazivPonudjaca));
            }
            if (criteria.getProcijenjenaVrijednost() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getProcijenjenaVrijednost(), ViewVrednovanje_.procijenjenaVrijednost)
                    );
            }
            if (criteria.getPonudjenaVrijednost() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getPonudjenaVrijednost(), ViewVrednovanje_.ponudjenaVrijednost));
            }
            if (criteria.getKolicina() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKolicina(), ViewVrednovanje_.kolicina));
            }
            if (criteria.getAtc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAtc(), ViewVrednovanje_.atc));
            }
            if (criteria.getInn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInn(), ViewVrednovanje_.inn));
            }
            if (criteria.getZastceniNaziv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZastceniNaziv(), ViewVrednovanje_.zastceniNaziv));
            }
            if (criteria.getFarmaceutskiOblikLijeka() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getFarmaceutskiOblikLijeka(), ViewVrednovanje_.farmaceutskiOblikLijeka)
                    );
            }
            if (criteria.getJacinaLijeka() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJacinaLijeka(), ViewVrednovanje_.jacinaLijeka));
            }
            if (criteria.getPakovanje() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPakovanje(), ViewVrednovanje_.pakovanje));
            }
            if (criteria.getBodIsporuka() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBodIsporuka(), ViewVrednovanje_.bodIsporuka));
            }
            if (criteria.getRokIsporuke() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRokIsporuke(), ViewVrednovanje_.rokIsporuke));
            }
            if (criteria.getBodCijena() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBodCijena(), ViewVrednovanje_.bodCijena));
            }
            if (criteria.getBodUkupno() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBodUkupno(), ViewVrednovanje_.bodUkupno));
            }
        }
        return specification;
    }
}
