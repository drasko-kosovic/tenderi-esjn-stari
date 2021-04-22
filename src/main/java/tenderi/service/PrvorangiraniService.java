package tenderi.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenderi.domain.Prvorangirani;
import tenderi.repository.PrvorangiraniRepository;

/**
 * Service Implementation for managing {@link Prvorangirani}.
 */
@Service
@Transactional
public class PrvorangiraniService {

    private final Logger log = LoggerFactory.getLogger(PrvorangiraniService.class);

    private final PrvorangiraniRepository prvorangiraniRepository;

    public PrvorangiraniService(PrvorangiraniRepository prvorangiraniRepository) {
        this.prvorangiraniRepository = prvorangiraniRepository;
    }

    /**
     * Save a prvorangirani.
     *
     * @param prvorangirani the entity to save.
     * @return the persisted entity.
     */
    public Prvorangirani save(Prvorangirani prvorangirani) {
        log.debug("Request to save Prvorangirani : {}", prvorangirani);
        return prvorangiraniRepository.save(prvorangirani);
    }

    /**
     * Partially update a prvorangirani.
     *
     * @param prvorangirani the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Prvorangirani> partialUpdate(Prvorangirani prvorangirani) {
        log.debug("Request to partially update Prvorangirani : {}", prvorangirani);

        return prvorangiraniRepository
            .findById(prvorangirani.getId())
            .map(
                existingPrvorangirani -> {
                    if (prvorangirani.getSifraPostupka() != null) {
                        existingPrvorangirani.setSifraPostupka(prvorangirani.getSifraPostupka());
                    }
                    if (prvorangirani.getSifraPonude() != null) {
                        existingPrvorangirani.setSifraPonude(prvorangirani.getSifraPonude());
                    }
                    if (prvorangirani.getBrojPartije() != null) {
                        existingPrvorangirani.setBrojPartije(prvorangirani.getBrojPartije());
                    }
                    if (prvorangirani.getAtc() != null) {
                        existingPrvorangirani.setAtc(prvorangirani.getAtc());
                    }
                    if (prvorangirani.getInn() != null) {
                        existingPrvorangirani.setInn(prvorangirani.getInn());
                    }
                    if (prvorangirani.getZastceniNaziv() != null) {
                        existingPrvorangirani.setZastceniNaziv(prvorangirani.getZastceniNaziv());
                    }
                    if (prvorangirani.getFarmaceutskiOblikLijeka() != null) {
                        existingPrvorangirani.setFarmaceutskiOblikLijeka(prvorangirani.getFarmaceutskiOblikLijeka());
                    }
                    if (prvorangirani.getJacinaLijeka() != null) {
                        existingPrvorangirani.setJacinaLijeka(prvorangirani.getJacinaLijeka());
                    }
                    if (prvorangirani.getPakovanje() != null) {
                        existingPrvorangirani.setPakovanje(prvorangirani.getPakovanje());
                    }
                    if (prvorangirani.getTrazenaKolicina() != null) {
                        existingPrvorangirani.setTrazenaKolicina(prvorangirani.getTrazenaKolicina());
                    }
                    if (prvorangirani.getTrazenaJedinicnaCijena() != null) {
                        existingPrvorangirani.setTrazenaJedinicnaCijena(prvorangirani.getTrazenaJedinicnaCijena());
                    }
                    if (prvorangirani.getProcijenjenaVrijednost() != null) {
                        existingPrvorangirani.setProcijenjenaVrijednost(prvorangirani.getProcijenjenaVrijednost());
                    }
                    if (prvorangirani.getPonudjenaJedinicnaCijena() != null) {
                        existingPrvorangirani.setPonudjenaJedinicnaCijena(prvorangirani.getPonudjenaJedinicnaCijena());
                    }
                    if (prvorangirani.getPonudjenaVrijednost() != null) {
                        existingPrvorangirani.setPonudjenaVrijednost(prvorangirani.getPonudjenaVrijednost());
                    }
                    if (prvorangirani.getRokIsporuke() != null) {
                        existingPrvorangirani.setRokIsporuke(prvorangirani.getRokIsporuke());
                    }
                    if (prvorangirani.getNazivPonudjaca() != null) {
                        existingPrvorangirani.setNazivPonudjaca(prvorangirani.getNazivPonudjaca());
                    }
                    if (prvorangirani.getBodIsporuka() != null) {
                        existingPrvorangirani.setBodIsporuka(prvorangirani.getBodIsporuka());
                    }
                    if (prvorangirani.getBrojUgovora() != null) {
                        existingPrvorangirani.setBrojUgovora(prvorangirani.getBrojUgovora());
                    }
                    if (prvorangirani.getDatumUgovora() != null) {
                        existingPrvorangirani.setDatumUgovora(prvorangirani.getDatumUgovora());
                    }

                    return existingPrvorangirani;
                }
            )
            .map(prvorangiraniRepository::save);
    }

    /**
     * Get all the prvorangiranis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Prvorangirani> findAll(Pageable pageable) {
        log.debug("Request to get all Prvorangiranis");
        return prvorangiraniRepository.findAll(pageable);
    }

    /**
     * Get one prvorangirani by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Prvorangirani> findOne(Long id) {
        log.debug("Request to get Prvorangirani : {}", id);
        return prvorangiraniRepository.findById(id);
    }

    /**
     * Delete the prvorangirani by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Prvorangirani : {}", id);
        prvorangiraniRepository.deleteById(id);
    }
}
