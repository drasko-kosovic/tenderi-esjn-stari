package tenderi.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenderi.domain.Specifikacija;
import tenderi.repository.SpecifikacijaRepository;

/**
 * Service Implementation for managing {@link Specifikacija}.
 */
@Service
@Transactional
public class SpecifikacijaService {

    private final Logger log = LoggerFactory.getLogger(SpecifikacijaService.class);

    private final SpecifikacijaRepository specifikacijaRepository;

    public SpecifikacijaService(SpecifikacijaRepository specifikacijaRepository) {
        this.specifikacijaRepository = specifikacijaRepository;
    }

    /**
     * Save a specifikacija.
     *
     * @param specifikacija the entity to save.
     * @return the persisted entity.
     */
    public Specifikacija save(Specifikacija specifikacija) {
        log.debug("Request to save Specifikacija : {}", specifikacija);
        return specifikacijaRepository.save(specifikacija);
    }

    /**
     * Partially update a specifikacija.
     *
     * @param specifikacija the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Specifikacija> partialUpdate(Specifikacija specifikacija) {
        log.debug("Request to partially update Specifikacija : {}", specifikacija);

        return specifikacijaRepository
            .findById(specifikacija.getId())
            .map(
                existingSpecifikacija -> {
                    if (specifikacija.getSifraPostupka() != null) {
                        existingSpecifikacija.setSifraPostupka(specifikacija.getSifraPostupka());
                    }
                    if (specifikacija.getBrojPartije() != null) {
                        existingSpecifikacija.setBrojPartije(specifikacija.getBrojPartije());
                    }
                    if (specifikacija.getAtc() != null) {
                        existingSpecifikacija.setAtc(specifikacija.getAtc());
                    }
                    if (specifikacija.getInn() != null) {
                        existingSpecifikacija.setInn(specifikacija.getInn());
                    }
                    if (specifikacija.getFarmaceutskiOblikLijeka() != null) {
                        existingSpecifikacija.setFarmaceutskiOblikLijeka(specifikacija.getFarmaceutskiOblikLijeka());
                    }
                    if (specifikacija.getJacinaLijeka() != null) {
                        existingSpecifikacija.setJacinaLijeka(specifikacija.getJacinaLijeka());
                    }
                    if (specifikacija.getTrazenaKolicina() != null) {
                        existingSpecifikacija.setTrazenaKolicina(specifikacija.getTrazenaKolicina());
                    }
                    if (specifikacija.getPakovanje() != null) {
                        existingSpecifikacija.setPakovanje(specifikacija.getPakovanje());
                    }
                    if (specifikacija.getProcijenjenaVrijednost() != null) {
                        existingSpecifikacija.setProcijenjenaVrijednost(specifikacija.getProcijenjenaVrijednost());
                    }
                    if (specifikacija.getTrazenaJedinicnaCijena() != null) {
                        existingSpecifikacija.setTrazenaJedinicnaCijena(specifikacija.getTrazenaJedinicnaCijena());
                    }

                    return existingSpecifikacija;
                }
            )
            .map(specifikacijaRepository::save);
    }

    /**
     * Get all the specifikacijas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Specifikacija> findAll(Pageable pageable) {
        log.debug("Request to get all Specifikacijas");
        return specifikacijaRepository.findAll(pageable);
    }

    /**
     * Get one specifikacija by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Specifikacija> findOne(Long id) {
        log.debug("Request to get Specifikacija : {}", id);
        return specifikacijaRepository.findById(id);
    }

    /**
     * Delete the specifikacija by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Specifikacija : {}", id);
        specifikacijaRepository.deleteById(id);
    }
}
