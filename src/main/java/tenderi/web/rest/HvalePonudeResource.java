package tenderi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tenderi.domain.HvalePonude;
import tenderi.repository.HvalePonudeRepository;
import tenderi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tenderi.domain.HvalePonude}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HvalePonudeResource {

    private final Logger log = LoggerFactory.getLogger(HvalePonudeResource.class);

    private final HvalePonudeRepository hvalePonudeRepository;

    public HvalePonudeResource(HvalePonudeRepository hvalePonudeRepository) {
        this.hvalePonudeRepository = hvalePonudeRepository;
    }

    /**
     * {@code GET  /hvale-ponudes} : get all the hvalePonudes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hvalePonudes in body.
     */
    @GetMapping("/hvale-ponudes")
    public ResponseEntity<List<HvalePonude>> getAllHvalePonudes(Pageable pageable) {
        log.debug("REST request to get a page of HvalePonudes");
        Page<HvalePonude> page = hvalePonudeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hvale-ponudes/:id} : get the "id" hvalePonude.
     *
     * @param id the id of the hvalePonude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hvalePonude, or with status {@code 404 (Not Found)}.
     */

    @GetMapping("/hvale/{sifra}")
    public List<HvalePonude> getHvalePonude(@PathVariable Integer sifra) {
        List<HvalePonude> hvalePonude = hvalePonudeRepository.HvalePonude(sifra);
        return hvalePonude;
    }
}
