package tenderi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Prvorangirani.
 */
@Entity
@Table(name = "view_prvorangirani")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prvorangirani implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_postupka")
    private Integer sifraPostupka;

    @Column(name = "sifra_ponude")
    private Integer sifraPonude;

    @Column(name = "broj_partije")
    private Integer brojPartije;

    @Column(name = "atc")
    private String atc;

    @Column(name = "inn")
    private String inn;

    @Column(name = "zastceni_naziv")
    private String zastceniNaziv;

    @Column(name = "farmaceutski_oblik_lijeka")
    private String farmaceutskiOblikLijeka;

    @Column(name = "jacina_lijeka")
    private String jacinaLijeka;

    @Column(name = "pakovanje")
    private String pakovanje;

    @Column(name = "trazena_kolicina")
    private Integer trazenaKolicina;

    @Column(name = "trazena_jedinicna_cijena")
    private Double trazenaJedinicnaCijena;

    @Column(name = "procijenjena_vrijednost")
    private Double procijenjenaVrijednost;

    @Column(name = "ponudjena_jedinicna_cijena")
    private Double ponudjenaJedinicnaCijena;

    @Column(name = "ponudjena_vrijednost")
    private Double ponudjenaVrijednost;

    @Column(name = "rok_isporuke")
    private Integer rokIsporuke;

    @Column(name = "naziv_ponudjaca")
    private String nazivPonudjaca;

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

    public Prvorangirani id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public Prvorangirani sifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getSifraPonude() {
        return this.sifraPonude;
    }

    public Prvorangirani sifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
        return this;
    }

    public void setSifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public Integer getBrojPartije() {
        return this.brojPartije;
    }

    public Prvorangirani brojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
        return this;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getAtc() {
        return this.atc;
    }

    public Prvorangirani atc(String atc) {
        this.atc = atc;
        return this;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getInn() {
        return this.inn;
    }

    public Prvorangirani inn(String inn) {
        this.inn = inn;
        return this;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getZastceniNaziv() {
        return this.zastceniNaziv;
    }

    public Prvorangirani zastceniNaziv(String zastceniNaziv) {
        this.zastceniNaziv = zastceniNaziv;
        return this;
    }

    public void setZastceniNaziv(String zastceniNaziv) {
        this.zastceniNaziv = zastceniNaziv;
    }

    public String getFarmaceutskiOblikLijeka() {
        return this.farmaceutskiOblikLijeka;
    }

    public Prvorangirani farmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
        return this;
    }

    public void setFarmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public String getJacinaLijeka() {
        return this.jacinaLijeka;
    }

    public Prvorangirani jacinaLijeka(String jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
        return this;
    }

    public void setJacinaLijeka(String jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
    }

    public String getPakovanje() {
        return this.pakovanje;
    }

    public Prvorangirani pakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
        return this;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazenaKolicina() {
        return this.trazenaKolicina;
    }

    public Prvorangirani trazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
        return this;
    }

    public void setTrazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
    }

    public Double getTrazenaJedinicnaCijena() {
        return this.trazenaJedinicnaCijena;
    }

    public Prvorangirani trazenaJedinicnaCijena(Double trazenaJedinicnaCijena) {
        this.trazenaJedinicnaCijena = trazenaJedinicnaCijena;
        return this;
    }

    public void setTrazenaJedinicnaCijena(Double trazenaJedinicnaCijena) {
        this.trazenaJedinicnaCijena = trazenaJedinicnaCijena;
    }

    public Double getProcijenjenaVrijednost() {
        return this.procijenjenaVrijednost;
    }

    public Prvorangirani procijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
        return this;
    }

    public void setProcijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }

    public Double getPonudjenaJedinicnaCijena() {
        return this.ponudjenaJedinicnaCijena;
    }

    public Prvorangirani ponudjenaJedinicnaCijena(Double ponudjenaJedinicnaCijena) {
        this.ponudjenaJedinicnaCijena = ponudjenaJedinicnaCijena;
        return this;
    }

    public void setPonudjenaJedinicnaCijena(Double ponudjenaJedinicnaCijena) {
        this.ponudjenaJedinicnaCijena = ponudjenaJedinicnaCijena;
    }

    public Double getPonudjenaVrijednost() {
        return this.ponudjenaVrijednost;
    }

    public Prvorangirani ponudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
        return this;
    }

    public void setPonudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public Integer getRokIsporuke() {
        return this.rokIsporuke;
    }

    public Prvorangirani rokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
        return this;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public String getNazivPonudjaca() {
        return this.nazivPonudjaca;
    }

    public Prvorangirani nazivPonudjaca(String nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
        return this;
    }

    public void setNazivPonudjaca(String nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
    }

    public String getBrojUgovora() {
        return this.brojUgovora;
    }

    public Prvorangirani brojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
        return this;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public LocalDate getDatumUgovora() {
        return this.datumUgovora;
    }

    public Prvorangirani datumUgovora(LocalDate datumUgovora) {
        this.datumUgovora = datumUgovora;
        return this;
    }

    public void setDatumUgovora(LocalDate datumUgovora) {
        this.datumUgovora = datumUgovora;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prvorangirani)) {
            return false;
        }
        return id != null && id.equals(((Prvorangirani) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prvorangirani{" +
            "id=" + getId() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", sifraPonude=" + getSifraPonude() +
            ", brojPartije=" + getBrojPartije() +
            ", atc='" + getAtc() + "'" +
            ", inn='" + getInn() + "'" +
            ", zastceniNaziv='" + getZastceniNaziv() + "'" +
            ", farmaceutskiOblikLijeka='" + getFarmaceutskiOblikLijeka() + "'" +
            ", jacinaLijeka='" + getJacinaLijeka() + "'" +
            ", pakovanje='" + getPakovanje() + "'" +
            ", trazenaKolicina=" + getTrazenaKolicina() +
            ", trazenaJedinicnaCijena=" + getTrazenaJedinicnaCijena() +
            ", procijenjenaVrijednost=" + getProcijenjenaVrijednost() +
            ", ponudjenaJedinicnaCijena=" + getPonudjenaJedinicnaCijena() +
            ", ponudjenaVrijednost=" + getPonudjenaVrijednost() +
            ", rokIsporuke=" + getRokIsporuke() +
            ", nazivPonudjaca='" + getNazivPonudjaca() + "'" +
            ", brojUgovora='" + getBrojUgovora() + "'" +
            ", datumUgovora='" + getDatumUgovora() + "'" +
            "}";
    }
}
