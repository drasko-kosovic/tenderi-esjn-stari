package tenderi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ponude.
 */
@Entity
@Table(name = "ponude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ponude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sifra_postupka", nullable = false)
    private Integer sifraPostupka;

    @NotNull
    @Column(name = "sifra_ponude", nullable = false)
    private Integer sifraPonude;

    @NotNull
    @Column(name = "broj_partije", nullable = false)
    private Integer brojPartije;

    @NotNull
    @Column(name = "naziv_ponudjaca", nullable = false)
    private String nazivPonudjaca;

    @NotNull
    @Column(name = "naziv_proizvodjaca", nullable = false)
    private String nazivProizvodjaca;

    @NotNull
    @Column(name = "zastceni_naziv", nullable = true)
    private String zastceniNaziv;

    @NotNull
    @Column(name = "ponudjena_vrijednost", nullable = false)
    private Double ponudjenaVrijednost;

    @NotNull
    @Column(name = "ponudjena_jedinicna_cijena", nullable = false)
    private Double ponudjenaJedinicnaCijena;

    @Column(name = "rok_isporuke")
    private Integer rokIsporuke;

    @Column(name = "broj_ugovora")
    private String brojUgovora;

    @Column(name = "datum_ugovora")
    private LocalDate datumUgovora;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSifraPostupka() {
        return sifraPostupka;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getSifraPonude() {
        return sifraPonude;
    }

    public void setSifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public Integer getBrojPartije() {
        return brojPartije;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getNazivPonudjaca() {
        return nazivPonudjaca;
    }

    public void setNazivPonudjaca(String nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
    }

    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public String getZastceniNaziv() {
        return zastceniNaziv;
    }

    public void setZastceniNaziv(String zastceniNaziv) {
        this.zastceniNaziv = zastceniNaziv;
    }

    public Double getPonudjenaVrijednost() {
        return ponudjenaVrijednost;
    }

    public void setPonudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public Double getPonudjenaJedinicnaCijena() {
        return ponudjenaJedinicnaCijena;
    }

    public void setPonudjenaJedinicnaCijena(Double ponudjenaJedinicnaCijena) {
        this.ponudjenaJedinicnaCijena = ponudjenaJedinicnaCijena;
    }

    public Integer getRokIsporuke() {
        return rokIsporuke;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public LocalDate getDatumUgovora() {
        return datumUgovora;
    }

    public void setDatumUgovora(LocalDate datumUgovora) {
        this.datumUgovora = datumUgovora;
    }
}
