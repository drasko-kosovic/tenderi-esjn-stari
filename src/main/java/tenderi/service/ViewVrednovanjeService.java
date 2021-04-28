package tenderi.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenderi.domain.ViewVrednovanje;
import tenderi.repository.ViewVrednovanjeRepository;

/**
 * Service Implementation for managing {@link ViewVrednovanje}.
 */
@Service
@Transactional
public class ViewVrednovanjeService {

    private final Logger log = LoggerFactory.getLogger(ViewVrednovanjeService.class);

    private final ViewVrednovanjeRepository viewVrednovanjeRepository;

    public ViewVrednovanjeService(ViewVrednovanjeRepository viewVrednovanjeRepository) {
        this.viewVrednovanjeRepository = viewVrednovanjeRepository;
    }

    /**
     * Save a viewVrednovanje.
     *
     * @param viewVrednovanje the entity to save.
     * @return the persisted entity.
     */
    public ViewVrednovanje save(ViewVrednovanje viewVrednovanje) {
        log.debug("Request to save ViewVrednovanje : {}", viewVrednovanje);
        return viewVrednovanjeRepository.save(viewVrednovanje);
    }

    /**
     * Partially update a viewVrednovanje.
     *
     * @param viewVrednovanje the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewVrednovanje> partialUpdate(ViewVrednovanje viewVrednovanje) {
        log.debug("Request to partially update ViewVrednovanje : {}", viewVrednovanje);

        return viewVrednovanjeRepository
            .findById(viewVrednovanje.getId())
            .map(
                existingViewVrednovanje -> {
                    if (viewVrednovanje.getSifraPostupka() != null) {
                        existingViewVrednovanje.setSifraPostupka(viewVrednovanje.getSifraPostupka());
                    }
                    if (viewVrednovanje.getSifraPonude() != null) {
                        existingViewVrednovanje.setSifraPonude(viewVrednovanje.getSifraPonude());
                    }
                    if (viewVrednovanje.getBrojPartije() != null) {
                        existingViewVrednovanje.setBrojPartije(viewVrednovanje.getBrojPartije());
                    }
                    if (viewVrednovanje.getNazivPonudjaca() != null) {
                        existingViewVrednovanje.setNazivPonudjaca(viewVrednovanje.getNazivPonudjaca());
                    }
                    if (viewVrednovanje.getProcijenjenaVrijednost() != null) {
                        existingViewVrednovanje.setProcijenjenaVrijednost(viewVrednovanje.getProcijenjenaVrijednost());
                    }
                    if (viewVrednovanje.getPonudjenaVrijednost() != null) {
                        existingViewVrednovanje.setPonudjenaVrijednost(viewVrednovanje.getPonudjenaVrijednost());
                    }
                    if (viewVrednovanje.getTrazenaKolicina() != null) {
                        existingViewVrednovanje.setTrazenaKolicina(viewVrednovanje.getTrazenaKolicina());
                    }
                    if (viewVrednovanje.getAtc() != null) {
                        existingViewVrednovanje.setAtc(viewVrednovanje.getAtc());
                    }
                    if (viewVrednovanje.getInn() != null) {
                        existingViewVrednovanje.setInn(viewVrednovanje.getInn());
                    }
                    if (viewVrednovanje.getZastceniNaziv() != null) {
                        existingViewVrednovanje.setZastceniNaziv(viewVrednovanje.getZastceniNaziv());
                    }
                    if (viewVrednovanje.getFarmaceutskiOblikLijeka() != null) {
                        existingViewVrednovanje.setFarmaceutskiOblikLijeka(viewVrednovanje.getFarmaceutskiOblikLijeka());
                    }
                    if (viewVrednovanje.getJacinaLijeka() != null) {
                        existingViewVrednovanje.setJacinaLijeka(viewVrednovanje.getJacinaLijeka());
                    }
                    if (viewVrednovanje.getPakovanje() != null) {
                        existingViewVrednovanje.setPakovanje(viewVrednovanje.getPakovanje());
                    }

                    return existingViewVrednovanje;
                }
            )
            .map(viewVrednovanjeRepository::save);
    }

    /**
     * Get all the viewVrednovanjes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ViewVrednovanje> findAll(Pageable pageable) {
        log.debug("Request to get all ViewVrednovanjes");
        return viewVrednovanjeRepository.findAll(pageable);
    }

    /**
     * Get one viewVrednovanje by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewVrednovanje> findOne(Long id) {
        log.debug("Request to get ViewVrednovanje : {}", id);
        return viewVrednovanjeRepository.findById(id);
    }

    /**
     * Delete the viewVrednovanje by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewVrednovanje : {}", id);
        viewVrednovanjeRepository.deleteById(id);
    }
}
