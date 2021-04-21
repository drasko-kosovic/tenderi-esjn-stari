package tenderi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tenderi.IntegrationTest;
import tenderi.domain.Specifikacija;
import tenderi.repository.SpecifikacijaRepository;
import tenderi.service.criteria.SpecifikacijaCriteria;

/**
 * Integration tests for the {@link SpecifikacijaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SpecifikacijaResourceIT {

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;
    private static final Integer SMALLER_SIFRA_POSTUPKA = 1 - 1;

    private static final Integer DEFAULT_BROJ_PARTIJE = 1;
    private static final Integer UPDATED_BROJ_PARTIJE = 2;
    private static final Integer SMALLER_BROJ_PARTIJE = 1 - 1;

    private static final String DEFAULT_ATC = "AAAAAAAAAA";
    private static final String UPDATED_ATC = "BBBBBBBBBB";

    private static final String DEFAULT_INN = "AAAAAAAAAA";
    private static final String UPDATED_INN = "BBBBBBBBBB";

    private static final String DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_FARMACEUTSKI_OBLIK_LIJEKA = "BBBBBBBBBB";

    private static final String DEFAULT_JACINA_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_JACINA_LIJEKA = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRAZENA_KOLICINA = 1;
    private static final Integer UPDATED_TRAZENA_KOLICINA = 2;
    private static final Integer SMALLER_TRAZENA_KOLICINA = 1 - 1;

    private static final String DEFAULT_PAKOVANJE = "AAAAAAAAAA";
    private static final String UPDATED_PAKOVANJE = "BBBBBBBBBB";

    private static final Double DEFAULT_PROCIJENJENA_VRIJEDNOST = 1D;
    private static final Double UPDATED_PROCIJENJENA_VRIJEDNOST = 2D;
    private static final Double SMALLER_PROCIJENJENA_VRIJEDNOST = 1D - 1D;

    private static final Double DEFAULT_TRAZENA_JEDINICNA_CIJENA = 1D;
    private static final Double UPDATED_TRAZENA_JEDINICNA_CIJENA = 2D;
    private static final Double SMALLER_TRAZENA_JEDINICNA_CIJENA = 1D - 1D;

    private static final String ENTITY_API_URL = "/api/specifikacijas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SpecifikacijaRepository specifikacijaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecifikacijaMockMvc;

    private Specifikacija specifikacija;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specifikacija createEntity(EntityManager em) {
        Specifikacija specifikacija = new Specifikacija()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .brojPartije(DEFAULT_BROJ_PARTIJE)
            .atc(DEFAULT_ATC)
            .inn(DEFAULT_INN)
            .farmaceutskiOblikLijeka(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(DEFAULT_JACINA_LIJEKA)
            .trazenaKolicina(DEFAULT_TRAZENA_KOLICINA)
            .pakovanje(DEFAULT_PAKOVANJE)
            .procijenjenaVrijednost(DEFAULT_PROCIJENJENA_VRIJEDNOST)
            .trazenaJedinicnaCijena(DEFAULT_TRAZENA_JEDINICNA_CIJENA);
        return specifikacija;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specifikacija createUpdatedEntity(EntityManager em) {
        Specifikacija specifikacija = new Specifikacija()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(UPDATED_JACINA_LIJEKA)
            .trazenaKolicina(UPDATED_TRAZENA_KOLICINA)
            .pakovanje(UPDATED_PAKOVANJE)
            .procijenjenaVrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .trazenaJedinicnaCijena(UPDATED_TRAZENA_JEDINICNA_CIJENA);
        return specifikacija;
    }

    @BeforeEach
    public void initTest() {
        specifikacija = createEntity(em);
    }

    @Test
    @Transactional
    void createSpecifikacija() throws Exception {
        int databaseSizeBeforeCreate = specifikacijaRepository.findAll().size();
        // Create the Specifikacija
        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isCreated());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeCreate + 1);
        Specifikacija testSpecifikacija = specifikacijaList.get(specifikacijaList.size() - 1);
        assertThat(testSpecifikacija.getSifraPostupka()).isEqualTo(DEFAULT_SIFRA_POSTUPKA);
        assertThat(testSpecifikacija.getBrojPartije()).isEqualTo(DEFAULT_BROJ_PARTIJE);
        assertThat(testSpecifikacija.getAtc()).isEqualTo(DEFAULT_ATC);
        assertThat(testSpecifikacija.getInn()).isEqualTo(DEFAULT_INN);
        assertThat(testSpecifikacija.getFarmaceutskiOblikLijeka()).isEqualTo(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testSpecifikacija.getJacinaLijeka()).isEqualTo(DEFAULT_JACINA_LIJEKA);
        assertThat(testSpecifikacija.getTrazenaKolicina()).isEqualTo(DEFAULT_TRAZENA_KOLICINA);
        assertThat(testSpecifikacija.getPakovanje()).isEqualTo(DEFAULT_PAKOVANJE);
        assertThat(testSpecifikacija.getProcijenjenaVrijednost()).isEqualTo(DEFAULT_PROCIJENJENA_VRIJEDNOST);
        assertThat(testSpecifikacija.getTrazenaJedinicnaCijena()).isEqualTo(DEFAULT_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void createSpecifikacijaWithExistingId() throws Exception {
        // Create the Specifikacija with an existing ID
        specifikacija.setId(1L);

        int databaseSizeBeforeCreate = specifikacijaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSifraPostupkaIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setSifraPostupka(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBrojPartijeIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setBrojPartije(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAtcIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setAtc(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTrazenaKolicinaIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setTrazenaKolicina(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProcijenjenaVrijednostIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setProcijenjenaVrijednost(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTrazenaJedinicnaCijenaIsRequired() throws Exception {
        int databaseSizeBeforeTest = specifikacijaRepository.findAll().size();
        // set the field null
        specifikacija.setTrazenaJedinicnaCijena(null);

        // Create the Specifikacija, which fails.

        restSpecifikacijaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isBadRequest());

        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSpecifikacijas() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specifikacija.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].farmaceutskiOblikLijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacinaLijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].trazenaKolicina").value(hasItem(DEFAULT_TRAZENA_KOLICINA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].procijenjenaVrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].trazenaJedinicnaCijena").value(hasItem(DEFAULT_TRAZENA_JEDINICNA_CIJENA.doubleValue())));
    }

    @Test
    @Transactional
    void getSpecifikacija() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get the specifikacija
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL_ID, specifikacija.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specifikacija.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.brojPartije").value(DEFAULT_BROJ_PARTIJE))
            .andExpect(jsonPath("$.atc").value(DEFAULT_ATC))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN))
            .andExpect(jsonPath("$.farmaceutskiOblikLijeka").value(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA))
            .andExpect(jsonPath("$.jacinaLijeka").value(DEFAULT_JACINA_LIJEKA))
            .andExpect(jsonPath("$.trazenaKolicina").value(DEFAULT_TRAZENA_KOLICINA))
            .andExpect(jsonPath("$.pakovanje").value(DEFAULT_PAKOVANJE))
            .andExpect(jsonPath("$.procijenjenaVrijednost").value(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.trazenaJedinicnaCijena").value(DEFAULT_TRAZENA_JEDINICNA_CIJENA.doubleValue()));
    }

    @Test
    @Transactional
    void getSpecifikacijasByIdFiltering() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        Long id = specifikacija.getId();

        defaultSpecifikacijaShouldBeFound("id.equals=" + id);
        defaultSpecifikacijaShouldNotBeFound("id.notEquals=" + id);

        defaultSpecifikacijaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSpecifikacijaShouldNotBeFound("id.greaterThan=" + id);

        defaultSpecifikacijaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSpecifikacijaShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka equals to DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.equals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.equals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka not equals to DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.notEquals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka not equals to UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.notEquals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka in DEFAULT_SIFRA_POSTUPKA or UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.in=" + DEFAULT_SIFRA_POSTUPKA + "," + UPDATED_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.in=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka is not null
        defaultSpecifikacijaShouldBeFound("sifraPostupka.specified=true");

        // Get all the specifikacijaList where sifraPostupka is null
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka is greater than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.greaterThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka is greater than or equal to UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.greaterThanOrEqual=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka is less than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.lessThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka is less than or equal to SMALLER_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.lessThanOrEqual=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsLessThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka is less than DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.lessThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka is less than UPDATED_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.lessThan=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasBySifraPostupkaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where sifraPostupka is greater than DEFAULT_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldNotBeFound("sifraPostupka.greaterThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the specifikacijaList where sifraPostupka is greater than SMALLER_SIFRA_POSTUPKA
        defaultSpecifikacijaShouldBeFound("sifraPostupka.greaterThan=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije equals to DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.equals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.equals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije not equals to DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.notEquals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije not equals to UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.notEquals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije in DEFAULT_BROJ_PARTIJE or UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.in=" + DEFAULT_BROJ_PARTIJE + "," + UPDATED_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.in=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije is not null
        defaultSpecifikacijaShouldBeFound("brojPartije.specified=true");

        // Get all the specifikacijaList where brojPartije is null
        defaultSpecifikacijaShouldNotBeFound("brojPartije.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije is greater than or equal to DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.greaterThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije is greater than or equal to UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.greaterThanOrEqual=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije is less than or equal to DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.lessThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije is less than or equal to SMALLER_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.lessThanOrEqual=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsLessThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije is less than DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.lessThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije is less than UPDATED_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.lessThan=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByBrojPartijeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where brojPartije is greater than DEFAULT_BROJ_PARTIJE
        defaultSpecifikacijaShouldNotBeFound("brojPartije.greaterThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the specifikacijaList where brojPartije is greater than SMALLER_BROJ_PARTIJE
        defaultSpecifikacijaShouldBeFound("brojPartije.greaterThan=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc equals to DEFAULT_ATC
        defaultSpecifikacijaShouldBeFound("atc.equals=" + DEFAULT_ATC);

        // Get all the specifikacijaList where atc equals to UPDATED_ATC
        defaultSpecifikacijaShouldNotBeFound("atc.equals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc not equals to DEFAULT_ATC
        defaultSpecifikacijaShouldNotBeFound("atc.notEquals=" + DEFAULT_ATC);

        // Get all the specifikacijaList where atc not equals to UPDATED_ATC
        defaultSpecifikacijaShouldBeFound("atc.notEquals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc in DEFAULT_ATC or UPDATED_ATC
        defaultSpecifikacijaShouldBeFound("atc.in=" + DEFAULT_ATC + "," + UPDATED_ATC);

        // Get all the specifikacijaList where atc equals to UPDATED_ATC
        defaultSpecifikacijaShouldNotBeFound("atc.in=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc is not null
        defaultSpecifikacijaShouldBeFound("atc.specified=true");

        // Get all the specifikacijaList where atc is null
        defaultSpecifikacijaShouldNotBeFound("atc.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc contains DEFAULT_ATC
        defaultSpecifikacijaShouldBeFound("atc.contains=" + DEFAULT_ATC);

        // Get all the specifikacijaList where atc contains UPDATED_ATC
        defaultSpecifikacijaShouldNotBeFound("atc.contains=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByAtcNotContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where atc does not contain DEFAULT_ATC
        defaultSpecifikacijaShouldNotBeFound("atc.doesNotContain=" + DEFAULT_ATC);

        // Get all the specifikacijaList where atc does not contain UPDATED_ATC
        defaultSpecifikacijaShouldBeFound("atc.doesNotContain=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn equals to DEFAULT_INN
        defaultSpecifikacijaShouldBeFound("inn.equals=" + DEFAULT_INN);

        // Get all the specifikacijaList where inn equals to UPDATED_INN
        defaultSpecifikacijaShouldNotBeFound("inn.equals=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn not equals to DEFAULT_INN
        defaultSpecifikacijaShouldNotBeFound("inn.notEquals=" + DEFAULT_INN);

        // Get all the specifikacijaList where inn not equals to UPDATED_INN
        defaultSpecifikacijaShouldBeFound("inn.notEquals=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn in DEFAULT_INN or UPDATED_INN
        defaultSpecifikacijaShouldBeFound("inn.in=" + DEFAULT_INN + "," + UPDATED_INN);

        // Get all the specifikacijaList where inn equals to UPDATED_INN
        defaultSpecifikacijaShouldNotBeFound("inn.in=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn is not null
        defaultSpecifikacijaShouldBeFound("inn.specified=true");

        // Get all the specifikacijaList where inn is null
        defaultSpecifikacijaShouldNotBeFound("inn.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn contains DEFAULT_INN
        defaultSpecifikacijaShouldBeFound("inn.contains=" + DEFAULT_INN);

        // Get all the specifikacijaList where inn contains UPDATED_INN
        defaultSpecifikacijaShouldNotBeFound("inn.contains=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByInnNotContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where inn does not contain DEFAULT_INN
        defaultSpecifikacijaShouldNotBeFound("inn.doesNotContain=" + DEFAULT_INN);

        // Get all the specifikacijaList where inn does not contain UPDATED_INN
        defaultSpecifikacijaShouldBeFound("inn.doesNotContain=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka equals to DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldBeFound("farmaceutskiOblikLijeka.equals=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.equals=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka not equals to DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.notEquals=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka not equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldBeFound("farmaceutskiOblikLijeka.notEquals=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka in DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA or UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldBeFound(
            "farmaceutskiOblikLijeka.in=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA + "," + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        );

        // Get all the specifikacijaList where farmaceutskiOblikLijeka equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.in=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka is not null
        defaultSpecifikacijaShouldBeFound("farmaceutskiOblikLijeka.specified=true");

        // Get all the specifikacijaList where farmaceutskiOblikLijeka is null
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka contains DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldBeFound("farmaceutskiOblikLijeka.contains=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka contains UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.contains=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByFarmaceutskiOblikLijekaNotContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka does not contain DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("farmaceutskiOblikLijeka.doesNotContain=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the specifikacijaList where farmaceutskiOblikLijeka does not contain UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultSpecifikacijaShouldBeFound("farmaceutskiOblikLijeka.doesNotContain=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka equals to DEFAULT_JACINA_LIJEKA
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.equals=" + DEFAULT_JACINA_LIJEKA);

        // Get all the specifikacijaList where jacinaLijeka equals to UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.equals=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka not equals to DEFAULT_JACINA_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.notEquals=" + DEFAULT_JACINA_LIJEKA);

        // Get all the specifikacijaList where jacinaLijeka not equals to UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.notEquals=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka in DEFAULT_JACINA_LIJEKA or UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.in=" + DEFAULT_JACINA_LIJEKA + "," + UPDATED_JACINA_LIJEKA);

        // Get all the specifikacijaList where jacinaLijeka equals to UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.in=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka is not null
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.specified=true");

        // Get all the specifikacijaList where jacinaLijeka is null
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka contains DEFAULT_JACINA_LIJEKA
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.contains=" + DEFAULT_JACINA_LIJEKA);

        // Get all the specifikacijaList where jacinaLijeka contains UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.contains=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByJacinaLijekaNotContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where jacinaLijeka does not contain DEFAULT_JACINA_LIJEKA
        defaultSpecifikacijaShouldNotBeFound("jacinaLijeka.doesNotContain=" + DEFAULT_JACINA_LIJEKA);

        // Get all the specifikacijaList where jacinaLijeka does not contain UPDATED_JACINA_LIJEKA
        defaultSpecifikacijaShouldBeFound("jacinaLijeka.doesNotContain=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina equals to DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.equals=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina equals to UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.equals=" + UPDATED_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina not equals to DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.notEquals=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina not equals to UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.notEquals=" + UPDATED_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina in DEFAULT_TRAZENA_KOLICINA or UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.in=" + DEFAULT_TRAZENA_KOLICINA + "," + UPDATED_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina equals to UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.in=" + UPDATED_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina is not null
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.specified=true");

        // Get all the specifikacijaList where trazenaKolicina is null
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina is greater than or equal to DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.greaterThanOrEqual=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina is greater than or equal to UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.greaterThanOrEqual=" + UPDATED_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina is less than or equal to DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.lessThanOrEqual=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina is less than or equal to SMALLER_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.lessThanOrEqual=" + SMALLER_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsLessThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina is less than DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.lessThan=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina is less than UPDATED_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.lessThan=" + UPDATED_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaKolicinaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaKolicina is greater than DEFAULT_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldNotBeFound("trazenaKolicina.greaterThan=" + DEFAULT_TRAZENA_KOLICINA);

        // Get all the specifikacijaList where trazenaKolicina is greater than SMALLER_TRAZENA_KOLICINA
        defaultSpecifikacijaShouldBeFound("trazenaKolicina.greaterThan=" + SMALLER_TRAZENA_KOLICINA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje equals to DEFAULT_PAKOVANJE
        defaultSpecifikacijaShouldBeFound("pakovanje.equals=" + DEFAULT_PAKOVANJE);

        // Get all the specifikacijaList where pakovanje equals to UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldNotBeFound("pakovanje.equals=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje not equals to DEFAULT_PAKOVANJE
        defaultSpecifikacijaShouldNotBeFound("pakovanje.notEquals=" + DEFAULT_PAKOVANJE);

        // Get all the specifikacijaList where pakovanje not equals to UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldBeFound("pakovanje.notEquals=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje in DEFAULT_PAKOVANJE or UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldBeFound("pakovanje.in=" + DEFAULT_PAKOVANJE + "," + UPDATED_PAKOVANJE);

        // Get all the specifikacijaList where pakovanje equals to UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldNotBeFound("pakovanje.in=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje is not null
        defaultSpecifikacijaShouldBeFound("pakovanje.specified=true");

        // Get all the specifikacijaList where pakovanje is null
        defaultSpecifikacijaShouldNotBeFound("pakovanje.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje contains DEFAULT_PAKOVANJE
        defaultSpecifikacijaShouldBeFound("pakovanje.contains=" + DEFAULT_PAKOVANJE);

        // Get all the specifikacijaList where pakovanje contains UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldNotBeFound("pakovanje.contains=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByPakovanjeNotContainsSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where pakovanje does not contain DEFAULT_PAKOVANJE
        defaultSpecifikacijaShouldNotBeFound("pakovanje.doesNotContain=" + DEFAULT_PAKOVANJE);

        // Get all the specifikacijaList where pakovanje does not contain UPDATED_PAKOVANJE
        defaultSpecifikacijaShouldBeFound("pakovanje.doesNotContain=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost equals to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.equals=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.equals=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost not equals to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.notEquals=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost not equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.notEquals=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost in DEFAULT_PROCIJENJENA_VRIJEDNOST or UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound(
            "procijenjenaVrijednost.in=" + DEFAULT_PROCIJENJENA_VRIJEDNOST + "," + UPDATED_PROCIJENJENA_VRIJEDNOST
        );

        // Get all the specifikacijaList where procijenjenaVrijednost equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.in=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost is not null
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.specified=true");

        // Get all the specifikacijaList where procijenjenaVrijednost is null
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost is greater than or equal to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.greaterThanOrEqual=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost is greater than or equal to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.greaterThanOrEqual=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost is less than or equal to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.lessThanOrEqual=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost is less than or equal to SMALLER_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.lessThanOrEqual=" + SMALLER_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsLessThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost is less than DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.lessThan=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost is less than UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.lessThan=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByProcijenjenaVrijednostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where procijenjenaVrijednost is greater than DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldNotBeFound("procijenjenaVrijednost.greaterThan=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the specifikacijaList where procijenjenaVrijednost is greater than SMALLER_PROCIJENJENA_VRIJEDNOST
        defaultSpecifikacijaShouldBeFound("procijenjenaVrijednost.greaterThan=" + SMALLER_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena equals to DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.equals=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena equals to UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.equals=" + UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena not equals to DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.notEquals=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena not equals to UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.notEquals=" + UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsInShouldWork() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena in DEFAULT_TRAZENA_JEDINICNA_CIJENA or UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound(
            "trazenaJedinicnaCijena.in=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA + "," + UPDATED_TRAZENA_JEDINICNA_CIJENA
        );

        // Get all the specifikacijaList where trazenaJedinicnaCijena equals to UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.in=" + UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is not null
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.specified=true");

        // Get all the specifikacijaList where trazenaJedinicnaCijena is null
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.specified=false");
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is greater than or equal to DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.greaterThanOrEqual=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is greater than or equal to UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.greaterThanOrEqual=" + UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is less than or equal to DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.lessThanOrEqual=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is less than or equal to SMALLER_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.lessThanOrEqual=" + SMALLER_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsLessThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is less than DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.lessThan=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is less than UPDATED_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.lessThan=" + UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void getAllSpecifikacijasByTrazenaJedinicnaCijenaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is greater than DEFAULT_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldNotBeFound("trazenaJedinicnaCijena.greaterThan=" + DEFAULT_TRAZENA_JEDINICNA_CIJENA);

        // Get all the specifikacijaList where trazenaJedinicnaCijena is greater than SMALLER_TRAZENA_JEDINICNA_CIJENA
        defaultSpecifikacijaShouldBeFound("trazenaJedinicnaCijena.greaterThan=" + SMALLER_TRAZENA_JEDINICNA_CIJENA);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSpecifikacijaShouldBeFound(String filter) throws Exception {
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specifikacija.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].farmaceutskiOblikLijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacinaLijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].trazenaKolicina").value(hasItem(DEFAULT_TRAZENA_KOLICINA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].procijenjenaVrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].trazenaJedinicnaCijena").value(hasItem(DEFAULT_TRAZENA_JEDINICNA_CIJENA.doubleValue())));

        // Check, that the count call also returns 1
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSpecifikacijaShouldNotBeFound(String filter) throws Exception {
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSpecifikacijaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingSpecifikacija() throws Exception {
        // Get the specifikacija
        restSpecifikacijaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSpecifikacija() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();

        // Update the specifikacija
        Specifikacija updatedSpecifikacija = specifikacijaRepository.findById(specifikacija.getId()).get();
        // Disconnect from session so that the updates on updatedSpecifikacija are not directly saved in db
        em.detach(updatedSpecifikacija);
        updatedSpecifikacija
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(UPDATED_JACINA_LIJEKA)
            .trazenaKolicina(UPDATED_TRAZENA_KOLICINA)
            .pakovanje(UPDATED_PAKOVANJE)
            .procijenjenaVrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .trazenaJedinicnaCijena(UPDATED_TRAZENA_JEDINICNA_CIJENA);

        restSpecifikacijaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSpecifikacija.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSpecifikacija))
            )
            .andExpect(status().isOk());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
        Specifikacija testSpecifikacija = specifikacijaList.get(specifikacijaList.size() - 1);
        assertThat(testSpecifikacija.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testSpecifikacija.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testSpecifikacija.getAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testSpecifikacija.getInn()).isEqualTo(UPDATED_INN);
        assertThat(testSpecifikacija.getFarmaceutskiOblikLijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testSpecifikacija.getJacinaLijeka()).isEqualTo(UPDATED_JACINA_LIJEKA);
        assertThat(testSpecifikacija.getTrazenaKolicina()).isEqualTo(UPDATED_TRAZENA_KOLICINA);
        assertThat(testSpecifikacija.getPakovanje()).isEqualTo(UPDATED_PAKOVANJE);
        assertThat(testSpecifikacija.getProcijenjenaVrijednost()).isEqualTo(UPDATED_PROCIJENJENA_VRIJEDNOST);
        assertThat(testSpecifikacija.getTrazenaJedinicnaCijena()).isEqualTo(UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void putNonExistingSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, specifikacija.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(specifikacija))
            )
            .andExpect(status().isBadRequest());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(specifikacija))
            )
            .andExpect(status().isBadRequest());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(specifikacija)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSpecifikacijaWithPatch() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();

        // Update the specifikacija using partial update
        Specifikacija partialUpdatedSpecifikacija = new Specifikacija();
        partialUpdatedSpecifikacija.setId(specifikacija.getId());

        partialUpdatedSpecifikacija
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .atc(UPDATED_ATC)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .trazenaKolicina(UPDATED_TRAZENA_KOLICINA);

        restSpecifikacijaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSpecifikacija.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpecifikacija))
            )
            .andExpect(status().isOk());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
        Specifikacija testSpecifikacija = specifikacijaList.get(specifikacijaList.size() - 1);
        assertThat(testSpecifikacija.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testSpecifikacija.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testSpecifikacija.getAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testSpecifikacija.getInn()).isEqualTo(DEFAULT_INN);
        assertThat(testSpecifikacija.getFarmaceutskiOblikLijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testSpecifikacija.getJacinaLijeka()).isEqualTo(DEFAULT_JACINA_LIJEKA);
        assertThat(testSpecifikacija.getTrazenaKolicina()).isEqualTo(UPDATED_TRAZENA_KOLICINA);
        assertThat(testSpecifikacija.getPakovanje()).isEqualTo(DEFAULT_PAKOVANJE);
        assertThat(testSpecifikacija.getProcijenjenaVrijednost()).isEqualTo(DEFAULT_PROCIJENJENA_VRIJEDNOST);
        assertThat(testSpecifikacija.getTrazenaJedinicnaCijena()).isEqualTo(DEFAULT_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void fullUpdateSpecifikacijaWithPatch() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();

        // Update the specifikacija using partial update
        Specifikacija partialUpdatedSpecifikacija = new Specifikacija();
        partialUpdatedSpecifikacija.setId(specifikacija.getId());

        partialUpdatedSpecifikacija
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(UPDATED_JACINA_LIJEKA)
            .trazenaKolicina(UPDATED_TRAZENA_KOLICINA)
            .pakovanje(UPDATED_PAKOVANJE)
            .procijenjenaVrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .trazenaJedinicnaCijena(UPDATED_TRAZENA_JEDINICNA_CIJENA);

        restSpecifikacijaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSpecifikacija.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpecifikacija))
            )
            .andExpect(status().isOk());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
        Specifikacija testSpecifikacija = specifikacijaList.get(specifikacijaList.size() - 1);
        assertThat(testSpecifikacija.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testSpecifikacija.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testSpecifikacija.getAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testSpecifikacija.getInn()).isEqualTo(UPDATED_INN);
        assertThat(testSpecifikacija.getFarmaceutskiOblikLijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testSpecifikacija.getJacinaLijeka()).isEqualTo(UPDATED_JACINA_LIJEKA);
        assertThat(testSpecifikacija.getTrazenaKolicina()).isEqualTo(UPDATED_TRAZENA_KOLICINA);
        assertThat(testSpecifikacija.getPakovanje()).isEqualTo(UPDATED_PAKOVANJE);
        assertThat(testSpecifikacija.getProcijenjenaVrijednost()).isEqualTo(UPDATED_PROCIJENJENA_VRIJEDNOST);
        assertThat(testSpecifikacija.getTrazenaJedinicnaCijena()).isEqualTo(UPDATED_TRAZENA_JEDINICNA_CIJENA);
    }

    @Test
    @Transactional
    void patchNonExistingSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, specifikacija.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(specifikacija))
            )
            .andExpect(status().isBadRequest());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(specifikacija))
            )
            .andExpect(status().isBadRequest());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSpecifikacija() throws Exception {
        int databaseSizeBeforeUpdate = specifikacijaRepository.findAll().size();
        specifikacija.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpecifikacijaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(specifikacija))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Specifikacija in the database
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSpecifikacija() throws Exception {
        // Initialize the database
        specifikacijaRepository.saveAndFlush(specifikacija);

        int databaseSizeBeforeDelete = specifikacijaRepository.findAll().size();

        // Delete the specifikacija
        restSpecifikacijaMockMvc
            .perform(delete(ENTITY_API_URL_ID, specifikacija.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specifikacija> specifikacijaList = specifikacijaRepository.findAll();
        assertThat(specifikacijaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
