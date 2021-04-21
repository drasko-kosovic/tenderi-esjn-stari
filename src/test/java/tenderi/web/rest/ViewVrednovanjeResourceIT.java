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
import tenderi.domain.ViewVrednovanje;
import tenderi.repository.ViewVrednovanjeRepository;
import tenderi.service.criteria.ViewVrednovanjeCriteria;

/**
 * Integration tests for the {@link ViewVrednovanjeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewVrednovanjeResourceIT {

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

    private static final Double DEFAULT_PROCIJENJENA_VRIJEDNOST = 1D;
    private static final Double UPDATED_PROCIJENJENA_VRIJEDNOST = 2D;
    private static final Double SMALLER_PROCIJENJENA_VRIJEDNOST = 1D - 1D;

    private static final Double DEFAULT_PONUDJENA_VRIJEDNOST = 1D;
    private static final Double UPDATED_PONUDJENA_VRIJEDNOST = 2D;
    private static final Double SMALLER_PONUDJENA_VRIJEDNOST = 1D - 1D;

    private static final Integer DEFAULT_KOLICINA = 1;
    private static final Integer UPDATED_KOLICINA = 2;
    private static final Integer SMALLER_KOLICINA = 1 - 1;

    private static final String DEFAULT_ATC = "AAAAAAAAAA";
    private static final String UPDATED_ATC = "BBBBBBBBBB";

    private static final String DEFAULT_INN = "AAAAAAAAAA";
    private static final String UPDATED_INN = "BBBBBBBBBB";

    private static final String DEFAULT_ZASTCENI_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_ZASTCENI_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_FARMACEUTSKI_OBLIK_LIJEKA = "BBBBBBBBBB";

    private static final String DEFAULT_JACINA_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_JACINA_LIJEKA = "BBBBBBBBBB";

    private static final String DEFAULT_PAKOVANJE = "AAAAAAAAAA";
    private static final String UPDATED_PAKOVANJE = "BBBBBBBBBB";

    private static final Double DEFAULT_BOD_ISPORUKA = 1D;
    private static final Double UPDATED_BOD_ISPORUKA = 2D;
    private static final Double SMALLER_BOD_ISPORUKA = 1D - 1D;

    private static final Integer DEFAULT_ROK_ISPORUKE = 1;
    private static final Integer UPDATED_ROK_ISPORUKE = 2;
    private static final Integer SMALLER_ROK_ISPORUKE = 1 - 1;

    private static final Double DEFAULT_BOD_CIJENA = 1D;
    private static final Double UPDATED_BOD_CIJENA = 2D;
    private static final Double SMALLER_BOD_CIJENA = 1D - 1D;

    private static final Double DEFAULT_BOD_UKUPNO = 1D;
    private static final Double UPDATED_BOD_UKUPNO = 2D;
    private static final Double SMALLER_BOD_UKUPNO = 1D - 1D;

    private static final String ENTITY_API_URL = "/api/view-vrednovanjes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewVrednovanjeRepository viewVrednovanjeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewVrednovanjeMockMvc;

    private ViewVrednovanje viewVrednovanje;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewVrednovanje createEntity(EntityManager em) {
        ViewVrednovanje viewVrednovanje = new ViewVrednovanje()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .sifraPonude(DEFAULT_SIFRA_PONUDE)
            .brojPartije(DEFAULT_BROJ_PARTIJE)
            .nazivPonudjaca(DEFAULT_NAZIV_PONUDJACA)
            .procijenjenaVrijednost(DEFAULT_PROCIJENJENA_VRIJEDNOST)
            .ponudjenaVrijednost(DEFAULT_PONUDJENA_VRIJEDNOST)
            .kolicina(DEFAULT_KOLICINA)
            .atc(DEFAULT_ATC)
            .inn(DEFAULT_INN)
            .zastceniNaziv(DEFAULT_ZASTCENI_NAZIV)
            .farmaceutskiOblikLijeka(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(DEFAULT_JACINA_LIJEKA)
            .pakovanje(DEFAULT_PAKOVANJE)
            .bodIsporuka(DEFAULT_BOD_ISPORUKA)
            .rokIsporuke(DEFAULT_ROK_ISPORUKE)
            .bodCijena(DEFAULT_BOD_CIJENA)
            .bodUkupno(DEFAULT_BOD_UKUPNO);
        return viewVrednovanje;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewVrednovanje createUpdatedEntity(EntityManager em) {
        ViewVrednovanje viewVrednovanje = new ViewVrednovanje()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .brojPartije(UPDATED_BROJ_PARTIJE)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA)
            .procijenjenaVrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .ponudjenaVrijednost(UPDATED_PONUDJENA_VRIJEDNOST)
            .kolicina(UPDATED_KOLICINA)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .zastceniNaziv(UPDATED_ZASTCENI_NAZIV)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .bodIsporuka(UPDATED_BOD_ISPORUKA)
            .rokIsporuke(UPDATED_ROK_ISPORUKE)
            .bodCijena(UPDATED_BOD_CIJENA)
            .bodUkupno(UPDATED_BOD_UKUPNO);
        return viewVrednovanje;
    }

    @BeforeEach
    public void initTest() {
        viewVrednovanje = createEntity(em);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjes() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewVrednovanje.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].procijenjenaVrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].ponudjenaVrijednost").value(hasItem(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].kolicina").value(hasItem(DEFAULT_KOLICINA)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].zastceniNaziv").value(hasItem(DEFAULT_ZASTCENI_NAZIV)))
            .andExpect(jsonPath("$.[*].farmaceutskiOblikLijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacinaLijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].bodIsporuka").value(hasItem(DEFAULT_BOD_ISPORUKA.doubleValue())))
            .andExpect(jsonPath("$.[*].rokIsporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)))
            .andExpect(jsonPath("$.[*].bodCijena").value(hasItem(DEFAULT_BOD_CIJENA.doubleValue())))
            .andExpect(jsonPath("$.[*].bodUkupno").value(hasItem(DEFAULT_BOD_UKUPNO.doubleValue())));
    }

    @Test
    @Transactional
    void getViewVrednovanje() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get the viewVrednovanje
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL_ID, viewVrednovanje.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewVrednovanje.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifraPonude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.brojPartije").value(DEFAULT_BROJ_PARTIJE))
            .andExpect(jsonPath("$.nazivPonudjaca").value(DEFAULT_NAZIV_PONUDJACA))
            .andExpect(jsonPath("$.procijenjenaVrijednost").value(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.ponudjenaVrijednost").value(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.kolicina").value(DEFAULT_KOLICINA))
            .andExpect(jsonPath("$.atc").value(DEFAULT_ATC))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN))
            .andExpect(jsonPath("$.zastceniNaziv").value(DEFAULT_ZASTCENI_NAZIV))
            .andExpect(jsonPath("$.farmaceutskiOblikLijeka").value(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA))
            .andExpect(jsonPath("$.jacinaLijeka").value(DEFAULT_JACINA_LIJEKA))
            .andExpect(jsonPath("$.pakovanje").value(DEFAULT_PAKOVANJE))
            .andExpect(jsonPath("$.bodIsporuka").value(DEFAULT_BOD_ISPORUKA.doubleValue()))
            .andExpect(jsonPath("$.rokIsporuke").value(DEFAULT_ROK_ISPORUKE))
            .andExpect(jsonPath("$.bodCijena").value(DEFAULT_BOD_CIJENA.doubleValue()))
            .andExpect(jsonPath("$.bodUkupno").value(DEFAULT_BOD_UKUPNO.doubleValue()));
    }

    @Test
    @Transactional
    void getViewVrednovanjesByIdFiltering() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        Long id = viewVrednovanje.getId();

        defaultViewVrednovanjeShouldBeFound("id.equals=" + id);
        defaultViewVrednovanjeShouldNotBeFound("id.notEquals=" + id);

        defaultViewVrednovanjeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultViewVrednovanjeShouldNotBeFound("id.greaterThan=" + id);

        defaultViewVrednovanjeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultViewVrednovanjeShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka equals to DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.equals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.equals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka not equals to DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.notEquals=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka not equals to UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.notEquals=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka in DEFAULT_SIFRA_POSTUPKA or UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.in=" + DEFAULT_SIFRA_POSTUPKA + "," + UPDATED_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka equals to UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.in=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka is not null
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.specified=true");

        // Get all the viewVrednovanjeList where sifraPostupka is null
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka is greater than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.greaterThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka is greater than or equal to UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.greaterThanOrEqual=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka is less than or equal to DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.lessThanOrEqual=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka is less than or equal to SMALLER_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.lessThanOrEqual=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka is less than DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.lessThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka is less than UPDATED_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.lessThan=" + UPDATED_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPostupkaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPostupka is greater than DEFAULT_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldNotBeFound("sifraPostupka.greaterThan=" + DEFAULT_SIFRA_POSTUPKA);

        // Get all the viewVrednovanjeList where sifraPostupka is greater than SMALLER_SIFRA_POSTUPKA
        defaultViewVrednovanjeShouldBeFound("sifraPostupka.greaterThan=" + SMALLER_SIFRA_POSTUPKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude equals to DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.equals=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude equals to UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.equals=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude not equals to DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.notEquals=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude not equals to UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.notEquals=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude in DEFAULT_SIFRA_PONUDE or UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.in=" + DEFAULT_SIFRA_PONUDE + "," + UPDATED_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude equals to UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.in=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude is not null
        defaultViewVrednovanjeShouldBeFound("sifraPonude.specified=true");

        // Get all the viewVrednovanjeList where sifraPonude is null
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude is greater than or equal to DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.greaterThanOrEqual=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude is greater than or equal to UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.greaterThanOrEqual=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude is less than or equal to DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.lessThanOrEqual=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude is less than or equal to SMALLER_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.lessThanOrEqual=" + SMALLER_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude is less than DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.lessThan=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude is less than UPDATED_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.lessThan=" + UPDATED_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesBySifraPonudeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where sifraPonude is greater than DEFAULT_SIFRA_PONUDE
        defaultViewVrednovanjeShouldNotBeFound("sifraPonude.greaterThan=" + DEFAULT_SIFRA_PONUDE);

        // Get all the viewVrednovanjeList where sifraPonude is greater than SMALLER_SIFRA_PONUDE
        defaultViewVrednovanjeShouldBeFound("sifraPonude.greaterThan=" + SMALLER_SIFRA_PONUDE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije equals to DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.equals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.equals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije not equals to DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.notEquals=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije not equals to UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.notEquals=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije in DEFAULT_BROJ_PARTIJE or UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.in=" + DEFAULT_BROJ_PARTIJE + "," + UPDATED_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije equals to UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.in=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije is not null
        defaultViewVrednovanjeShouldBeFound("brojPartije.specified=true");

        // Get all the viewVrednovanjeList where brojPartije is null
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije is greater than or equal to DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.greaterThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije is greater than or equal to UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.greaterThanOrEqual=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije is less than or equal to DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.lessThanOrEqual=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije is less than or equal to SMALLER_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.lessThanOrEqual=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije is less than DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.lessThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije is less than UPDATED_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.lessThan=" + UPDATED_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBrojPartijeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where brojPartije is greater than DEFAULT_BROJ_PARTIJE
        defaultViewVrednovanjeShouldNotBeFound("brojPartije.greaterThan=" + DEFAULT_BROJ_PARTIJE);

        // Get all the viewVrednovanjeList where brojPartije is greater than SMALLER_BROJ_PARTIJE
        defaultViewVrednovanjeShouldBeFound("brojPartije.greaterThan=" + SMALLER_BROJ_PARTIJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca equals to DEFAULT_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.equals=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the viewVrednovanjeList where nazivPonudjaca equals to UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.equals=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca not equals to DEFAULT_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.notEquals=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the viewVrednovanjeList where nazivPonudjaca not equals to UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.notEquals=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca in DEFAULT_NAZIV_PONUDJACA or UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.in=" + DEFAULT_NAZIV_PONUDJACA + "," + UPDATED_NAZIV_PONUDJACA);

        // Get all the viewVrednovanjeList where nazivPonudjaca equals to UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.in=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca is not null
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.specified=true");

        // Get all the viewVrednovanjeList where nazivPonudjaca is null
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca contains DEFAULT_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.contains=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the viewVrednovanjeList where nazivPonudjaca contains UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.contains=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByNazivPonudjacaNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where nazivPonudjaca does not contain DEFAULT_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldNotBeFound("nazivPonudjaca.doesNotContain=" + DEFAULT_NAZIV_PONUDJACA);

        // Get all the viewVrednovanjeList where nazivPonudjaca does not contain UPDATED_NAZIV_PONUDJACA
        defaultViewVrednovanjeShouldBeFound("nazivPonudjaca.doesNotContain=" + UPDATED_NAZIV_PONUDJACA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost equals to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.equals=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.equals=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost not equals to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.notEquals=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost not equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.notEquals=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost in DEFAULT_PROCIJENJENA_VRIJEDNOST or UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound(
            "procijenjenaVrijednost.in=" + DEFAULT_PROCIJENJENA_VRIJEDNOST + "," + UPDATED_PROCIJENJENA_VRIJEDNOST
        );

        // Get all the viewVrednovanjeList where procijenjenaVrijednost equals to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.in=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is not null
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.specified=true");

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is null
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is greater than or equal to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.greaterThanOrEqual=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is greater than or equal to UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.greaterThanOrEqual=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is less than or equal to DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.lessThanOrEqual=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is less than or equal to SMALLER_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.lessThanOrEqual=" + SMALLER_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is less than DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.lessThan=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is less than UPDATED_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.lessThan=" + UPDATED_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByProcijenjenaVrijednostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is greater than DEFAULT_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("procijenjenaVrijednost.greaterThan=" + DEFAULT_PROCIJENJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where procijenjenaVrijednost is greater than SMALLER_PROCIJENJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("procijenjenaVrijednost.greaterThan=" + SMALLER_PROCIJENJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost equals to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.equals=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.equals=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost not equals to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.notEquals=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost not equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.notEquals=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost in DEFAULT_PONUDJENA_VRIJEDNOST or UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.in=" + DEFAULT_PONUDJENA_VRIJEDNOST + "," + UPDATED_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost equals to UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.in=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is not null
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.specified=true");

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is null
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is greater than or equal to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.greaterThanOrEqual=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is greater than or equal to UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.greaterThanOrEqual=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is less than or equal to DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.lessThanOrEqual=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is less than or equal to SMALLER_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.lessThanOrEqual=" + SMALLER_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is less than DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.lessThan=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is less than UPDATED_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.lessThan=" + UPDATED_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPonudjenaVrijednostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is greater than DEFAULT_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldNotBeFound("ponudjenaVrijednost.greaterThan=" + DEFAULT_PONUDJENA_VRIJEDNOST);

        // Get all the viewVrednovanjeList where ponudjenaVrijednost is greater than SMALLER_PONUDJENA_VRIJEDNOST
        defaultViewVrednovanjeShouldBeFound("ponudjenaVrijednost.greaterThan=" + SMALLER_PONUDJENA_VRIJEDNOST);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina equals to DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.equals=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina equals to UPDATED_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.equals=" + UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina not equals to DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.notEquals=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina not equals to UPDATED_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.notEquals=" + UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina in DEFAULT_KOLICINA or UPDATED_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.in=" + DEFAULT_KOLICINA + "," + UPDATED_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina equals to UPDATED_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.in=" + UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina is not null
        defaultViewVrednovanjeShouldBeFound("kolicina.specified=true");

        // Get all the viewVrednovanjeList where kolicina is null
        defaultViewVrednovanjeShouldNotBeFound("kolicina.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina is greater than or equal to DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.greaterThanOrEqual=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina is greater than or equal to UPDATED_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.greaterThanOrEqual=" + UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina is less than or equal to DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.lessThanOrEqual=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina is less than or equal to SMALLER_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.lessThanOrEqual=" + SMALLER_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina is less than DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.lessThan=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina is less than UPDATED_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.lessThan=" + UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByKolicinaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where kolicina is greater than DEFAULT_KOLICINA
        defaultViewVrednovanjeShouldNotBeFound("kolicina.greaterThan=" + DEFAULT_KOLICINA);

        // Get all the viewVrednovanjeList where kolicina is greater than SMALLER_KOLICINA
        defaultViewVrednovanjeShouldBeFound("kolicina.greaterThan=" + SMALLER_KOLICINA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc equals to DEFAULT_ATC
        defaultViewVrednovanjeShouldBeFound("atc.equals=" + DEFAULT_ATC);

        // Get all the viewVrednovanjeList where atc equals to UPDATED_ATC
        defaultViewVrednovanjeShouldNotBeFound("atc.equals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc not equals to DEFAULT_ATC
        defaultViewVrednovanjeShouldNotBeFound("atc.notEquals=" + DEFAULT_ATC);

        // Get all the viewVrednovanjeList where atc not equals to UPDATED_ATC
        defaultViewVrednovanjeShouldBeFound("atc.notEquals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc in DEFAULT_ATC or UPDATED_ATC
        defaultViewVrednovanjeShouldBeFound("atc.in=" + DEFAULT_ATC + "," + UPDATED_ATC);

        // Get all the viewVrednovanjeList where atc equals to UPDATED_ATC
        defaultViewVrednovanjeShouldNotBeFound("atc.in=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc is not null
        defaultViewVrednovanjeShouldBeFound("atc.specified=true");

        // Get all the viewVrednovanjeList where atc is null
        defaultViewVrednovanjeShouldNotBeFound("atc.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc contains DEFAULT_ATC
        defaultViewVrednovanjeShouldBeFound("atc.contains=" + DEFAULT_ATC);

        // Get all the viewVrednovanjeList where atc contains UPDATED_ATC
        defaultViewVrednovanjeShouldNotBeFound("atc.contains=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByAtcNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where atc does not contain DEFAULT_ATC
        defaultViewVrednovanjeShouldNotBeFound("atc.doesNotContain=" + DEFAULT_ATC);

        // Get all the viewVrednovanjeList where atc does not contain UPDATED_ATC
        defaultViewVrednovanjeShouldBeFound("atc.doesNotContain=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn equals to DEFAULT_INN
        defaultViewVrednovanjeShouldBeFound("inn.equals=" + DEFAULT_INN);

        // Get all the viewVrednovanjeList where inn equals to UPDATED_INN
        defaultViewVrednovanjeShouldNotBeFound("inn.equals=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn not equals to DEFAULT_INN
        defaultViewVrednovanjeShouldNotBeFound("inn.notEquals=" + DEFAULT_INN);

        // Get all the viewVrednovanjeList where inn not equals to UPDATED_INN
        defaultViewVrednovanjeShouldBeFound("inn.notEquals=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn in DEFAULT_INN or UPDATED_INN
        defaultViewVrednovanjeShouldBeFound("inn.in=" + DEFAULT_INN + "," + UPDATED_INN);

        // Get all the viewVrednovanjeList where inn equals to UPDATED_INN
        defaultViewVrednovanjeShouldNotBeFound("inn.in=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn is not null
        defaultViewVrednovanjeShouldBeFound("inn.specified=true");

        // Get all the viewVrednovanjeList where inn is null
        defaultViewVrednovanjeShouldNotBeFound("inn.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn contains DEFAULT_INN
        defaultViewVrednovanjeShouldBeFound("inn.contains=" + DEFAULT_INN);

        // Get all the viewVrednovanjeList where inn contains UPDATED_INN
        defaultViewVrednovanjeShouldNotBeFound("inn.contains=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByInnNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where inn does not contain DEFAULT_INN
        defaultViewVrednovanjeShouldNotBeFound("inn.doesNotContain=" + DEFAULT_INN);

        // Get all the viewVrednovanjeList where inn does not contain UPDATED_INN
        defaultViewVrednovanjeShouldBeFound("inn.doesNotContain=" + UPDATED_INN);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv equals to DEFAULT_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.equals=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the viewVrednovanjeList where zastceniNaziv equals to UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.equals=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv not equals to DEFAULT_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.notEquals=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the viewVrednovanjeList where zastceniNaziv not equals to UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.notEquals=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv in DEFAULT_ZASTCENI_NAZIV or UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.in=" + DEFAULT_ZASTCENI_NAZIV + "," + UPDATED_ZASTCENI_NAZIV);

        // Get all the viewVrednovanjeList where zastceniNaziv equals to UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.in=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv is not null
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.specified=true");

        // Get all the viewVrednovanjeList where zastceniNaziv is null
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv contains DEFAULT_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.contains=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the viewVrednovanjeList where zastceniNaziv contains UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.contains=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByZastceniNazivNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where zastceniNaziv does not contain DEFAULT_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldNotBeFound("zastceniNaziv.doesNotContain=" + DEFAULT_ZASTCENI_NAZIV);

        // Get all the viewVrednovanjeList where zastceniNaziv does not contain UPDATED_ZASTCENI_NAZIV
        defaultViewVrednovanjeShouldBeFound("zastceniNaziv.doesNotContain=" + UPDATED_ZASTCENI_NAZIV);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka equals to DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldBeFound("farmaceutskiOblikLijeka.equals=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.equals=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka not equals to DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.notEquals=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka not equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldBeFound("farmaceutskiOblikLijeka.notEquals=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka in DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA or UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldBeFound(
            "farmaceutskiOblikLijeka.in=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA + "," + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        );

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka equals to UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.in=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka is not null
        defaultViewVrednovanjeShouldBeFound("farmaceutskiOblikLijeka.specified=true");

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka is null
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka contains DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldBeFound("farmaceutskiOblikLijeka.contains=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka contains UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.contains=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByFarmaceutskiOblikLijekaNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka does not contain DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("farmaceutskiOblikLijeka.doesNotContain=" + DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);

        // Get all the viewVrednovanjeList where farmaceutskiOblikLijeka does not contain UPDATED_FARMACEUTSKI_OBLIK_LIJEKA
        defaultViewVrednovanjeShouldBeFound("farmaceutskiOblikLijeka.doesNotContain=" + UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka equals to DEFAULT_JACINA_LIJEKA
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.equals=" + DEFAULT_JACINA_LIJEKA);

        // Get all the viewVrednovanjeList where jacinaLijeka equals to UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.equals=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka not equals to DEFAULT_JACINA_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.notEquals=" + DEFAULT_JACINA_LIJEKA);

        // Get all the viewVrednovanjeList where jacinaLijeka not equals to UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.notEquals=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka in DEFAULT_JACINA_LIJEKA or UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.in=" + DEFAULT_JACINA_LIJEKA + "," + UPDATED_JACINA_LIJEKA);

        // Get all the viewVrednovanjeList where jacinaLijeka equals to UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.in=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka is not null
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.specified=true");

        // Get all the viewVrednovanjeList where jacinaLijeka is null
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka contains DEFAULT_JACINA_LIJEKA
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.contains=" + DEFAULT_JACINA_LIJEKA);

        // Get all the viewVrednovanjeList where jacinaLijeka contains UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.contains=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByJacinaLijekaNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where jacinaLijeka does not contain DEFAULT_JACINA_LIJEKA
        defaultViewVrednovanjeShouldNotBeFound("jacinaLijeka.doesNotContain=" + DEFAULT_JACINA_LIJEKA);

        // Get all the viewVrednovanjeList where jacinaLijeka does not contain UPDATED_JACINA_LIJEKA
        defaultViewVrednovanjeShouldBeFound("jacinaLijeka.doesNotContain=" + UPDATED_JACINA_LIJEKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje equals to DEFAULT_PAKOVANJE
        defaultViewVrednovanjeShouldBeFound("pakovanje.equals=" + DEFAULT_PAKOVANJE);

        // Get all the viewVrednovanjeList where pakovanje equals to UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.equals=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje not equals to DEFAULT_PAKOVANJE
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.notEquals=" + DEFAULT_PAKOVANJE);

        // Get all the viewVrednovanjeList where pakovanje not equals to UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldBeFound("pakovanje.notEquals=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje in DEFAULT_PAKOVANJE or UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldBeFound("pakovanje.in=" + DEFAULT_PAKOVANJE + "," + UPDATED_PAKOVANJE);

        // Get all the viewVrednovanjeList where pakovanje equals to UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.in=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje is not null
        defaultViewVrednovanjeShouldBeFound("pakovanje.specified=true");

        // Get all the viewVrednovanjeList where pakovanje is null
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje contains DEFAULT_PAKOVANJE
        defaultViewVrednovanjeShouldBeFound("pakovanje.contains=" + DEFAULT_PAKOVANJE);

        // Get all the viewVrednovanjeList where pakovanje contains UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.contains=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByPakovanjeNotContainsSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where pakovanje does not contain DEFAULT_PAKOVANJE
        defaultViewVrednovanjeShouldNotBeFound("pakovanje.doesNotContain=" + DEFAULT_PAKOVANJE);

        // Get all the viewVrednovanjeList where pakovanje does not contain UPDATED_PAKOVANJE
        defaultViewVrednovanjeShouldBeFound("pakovanje.doesNotContain=" + UPDATED_PAKOVANJE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka equals to DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.equals=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka equals to UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.equals=" + UPDATED_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka not equals to DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.notEquals=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka not equals to UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.notEquals=" + UPDATED_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka in DEFAULT_BOD_ISPORUKA or UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.in=" + DEFAULT_BOD_ISPORUKA + "," + UPDATED_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka equals to UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.in=" + UPDATED_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka is not null
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.specified=true");

        // Get all the viewVrednovanjeList where bodIsporuka is null
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka is greater than or equal to DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.greaterThanOrEqual=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka is greater than or equal to UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.greaterThanOrEqual=" + UPDATED_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka is less than or equal to DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.lessThanOrEqual=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka is less than or equal to SMALLER_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.lessThanOrEqual=" + SMALLER_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka is less than DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.lessThan=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka is less than UPDATED_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.lessThan=" + UPDATED_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodIsporukaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodIsporuka is greater than DEFAULT_BOD_ISPORUKA
        defaultViewVrednovanjeShouldNotBeFound("bodIsporuka.greaterThan=" + DEFAULT_BOD_ISPORUKA);

        // Get all the viewVrednovanjeList where bodIsporuka is greater than SMALLER_BOD_ISPORUKA
        defaultViewVrednovanjeShouldBeFound("bodIsporuka.greaterThan=" + SMALLER_BOD_ISPORUKA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke equals to DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.equals=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke equals to UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.equals=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke not equals to DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.notEquals=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke not equals to UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.notEquals=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke in DEFAULT_ROK_ISPORUKE or UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.in=" + DEFAULT_ROK_ISPORUKE + "," + UPDATED_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke equals to UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.in=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke is not null
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.specified=true");

        // Get all the viewVrednovanjeList where rokIsporuke is null
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke is greater than or equal to DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.greaterThanOrEqual=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke is greater than or equal to UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.greaterThanOrEqual=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke is less than or equal to DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.lessThanOrEqual=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke is less than or equal to SMALLER_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.lessThanOrEqual=" + SMALLER_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke is less than DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.lessThan=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke is less than UPDATED_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.lessThan=" + UPDATED_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByRokIsporukeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where rokIsporuke is greater than DEFAULT_ROK_ISPORUKE
        defaultViewVrednovanjeShouldNotBeFound("rokIsporuke.greaterThan=" + DEFAULT_ROK_ISPORUKE);

        // Get all the viewVrednovanjeList where rokIsporuke is greater than SMALLER_ROK_ISPORUKE
        defaultViewVrednovanjeShouldBeFound("rokIsporuke.greaterThan=" + SMALLER_ROK_ISPORUKE);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena equals to DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.equals=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena equals to UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.equals=" + UPDATED_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena not equals to DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.notEquals=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena not equals to UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.notEquals=" + UPDATED_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena in DEFAULT_BOD_CIJENA or UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.in=" + DEFAULT_BOD_CIJENA + "," + UPDATED_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena equals to UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.in=" + UPDATED_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena is not null
        defaultViewVrednovanjeShouldBeFound("bodCijena.specified=true");

        // Get all the viewVrednovanjeList where bodCijena is null
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena is greater than or equal to DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.greaterThanOrEqual=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena is greater than or equal to UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.greaterThanOrEqual=" + UPDATED_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena is less than or equal to DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.lessThanOrEqual=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena is less than or equal to SMALLER_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.lessThanOrEqual=" + SMALLER_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena is less than DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.lessThan=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena is less than UPDATED_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.lessThan=" + UPDATED_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodCijenaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodCijena is greater than DEFAULT_BOD_CIJENA
        defaultViewVrednovanjeShouldNotBeFound("bodCijena.greaterThan=" + DEFAULT_BOD_CIJENA);

        // Get all the viewVrednovanjeList where bodCijena is greater than SMALLER_BOD_CIJENA
        defaultViewVrednovanjeShouldBeFound("bodCijena.greaterThan=" + SMALLER_BOD_CIJENA);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno equals to DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.equals=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno equals to UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.equals=" + UPDATED_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno not equals to DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.notEquals=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno not equals to UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.notEquals=" + UPDATED_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsInShouldWork() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno in DEFAULT_BOD_UKUPNO or UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.in=" + DEFAULT_BOD_UKUPNO + "," + UPDATED_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno equals to UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.in=" + UPDATED_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno is not null
        defaultViewVrednovanjeShouldBeFound("bodUkupno.specified=true");

        // Get all the viewVrednovanjeList where bodUkupno is null
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.specified=false");
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno is greater than or equal to DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.greaterThanOrEqual=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno is greater than or equal to UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.greaterThanOrEqual=" + UPDATED_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno is less than or equal to DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.lessThanOrEqual=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno is less than or equal to SMALLER_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.lessThanOrEqual=" + SMALLER_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsLessThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno is less than DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.lessThan=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno is less than UPDATED_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.lessThan=" + UPDATED_BOD_UKUPNO);
    }

    @Test
    @Transactional
    void getAllViewVrednovanjesByBodUkupnoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        viewVrednovanjeRepository.saveAndFlush(viewVrednovanje);

        // Get all the viewVrednovanjeList where bodUkupno is greater than DEFAULT_BOD_UKUPNO
        defaultViewVrednovanjeShouldNotBeFound("bodUkupno.greaterThan=" + DEFAULT_BOD_UKUPNO);

        // Get all the viewVrednovanjeList where bodUkupno is greater than SMALLER_BOD_UKUPNO
        defaultViewVrednovanjeShouldBeFound("bodUkupno.greaterThan=" + SMALLER_BOD_UKUPNO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultViewVrednovanjeShouldBeFound(String filter) throws Exception {
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewVrednovanje.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].brojPartije").value(hasItem(DEFAULT_BROJ_PARTIJE)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].procijenjenaVrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].ponudjenaVrijednost").value(hasItem(DEFAULT_PONUDJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].kolicina").value(hasItem(DEFAULT_KOLICINA)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].zastceniNaziv").value(hasItem(DEFAULT_ZASTCENI_NAZIV)))
            .andExpect(jsonPath("$.[*].farmaceutskiOblikLijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacinaLijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].bodIsporuka").value(hasItem(DEFAULT_BOD_ISPORUKA.doubleValue())))
            .andExpect(jsonPath("$.[*].rokIsporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)))
            .andExpect(jsonPath("$.[*].bodCijena").value(hasItem(DEFAULT_BOD_CIJENA.doubleValue())))
            .andExpect(jsonPath("$.[*].bodUkupno").value(hasItem(DEFAULT_BOD_UKUPNO.doubleValue())));

        // Check, that the count call also returns 1
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultViewVrednovanjeShouldNotBeFound(String filter) throws Exception {
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restViewVrednovanjeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingViewVrednovanje() throws Exception {
        // Get the viewVrednovanje
        restViewVrednovanjeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
