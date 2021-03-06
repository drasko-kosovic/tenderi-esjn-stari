package tenderi.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenderi.domain.Ponude;
import tenderi.repository.PonudeRepository;

/**
 * Service Implementation for managing {@link Ponude}.
 */
@Service
@Transactional
public class PonudeService {

    private final Logger log = LoggerFactory.getLogger(PonudeService.class);

    private final PonudeRepository ponudeRepository;

    public PonudeService(PonudeRepository ponudeRepository) {
        this.ponudeRepository = ponudeRepository;
    }

    /**
     * Save a ponude.
     *
     * @param ponude the entity to save.
     * @return the persisted entity.
     */
    public Ponude save(Ponude ponude) {
        log.debug("Request to save Ponude : {}", ponude);
        return ponudeRepository.save(ponude);
    }

    /**
     * Partially update a ponude.
     *
     * @param ponude the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Ponude> partialUpdate(Ponude ponude) {
        log.debug("Request to partially update Ponude : {}", ponude);

        return ponudeRepository
            .findById(ponude.getId())
            .map(
                existingPonude -> {
                    if (ponude.getSifraPostupka() != null) {
                        existingPonude.setSifraPostupka(ponude.getSifraPostupka());
                    }
                    if (ponude.getSifraPonude() != null) {
                        existingPonude.setSifraPonude(ponude.getSifraPonude());
                    }
                    if (ponude.getBrojPartije() != null) {
                        existingPonude.setBrojPartije(ponude.getBrojPartije());
                    }
                    if (ponude.getNazivPonudjaca() != null) {
                        existingPonude.setNazivPonudjaca(ponude.getNazivPonudjaca());
                    }
                    if (ponude.getNazivProizvodjaca() != null) {
                        existingPonude.setNazivProizvodjaca(ponude.getNazivProizvodjaca());
                    }
                    if (ponude.getZastceniNaziv() != null) {
                        existingPonude.setZastceniNaziv(ponude.getZastceniNaziv());
                    }
                    if (ponude.getPonudjenaVrijednost() != null) {
                        existingPonude.setPonudjenaVrijednost(ponude.getPonudjenaVrijednost());
                    }
                    if (ponude.getPonudjenaJedinicnaCijena() != null) {
                        existingPonude.setPonudjenaJedinicnaCijena(ponude.getPonudjenaJedinicnaCijena());
                    }
                    if (ponude.getRokIsporuke() != null) {
                        existingPonude.setRokIsporuke(ponude.getRokIsporuke());
                    }
                    if (ponude.getBrojUgovora() != null) {
                        existingPonude.setBrojUgovora(ponude.getBrojUgovora());
                    }
                    if (ponude.getDatumUgovora() != null) {
                        existingPonude.setDatumUgovora(ponude.getDatumUgovora());
                    }

                    return existingPonude;
                }
            )
            .map(ponudeRepository::save);
    }

    /**
     * Get all the ponudes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Ponude> findAll(Pageable pageable) {
        log.debug("Request to get all Ponudes");
        return ponudeRepository.findAll(pageable);
    }

    /**
     * Get one ponude by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Ponude> findOne(Long id) {
        log.debug("Request to get Ponude : {}", id);
        return ponudeRepository.findById(id);
    }

    /**
     * Delete the ponude by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ponude : {}", id);
        ponudeRepository.deleteById(id);
    }
}
