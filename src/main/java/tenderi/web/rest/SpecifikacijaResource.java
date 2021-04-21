package tenderi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tenderi.domain.Specifikacija;
import tenderi.repository.SpecifikacijaRepository;
import tenderi.service.SpecifikacijaQueryService;
import tenderi.service.SpecifikacijaService;
import tenderi.service.criteria.SpecifikacijaCriteria;
import tenderi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tenderi.domain.Specifikacija}.
 */
@RestController
@RequestMapping("/api")
public class SpecifikacijaResource {

    private final Logger log = LoggerFactory.getLogger(SpecifikacijaResource.class);

    private static final String ENTITY_NAME = "specifikacija";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecifikacijaService specifikacijaService;

    private final SpecifikacijaRepository specifikacijaRepository;

    private final SpecifikacijaQueryService specifikacijaQueryService;

    public SpecifikacijaResource(
        SpecifikacijaService specifikacijaService,
        SpecifikacijaRepository specifikacijaRepository,
        SpecifikacijaQueryService specifikacijaQueryService
    ) {
        this.specifikacijaService = specifikacijaService;
        this.specifikacijaRepository = specifikacijaRepository;
        this.specifikacijaQueryService = specifikacijaQueryService;
    }

    /**
     * {@code POST  /specifikacijas} : Create a new specifikacija.
     *
     * @param specifikacija the specifikacija to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specifikacija, or with status {@code 400 (Bad Request)} if the specifikacija has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specifikacijas")
    public ResponseEntity<Specifikacija> createSpecifikacija(@Valid @RequestBody Specifikacija specifikacija) throws URISyntaxException {
        log.debug("REST request to save Specifikacija : {}", specifikacija);
        if (specifikacija.getId() != null) {
            throw new BadRequestAlertException("A new specifikacija cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Specifikacija result = specifikacijaService.save(specifikacija);
        return ResponseEntity
            .created(new URI("/api/specifikacijas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specifikacijas/:id} : Updates an existing specifikacija.
     *
     * @param id the id of the specifikacija to save.
     * @param specifikacija the specifikacija to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specifikacija,
     * or with status {@code 400 (Bad Request)} if the specifikacija is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specifikacija couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specifikacijas/{id}")
    public ResponseEntity<Specifikacija> updateSpecifikacija(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Specifikacija specifikacija
    ) throws URISyntaxException {
        log.debug("REST request to update Specifikacija : {}, {}", id, specifikacija);
        if (specifikacija.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, specifikacija.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!specifikacijaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Specifikacija result = specifikacijaService.save(specifikacija);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specifikacija.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /specifikacijas/:id} : Partial updates given fields of an existing specifikacija, field will ignore if it is null
     *
     * @param id the id of the specifikacija to save.
     * @param specifikacija the specifikacija to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specifikacija,
     * or with status {@code 400 (Bad Request)} if the specifikacija is not valid,
     * or with status {@code 404 (Not Found)} if the specifikacija is not found,
     * or with status {@code 500 (Internal Server Error)} if the specifikacija couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/specifikacijas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Specifikacija> partialUpdateSpecifikacija(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Specifikacija specifikacija
    ) throws URISyntaxException {
        log.debug("REST request to partial update Specifikacija partially : {}, {}", id, specifikacija);
        if (specifikacija.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, specifikacija.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!specifikacijaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Specifikacija> result = specifikacijaService.partialUpdate(specifikacija);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specifikacija.getId().toString())
        );
    }

    /**
     * {@code GET  /specifikacijas} : get all the specifikacijas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specifikacijas in body.
     */
    @GetMapping("/specifikacijas")
    public ResponseEntity<List<Specifikacija>> getAllSpecifikacijas(SpecifikacijaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Specifikacijas by criteria: {}", criteria);
        Page<Specifikacija> page = specifikacijaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specifikacijas/count} : count all the specifikacijas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/specifikacijas/count")
    public ResponseEntity<Long> countSpecifikacijas(SpecifikacijaCriteria criteria) {
        log.debug("REST request to count Specifikacijas by criteria: {}", criteria);
        return ResponseEntity.ok().body(specifikacijaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /specifikacijas/:id} : get the "id" specifikacija.
     *
     * @param id the id of the specifikacija to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specifikacija, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specifikacijas/{id}")
    public ResponseEntity<Specifikacija> getSpecifikacija(@PathVariable Long id) {
        log.debug("REST request to get Specifikacija : {}", id);
        Optional<Specifikacija> specifikacija = specifikacijaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specifikacija);
    }

    /**
     * {@code DELETE  /specifikacijas/:id} : delete the "id" specifikacija.
     *
     * @param id the id of the specifikacija to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specifikacijas/{id}")
    public ResponseEntity<Void> deleteSpecifikacija(@PathVariable Long id) {
        log.debug("REST request to delete Specifikacija : {}", id);
        specifikacijaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
