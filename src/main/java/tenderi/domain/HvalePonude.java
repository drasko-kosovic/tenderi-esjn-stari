package tenderi.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HvalePonude.
 */
@Entity
@Table(name = "hvale_partije")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HvalePonude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_postupka")
    private Integer sifraPostupka;

    @Column(name = "broj_partije")
    private Integer brojPartije;

    @Column(name = "inn")
    private String inn;

    @Column(name = "farmaceutski_oblik_lijeka")
    private String farmaceutskiOblikLijeka;

    @Column(name = "pakovanje")
    private String pakovanje;

    @Column(name = "trazena_kolicina")
    private Integer trazenaKolicina;

    @Column(name = "trazena_jedinicna_cijena")
    private Double trazenaJedinicnaCijena;

    @Column(name = "procijenjena_vrijednost")
    private Double procijenjenaVrijednost;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Integer getBrojPartije() {
        return brojPartije;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getFarmaceutskiOblikLijeka() {
        return farmaceutskiOblikLijeka;
    }

    public void setFarmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public String getPakovanje() {
        return pakovanje;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazenaKolicina() {
        return trazenaKolicina;
    }

    public void setTrazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
    }

    public Double getTrazenaJedinicnaCijena() {
        return trazenaJedinicnaCijena;
    }

    public void setTrazenaJedinicnaCijena(Double trazenaJedinaicnaCijena) {
        this.trazenaJedinicnaCijena = trazenaJedinaicnaCijena;
    }

    public Double getProcijenjenaVrijednost() {
        return procijenjenaVrijednost;
    }

    public void setProcijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }
}
