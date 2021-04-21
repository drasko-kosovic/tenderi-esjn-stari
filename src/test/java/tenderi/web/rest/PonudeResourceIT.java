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
import tenderi.domain.Ponude;
import tenderi.repository.PonudeRepository;
import tenderi.service.criteria.PonudeCriteria;

/**
 * Integration tests for the {@link PonudeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PonudeResourceIT {

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;
    private static final Integer SMALLER_SIFRA_POSTUPKA = 1 - 1;

    private static final Integer DEFAULT_SIFRA_PONUDE = 1;
    private static final Integer UPDATED_SIFRA_PONUDE = 2;
    private static final Integer SMALLER_SIFRA_PONUDE = 1 - 1;

    private static final Integer DEFAULT_BROJ_PARTIJE = 1;
    private static final Integer UPDATED_BROJ_PARTIJE = 2;
    private static final Integer SMALLER_BROJ_PARTIJE = 1 - 1;

    private static final String DEFAULT_NAZIV_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PONUDJACA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZI_PROIZVODJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZI_PROIZVODJACA = "BBBBBBBBBB";

    private static final String DEFAULT_ZASTCENI_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_ZASTCENI_NAZIV = "BBBBBBBBBB";

    private static final Double DEFAULT_PONUDJENA_VRIJEDNOST = 1D;
    private static final Double UPDATED_PONUDJENA_VRIJEDNOST = 2D;
    private static final Double SMALLER_PONUDJENA_VRIJEDNOST = 1D - 1D;

    private static final Integer DEFAULT_ROK_ISPORUKE = 1;
    private static final Integer UPDATED_ROK_ISPORUKE = 2;
    private static final Integer SMALLER_ROK_ISPORUKE = 1 - 1;

    private static final String ENTITY_API_URL = "/api/ponudes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PonudeRepository ponudeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPonudeMockMvc;

    private Ponude ponude;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ponude createEntity(EntityManager em) {
        Ponude ponude = new Ponude()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .sifraPonude(DEFAULT_SIFRA_PONUDE)
            .brojPartije(DEFAULT_BROJ_PARTIJE)
            .nazivPonudjaca(DEFAULT_NAZIV_PONUDJACA)
            .naziProizvodjaca(DEFAULT_NAZI_PROIZVODJACA)
            .zastceniNaziv(DEFAULT_ZASTCENI_NAZIV)
            .ponudjenaVrijednost(DEFAULT_PONUDJENA_VRIJEDNOST)
            .rokIsporuke(DEFAULT_ROK_ISPORUKE);
        return ponude;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ponude createUpdatedEntity(EntityManager em) {
        Ponude ponude = new Ponude()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziProizvodjaca(UPDATED_NAZI_PROIZVODJACA)
            .zastceniNaziv(UPDATED_ZASTCENI_NAZIV)
            .ponudjenaVrijednost(UPDATED_PONUDJENA_VRIJEDNOST)
            .rokIsporuke(UPDATED_ROK_ISPORUKE);
        return ponude;
    }

    @BeforeEach
    public void initTest() {
        ponude = createEntity(em);
    }

    @Test
    @Transactional
    void createPonude() throws Exception {
        int databaseSizeBeforeCreate = ponudeRepository.findAll().size();
        // Create the Ponude
        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isCreated());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeCreate + 1);
        Ponude testPonude = ponudeList.get(ponudeList.size() - 1);
        assertThat(testPonude.getSifraPostupka()).isEqualTo(DEFAULT_SIFRA_POSTUPKA);
        assertThat(testPonude.getSifraPonude()).isEqualTo(DEFAULT_SIFRA_PONUDE);
        assertThat(testPonude.getBrojPartije()).isEqualTo(DEFAULT_BROJ_PARTIJE);
        assertThat(testPonude.getNazivPonudjaca()).isEqualTo(DEFAULT_NAZIV_PONUDJACA);
        assertThat(testPonude.getNaziProizvodjaca()).isEqualTo(DEFAULT_NAZI_PROIZVODJACA);
        assertThat(testPonude.getZastceniNaziv()).isEqualTo(DEFAULT_ZASTCENI_NAZIV);
        assertThat(testPonude.getPonudjenaVrijednost()).isEqualTo(DEFAULT_PONUDJENA_VRIJEDNOST);
        assertThat(testPonude.getRokIsporuke()).isEqualTo(DEFAULT_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void createPonudeWithExistingId() throws Exception {
        // Create the Ponude with an existing ID
        ponude.setId(1L);

        int databaseSizeBeforeCreate = ponudeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSifraPostupkaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setSifraPostupka(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSifraPonudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setSifraPonude(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBrojPartijeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setBrojPartije(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNazivPonudjacaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setNazivPonudjaca(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNaziProizvodjacaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setNaziProizvodjaca(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkZastceniNazivIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setZastceniNaziv(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPonudjenaVrijednostIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudeRepository.findAll().size();
        // set the field null
        ponude.setPonudjenaVrijednost(null);

        // Create the Ponude, which fails.

        restPonudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isBadRequest());

        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPonudes() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ponude.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].naziProizvodjaca").value(hasItem(DEFAULT_NAZI_PROIZVODJACA)))
            .andExpect(jsonPath("$.[*].zastceniNaziv").value(hasItem(DEFAULT_ZASTCENI_NAZIV)))
            .andExpect(jsonPath("$.[*].ponudjenaVrijednost").value(hasItem(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].rokIsporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)));
    }

    @Test
    @Transactional
    void getPonude() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get the ponude
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL_ID, ponude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ponude.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifraPonude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.brojPartije").value(DEFAULT_BROJ_PARTIJE))
            .andExpect(jsonPath("$.nazivPonudjaca").value(DEFAULT_NAZIV_PONUDJACA))
            .andExpect(jsonPath("$.naziProizvodjaca").value(DEFAULT_NAZI_PROIZVODJACA))
            .andExpect(jsonPath("$.zastceniNaziv").value(DEFAULT_ZASTCENI_NAZIV))
            .andExpect(jsonPath("$.ponudjenaVrijednost").value(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.rokIsporuke").value(DEFAULT_ROK_ISPORUKE));
    }

    @Test
    @Transactional
    void getPonudesByIdFiltering() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        Long id = ponude.getId();

        defaultPonudeShouldBeFound("id.equals=" + id);
        defaultPonudeShouldNotBeFound("id.notEquals=" + id);

        defaultPonudeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPonudeShouldNotBeFound("id.greaterThan=" + id);

        defaultPonudeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPonudeShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka equals to DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.equals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.equals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka not equals to DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.notEquals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka not equals to UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.notEquals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka in DEFAULT_SIFRA_POSTUPKA or UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.in=" + DEFAULT_SIFRA_POSTUPKA + "," + UPDATED_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.in=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka is not null
        defaultPonudeShouldBeFound("sifraPostupka.specified=true");

        // Get all the ponudeList where sifraPostupka is null
        defaultPonudeShouldNotBeFound("sifraPostupka.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka is greater than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.greaterThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka is greater than or equal to UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.greaterThanOrEqual=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka is less than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.lessThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka is less than or equal to SMALLER_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.lessThanOrEqual=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsLessThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka is less than DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.lessThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka is less than UPDATED_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.lessThan=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPostupkaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPostupka is greater than DEFAULT_SIFRA_POSTUPKA
        defaultPonudeShouldNotBeFound("sifraPostupka.greaterThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the ponudeList where sifraPostupka is greater than SMALLER_SIFRA_POSTUPKA
        defaultPonudeShouldBeFound("sifraPostupka.greaterThan=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude equals to DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.equals=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude equals to UPDATED_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.equals=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude not equals to DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.notEquals=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude not equals to UPDATED_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.notEquals=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude in DEFAULT_SIFRA_PONUDE or UPDATED_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.in=" + DEFAULT_SIFRA_PONUDE + "," + UPDATED_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude equals to UPDATED_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.in=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude is not null
        defaultPonudeShouldBeFound("sifraPonude.specified=true");

        // Get all the ponudeList where sifraPonude is null
        defaultPonudeShouldNotBeFound("sifraPonude.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude is greater than or equal to DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.greaterThanOrEqual=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude is greater than or equal to UPDATED_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.greaterThanOrEqual=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude is less than or equal to DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.lessThanOrEqual=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude is less than or equal to SMALLER_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.lessThanOrEqual=" + SMALLER_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsLessThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude is less than DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.lessThan=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude is less than UPDATED_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.lessThan=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesBySifraPonudeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where sifraPonude is greater than DEFAULT_SIFRA_PONUDE
        defaultPonudeShouldNotBeFound("sifraPonude.greaterThan=" + DEFAULT_SIFRA_PONUDE);

        // Get all the ponudeList where sifraPonude is greater than SMALLER_SIFRA_PONUDE
        defaultPonudeShouldBeFound("sifraPonude.greaterThan=" + SMALLER_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije equals to DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.equals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.equals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije not equals to DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.notEquals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije not equals to UPDATED_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.notEquals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije in DEFAULT_BROJ_PARTIJE or UPDATED_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.in=" + DEFAULT_BROJ_PARTIJE + "," + UPDATED_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.in=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije is not null
        defaultPonudeShouldBeFound("brojPartije.specified=true");

        // Get all the ponudeList where brojPartije is null
        defaultPonudeShouldNotBeFound("brojPartije.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije is greater than or equal to DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.greaterThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije is greater than or equal to UPDATED_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.greaterThanOrEqual=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije is less than or equal to DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.lessThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije is less than or equal to SMALLER_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.lessThanOrEqual=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsLessThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije is less than DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.lessThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije is less than UPDATED_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.lessThan=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByBrojPartijeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where brojPartije is greater than DEFAULT_BROJ_PARTIJE
        defaultPonudeShouldNotBeFound("brojPartije.greaterThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the ponudeList where brojPartije is greater than SMALLER_BROJ_PARTIJE
        defaultPonudeShouldBeFound("brojPartije.greaterThan=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca equals to DEFAULT_NAZIV_PONUDJACA
        defaultPonudeShouldBeFound("nazivPonudjaca.equals=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the ponudeList where nazivPonudjaca equals to UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldNotBeFound("nazivPonudjaca.equals=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca not equals to DEFAULT_NAZIV_PONUDJACA
        defaultPonudeShouldNotBeFound("nazivPonudjaca.notEquals=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the ponudeList where nazivPonudjaca not equals to UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldBeFound("nazivPonudjaca.notEquals=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca in DEFAULT_NAZIV_PONUDJACA or UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldBeFound("nazivPonudjaca.in=" + DEFAULT_NAZIV_PONUDJACA + "," + UPDATED_NAZIV_PONUDJACA);

        // Get all the ponudeList where nazivPonudjaca equals to UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldNotBeFound("nazivPonudjaca.in=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca is not null
        defaultPonudeShouldBeFound("nazivPonudjaca.specified=true");

        // Get all the ponudeList where nazivPonudjaca is null
        defaultPonudeShouldNotBeFound("nazivPonudjaca.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca contains DEFAULT_NAZIV_PONUDJACA
        defaultPonudeShouldBeFound("nazivPonudjaca.contains=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the ponudeList where nazivPonudjaca contains UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldNotBeFound("nazivPonudjaca.contains=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNazivPonudjacaNotContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where nazivPonudjaca does not contain DEFAULT_NAZIV_PONUDJACA
        defaultPonudeShouldNotBeFound("nazivPonudjaca.doesNotContain=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the ponudeList where nazivPonudjaca does not contain UPDATED_NAZIV_PONUDJACA
        defaultPonudeShouldBeFound("nazivPonudjaca.doesNotContain=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca equals to DEFAULT_NAZI_PROIZVODJACA
        defaultPonudeShouldBeFound("naziProizvodjaca.equals=" + DEFAULT_NAZI_PROIZVODJACA);

        // Get all the ponudeList where naziProizvodjaca equals to UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldNotBeFound("naziProizvodjaca.equals=" + UPDATED_NAZI_PROIZVODJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca not equals to DEFAULT_NAZI_PROIZVODJACA
        defaultPonudeShouldNotBeFound("naziProizvodjaca.notEquals=" + DEFAULT_NAZI_PROIZVODJACA);

        // Get all the ponudeList where naziProizvodjaca not equals to UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldBeFound("naziProizvodjaca.notEquals=" + UPDATED_NAZI_PROIZVODJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca in DEFAULT_NAZI_PROIZVODJACA or UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldBeFound("naziProizvodjaca.in=" + DEFAULT_NAZI_PROIZVODJACA + "," + UPDATED_NAZI_PROIZVODJACA);

        // Get all the ponudeList where naziProizvodjaca equals to UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldNotBeFound("naziProizvodjaca.in=" + UPDATED_NAZI_PROIZVODJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca is not null
        defaultPonudeShouldBeFound("naziProizvodjaca.specified=true");

        // Get all the ponudeList where naziProizvodjaca is null
        defaultPonudeShouldNotBeFound("naziProizvodjaca.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca contains DEFAULT_NAZI_PROIZVODJACA
        defaultPonudeShouldBeFound("naziProizvodjaca.contains=" + DEFAULT_NAZI_PROIZVODJACA);

        // Get all the ponudeList where naziProizvodjaca contains UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldNotBeFound("naziProizvodjaca.contains=" + UPDATED_NAZI_PROIZVODJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByNaziProizvodjacaNotContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where naziProizvodjaca does not contain DEFAULT_NAZI_PROIZVODJACA
        defaultPonudeShouldNotBeFound("naziProizvodjaca.doesNotContain=" + DEFAULT_NAZI_PROIZVODJACA);

        // Get all the ponudeList where naziProizvodjaca does not contain UPDATED_NAZI_PROIZVODJACA
        defaultPonudeShouldBeFound("naziProizvodjaca.doesNotContain=" + UPDATED_NAZI_PROIZVODJACA);
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv equals to DEFAULT_ZASTCENI_NAZIV
        defaultPonudeShouldBeFound("zastceniNaziv.equals=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the ponudeList where zastceniNaziv equals to UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldNotBeFound("zastceniNaziv.equals=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv not equals to DEFAULT_ZASTCENI_NAZIV
        defaultPonudeShouldNotBeFound("zastceniNaziv.notEquals=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the ponudeList where zastceniNaziv not equals to UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldBeFound("zastceniNaziv.notEquals=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv in DEFAULT_ZASTCENI_NAZIV or UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldBeFound("zastceniNaziv.in=" + DEFAULT_ZASTCENI_NAZIV + "," + UPDATED_ZASTCENI_NAZIV);

        // Get all the ponudeList where zastceniNaziv equals to UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldNotBeFound("zastceniNaziv.in=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv is not null
        defaultPonudeShouldBeFound("zastceniNaziv.specified=true");

        // Get all the ponudeList where zastceniNaziv is null
        defaultPonudeShouldNotBeFound("zastceniNaziv.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv contains DEFAULT_ZASTCENI_NAZIV
        defaultPonudeShouldBeFound("zastceniNaziv.contains=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the ponudeList where zastceniNaziv contains UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldNotBeFound("zastceniNaziv.contains=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllPonudesByZastceniNazivNotContainsSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where zastceniNaziv does not contain DEFAULT_ZASTCENI_NAZIV
        defaultPonudeShouldNotBeFound("zastceniNaziv.doesNotContain=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the ponudeList where zastceniNaziv does not contain UPDATED_ZASTCENI_NAZIV
        defaultPonudeShouldBeFound("zastceniNaziv.doesNotContain=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost equals to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.equals=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.equals=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost not equals to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.notEquals=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost not equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.notEquals=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost in DEFAULT_PONUDJENA_VRIJEDNOST or UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.in=" + DEFAULT_PONUDJENA_VRIJEDNOST + "," + UPDATED_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.in=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost is not null
        defaultPonudeShouldBeFound("ponudjenaVrijednost.specified=true");

        // Get all the ponudeList where ponudjenaVrijednost is null
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost is greater than or equal to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.greaterThanOrEqual=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost is greater than or equal to UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.greaterThanOrEqual=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost is less than or equal to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.lessThanOrEqual=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost is less than or equal to SMALLER_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.lessThanOrEqual=" + SMALLER_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsLessThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost is less than DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.lessThan=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost is less than UPDATED_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.lessThan=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByPonudjenaVrijednostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where ponudjenaVrijednost is greater than DEFAULT_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldNotBeFound("ponudjenaVrijednost.greaterThan=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the ponudeList where ponudjenaVrijednost is greater than SMALLER_PONUDJENA_VRIJEDNOST
        defaultPonudeShouldBeFound("ponudjenaVrijednost.greaterThan=" + SMALLER_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke equals to DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.equals=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke equals to UPDATED_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.equals=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke not equals to DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.notEquals=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke not equals to UPDATED_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.notEquals=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsInShouldWork() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke in DEFAULT_ROK_ISPORUKE or UPDATED_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.in=" + DEFAULT_ROK_ISPORUKE + "," + UPDATED_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke equals to UPDATED_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.in=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke is not null
        defaultPonudeShouldBeFound("rokIsporuke.specified=true");

        // Get all the ponudeList where rokIsporuke is null
        defaultPonudeShouldNotBeFound("rokIsporuke.specified=false");
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke is greater than or equal to DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.greaterThanOrEqual=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke is greater than or equal to UPDATED_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.greaterThanOrEqual=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke is less than or equal to DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.lessThanOrEqual=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke is less than or equal to SMALLER_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.lessThanOrEqual=" + SMALLER_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsLessThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke is less than DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.lessThan=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke is less than UPDATED_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.lessThan=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllPonudesByRokIsporukeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        // Get all the ponudeList where rokIsporuke is greater than DEFAULT_ROK_ISPORUKE
        defaultPonudeShouldNotBeFound("rokIsporuke.greaterThan=" + DEFAULT_ROK_ISPORUKE);

        // Get all the ponudeList where rokIsporuke is greater than SMALLER_ROK_ISPORUKE
        defaultPonudeShouldBeFound("rokIsporuke.greaterThan=" + SMALLER_ROK_ISPORUKE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPonudeShouldBeFound(String filter) throws Exception {
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ponude.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].naziProizvodjaca").value(hasItem(DEFAULT_NAZI_PROIZVODJACA)))
            .andExpect(jsonPath("$.[*].zastceniNaziv").value(hasItem(DEFAULT_ZASTCENI_NAZIV)))
            .andExpect(jsonPath("$.[*].ponudjenaVrijednost").value(hasItem(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].rokIsporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)));

        // Check, that the count call also returns 1
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPonudeShouldNotBeFound(String filter) throws Exception {
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPonudeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingPonude() throws Exception {
        // Get the ponude
        restPonudeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPonude() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();

        // Update the ponude
        Ponude updatedPonude = ponudeRepository.findById(ponude.getId()).get();
        // Disconnect from session so that the updates on updatedPonude are not directly saved in db
        em.detach(updatedPonude);
        updatedPonude
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziProizvodjaca(UPDATED_NAZI_PROIZVODJACA)
            .zastceniNaziv(UPDATED_ZASTCENI_NAZIV)
            .ponudjenaVrijednost(UPDATED_PONUDJENA_VRIJEDNOST)
            .rokIsporuke(UPDATED_ROK_ISPORUKE);

        restPonudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPonude.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPonude))
            )
            .andExpect(status().isOk());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
        Ponude testPonude = ponudeList.get(ponudeList.size() - 1);
        assertThat(testPonude.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPonude.getSifraPonude()).isEqualTo(UPDATED_SIFRA_PONUDE);
        assertThat(testPonude.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testPonude.getNazivPonudjaca()).isEqualTo(UPDATED_NAZIV_PONUDJACA);
        assertThat(testPonude.getNaziProizvodjaca()).isEqualTo(UPDATED_NAZI_PROIZVODJACA);
        assertThat(testPonude.getZastceniNaziv()).isEqualTo(UPDATED_ZASTCENI_NAZIV);
        assertThat(testPonude.getPonudjenaVrijednost()).isEqualTo(UPDATED_PONUDJENA_VRIJEDNOST);
        assertThat(testPonude.getRokIsporuke()).isEqualTo(UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void putNonExistingPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ponude.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ponude))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ponude))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePonudeWithPatch() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();

        // Update the ponude using partial update
        Ponude partialUpdatedPonude = new Ponude();
        partialUpdatedPonude.setId(ponude.getId());

        partialUpdatedPonude
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .naziProizvodjaca(UPDATED_NAZI_PROIZVODJACA)
            .ponudjenaVrijednost(UPDATED_PONUDJENA_VRIJEDNOST);

        restPonudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPonude.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPonude))
            )
            .andExpect(status().isOk());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
        Ponude testPonude = ponudeList.get(ponudeList.size() - 1);
        assertThat(testPonude.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPonude.getSifraPonude()).isEqualTo(UPDATED_SIFRA_PONUDE);
        assertThat(testPonude.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testPonude.getNazivPonudjaca()).isEqualTo(DEFAULT_NAZIV_PONUDJACA);
        assertThat(testPonude.getNaziProizvodjaca()).isEqualTo(UPDATED_NAZI_PROIZVODJACA);
        assertThat(testPonude.getZastceniNaziv()).isEqualTo(DEFAULT_ZASTCENI_NAZIV);
        assertThat(testPonude.getPonudjenaVrijednost()).isEqualTo(UPDATED_PONUDJENA_VRIJEDNOST);
        assertThat(testPonude.getRokIsporuke()).isEqualTo(DEFAULT_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void fullUpdatePonudeWithPatch() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();

        // Update the ponude using partial update
        Ponude partialUpdatedPonude = new Ponude();
        partialUpdatedPonude.setId(ponude.getId());

        partialUpdatedPonude
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziProizvodjaca(UPDATED_NAZI_PROIZVODJACA)
            .zastceniNaziv(UPDATED_ZASTCENI_NAZIV)
            .ponudjenaVrijednost(UPDATED_PONUDJENA_VRIJEDNOST)
            .rokIsporuke(UPDATED_ROK_ISPORUKE);

        restPonudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPonude.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPonude))
            )
            .andExpect(status().isOk());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
        Ponude testPonude = ponudeList.get(ponudeList.size() - 1);
        assertThat(testPonude.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPonude.getSifraPonude()).isEqualTo(UPDATED_SIFRA_PONUDE);
        assertThat(testPonude.getBrojPartije()).isEqualTo(UPDATED_BROJ_PARTIJE);
        assertThat(testPonude.getNazivPonudjaca()).isEqualTo(UPDATED_NAZIV_PONUDJACA);
        assertThat(testPonude.getNaziProizvodjaca()).isEqualTo(UPDATED_NAZI_PROIZVODJACA);
        assertThat(testPonude.getZastceniNaziv()).isEqualTo(UPDATED_ZASTCENI_NAZIV);
        assertThat(testPonude.getPonudjenaVrijednost()).isEqualTo(UPDATED_PONUDJENA_VRIJEDNOST);
        assertThat(testPonude.getRokIsporuke()).isEqualTo(UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void patchNonExistingPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ponude.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ponude))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ponude))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPonude() throws Exception {
        int databaseSizeBeforeUpdate = ponudeRepository.findAll().size();
        ponude.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPonudeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ponude)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ponude in the database
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePonude() throws Exception {
        // Initialize the database
        ponudeRepository.saveAndFlush(ponude);

        int databaseSizeBeforeDelete = ponudeRepository.findAll().size();

        // Delete the ponude
        restPonudeMockMvc
            .perform(delete(ENTITY_API_URL_ID, ponude.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ponude> ponudeList = ponudeRepository.findAll();
        assertThat(ponudeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
