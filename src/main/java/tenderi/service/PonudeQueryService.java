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
import tenderi.domain.Ponude;
import tenderi.repository.PonudeRepository;
import tenderi.service.criteria.PonudeCriteria;

/**
 * Service for executing complex queries for {@link Ponude} entities in the database.
 * The main input is a {@link PonudeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Ponude} or a {@link Page} of {@link Ponude} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PonudeQueryService extends QueryService<Ponude> {

    private final Logger log = LoggerFactory.getLogger(PonudeQueryService.class);

    private final PonudeRepository ponudeRepository;

    public PonudeQueryService(PonudeRepository ponudeRepository) {
        this.ponudeRepository = ponudeRepository;
    }

    /**
     * Return a {@link List} of {@link Ponude} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Ponude> findByCriteria(PonudeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ponude> specification = createSpecification(criteria);
        return ponudeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Ponude} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Ponude> findByCriteria(PonudeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ponude> specification = createSpecification(criteria);
        return ponudeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PonudeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ponude> specification = createSpecification(criteria);
        return ponudeRepository.count(specification);
    }

    /**
     * Function to convert {@link PonudeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Ponude> createSpecification(PonudeCriteria criteria) {
        Specification<Ponude> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Ponude_.id));
            }
            if (criteria.getSifraPostupka() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSifraPostupka(), Ponude_.sifraPostupka));
            }
            if (criteria.getSifraPonude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSifraPonude(), Ponude_.sifraPonude));
            }
            if (criteria.getBrojPartije() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBrojPartije(), Ponude_.brojPartije));
            }
            if (criteria.getNazivPonudjaca() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNazivPonudjaca(), Ponude_.nazivPonudjaca));
            }
            if (criteria.getNaziProizvodjaca() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNaziProizvodjaca(), Ponude_.nazivProizvodjaca));
            }
            if (criteria.getZastceniNaziv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZastceniNaziv(), Ponude_.zastceniNaziv));
            }
            if (criteria.getPonudjenaVrijednost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPonudjenaVrijednost(), Ponude_.ponudjenaVrijednost));
            }
            if (criteria.getPonudjenaJedinicnaCijena() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getPonudjenaJedinicnaCijena(), Ponude_.ponudjenaJedinicnaCijena));
            }
            if (criteria.getRokIsporuke() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRokIsporuke(), Ponude_.rokIsporuke));
            }
            if (criteria.getBrojUgovora() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBrojUgovora(), Ponude_.brojUgovora));
            }
            if (criteria.getDatumUgovora() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatumUgovora(), Ponude_.datumUgovora));
            }
        }
        return specification;
    }
}
