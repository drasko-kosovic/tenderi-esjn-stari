package tenderi.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ViewVrednovanje.
 */
@Entity
@Table(name = "view_vrednovanje")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ViewVrednovanje implements Serializable {

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
    private Integer trazanaKolicina;

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

    @Column(name = "naziv_proizvodjaca")
    private String nazivProizvodjaca;

    @Column(name = "broj_ugovora")
    private String brojUgovora;

    @Column(name = "datum_ugovora")
    private Date datumjUgovora;

    @Column(name = "bod_cijena")
    private Double bodCijena;

    @Column(name = "bod_rok")
    private Double bodRok;

    @Column(name = "bod_ukupno")
    private Double bodUkupno;

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

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getZastceniNaziv() {
        return zastceniNaziv;
    }

    public void setZastceniNaziv(String zastceniNaziv) {
        this.zastceniNaziv = zastceniNaziv;
    }

    public String getFarmaceutskiOblikLijeka() {
        return farmaceutskiOblikLijeka;
    }

    public void setFarmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public String getJacinaLijeka() {
        return jacinaLijeka;
    }

    public void setJacinaLijeka(String jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
    }

    public String getPakovanje() {
        return pakovanje;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazanaKolicina() {
        return trazanaKolicina;
    }

    public void setTrazanaKolicina(Integer trazanaKolicina) {
        this.trazanaKolicina = trazanaKolicina;
    }

    public Double getTrazenaJedinicnaCijena() {
        return trazenaJedinicnaCijena;
    }

    public void setTrazenaJedinicnaCijena(Double trazenaJedinicnaCijena) {
        this.trazenaJedinicnaCijena = trazenaJedinicnaCijena;
    }

    public Double getProcijenjenaVrijednost() {
        return procijenjenaVrijednost;
    }

    public void setProcijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }

    public Double getPonudjenaJedinicnaCijena() {
        return ponudjenaJedinicnaCijena;
    }

    public void setPonudjenaJedinicnaCijena(Double ponudjenaJedinicnaCijena) {
        this.ponudjenaJedinicnaCijena = ponudjenaJedinicnaCijena;
    }

    public Double getPonudjenaVrijednost() {
        return ponudjenaVrijednost;
    }

    public void setPonudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public Integer getRokIsporuke() {
        return rokIsporuke;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
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

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public Date getDatumjUgovora() {
        return datumjUgovora;
    }

    public void setDatumjUgovora(Date datumjUgovora) {
        this.datumjUgovora = datumjUgovora;
    }

    public Double getBodCijena() {
        return bodCijena;
    }

    public void setBodCijena(Double bodCijena) {
        this.bodCijena = bodCijena;
    }

    public Double getBodRok() {
        return bodRok;
    }

    public void setBodRok(Double bodRok) {
        this.bodRok = bodRok;
    }

    public Double getBodUkupno() {
        return bodUkupno;
    }

    public void setBodUkupno(Double bodUkupno) {
        this.bodUkupno = bodUkupno;
    }
}
