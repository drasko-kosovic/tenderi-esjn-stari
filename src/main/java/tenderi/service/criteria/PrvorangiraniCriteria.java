package tenderi.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link tenderi.domain.Prvorangirani} entity. This class is used
 * in {@link tenderi.web.rest.PrvorangiraniResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /prvorangiranis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PrvorangiraniCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter sifraPonude;

    private IntegerFilter brojPartije;

    private StringFilter atc;

    private StringFilter inn;

    private StringFilter zastceniNaziv;

    private StringFilter farmaceutskiOblikLijeka;

    private StringFilter jacinaLijeka;

    private StringFilter pakovanje;

    private IntegerFilter trazenaKolicina;

    private DoubleFilter trazenaJedinicnaCijena;

    private DoubleFilter procijenjenaVrijednost;

    private DoubleFilter ponudjenaJedinicnaCijena;

    private DoubleFilter ponudjenaVrijednost;

    private IntegerFilter rokIsporuke;

    private StringFilter nazivPonudjaca;

    private DoubleFilter bodIsporuka;

    private StringFilter brojUgovora;

    private LocalDateFilter datumUgovora;

    public PrvorangiraniCriteria() {}

    public PrvorangiraniCriteria(PrvorangiraniCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.sifraPonude = other.sifraPonude == null ? null : other.sifraPonude.copy();
        this.brojPartije = other.brojPartije == null ? null : other.brojPartije.copy();
        this.atc = other.atc == null ? null : other.atc.copy();
        this.inn = other.inn == null ? null : other.inn.copy();
        this.zastceniNaziv = other.zastceniNaziv == null ? null : other.zastceniNaziv.copy();
        this.farmaceutskiOblikLijeka = other.farmaceutskiOblikLijeka == null ? null : other.farmaceutskiOblikLijeka.copy();
        this.jacinaLijeka = other.jacinaLijeka == null ? null : other.jacinaLijeka.copy();
        this.pakovanje = other.pakovanje == null ? null : other.pakovanje.copy();
        this.trazenaKolicina = other.trazenaKolicina == null ? null : other.trazenaKolicina.copy();
        this.trazenaJedinicnaCijena = other.trazenaJedinicnaCijena == null ? null : other.trazenaJedinicnaCijena.copy();
        this.procijenjenaVrijednost = other.procijenjenaVrijednost == null ? null : other.procijenjenaVrijednost.copy();
        this.ponudjenaJedinicnaCijena = other.ponudjenaJedinicnaCijena == null ? null : other.ponudjenaJedinicnaCijena.copy();
        this.ponudjenaVrijednost = other.ponudjenaVrijednost == null ? null : other.ponudjenaVrijednost.copy();
        this.rokIsporuke = other.rokIsporuke == null ? null : other.rokIsporuke.copy();
        this.nazivPonudjaca = other.nazivPonudjaca == null ? null : other.nazivPonudjaca.copy();
        this.bodIsporuka = other.bodIsporuka == null ? null : other.bodIsporuka.copy();
        this.brojUgovora = other.brojUgovora == null ? null : other.brojUgovora.copy();
        this.datumUgovora = other.datumUgovora == null ? null : other.datumUgovora.copy();
    }

    @Override
    public PrvorangiraniCriteria copy() {
        return new PrvorangiraniCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSifraPostupka() {
        return sifraPostupka;
    }

    public IntegerFilter sifraPostupka() {
        if (sifraPostupka == null) {
            sifraPostupka = new IntegerFilter();
        }
        return sifraPostupka;
    }

    public void setSifraPostupka(IntegerFilter sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public IntegerFilter getSifraPonude() {
        return sifraPonude;
    }

    public IntegerFilter sifraPonude() {
        if (sifraPonude == null) {
            sifraPonude = new IntegerFilter();
        }
        return sifraPonude;
    }

    public void setSifraPonude(IntegerFilter sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public IntegerFilter getBrojPartije() {
        return brojPartije;
    }

    public IntegerFilter brojPartije() {
        if (brojPartije == null) {
            brojPartije = new IntegerFilter();
        }
        return brojPartije;
    }

    public void setBrojPartije(IntegerFilter brojPartije) {
        this.brojPartije = brojPartije;
    }

    public StringFilter getAtc() {
        return atc;
    }

    public StringFilter atc() {
        if (atc == null) {
            atc = new StringFilter();
        }
        return atc;
    }

    public void setAtc(StringFilter atc) {
        this.atc = atc;
    }

    public StringFilter getInn() {
        return inn;
    }

    public StringFilter inn() {
        if (inn == null) {
            inn = new StringFilter();
        }
        return inn;
    }

    public void setInn(StringFilter inn) {
        this.inn = inn;
    }

    public StringFilter getZastceniNaziv() {
        return zastceniNaziv;
    }

    public StringFilter zastceniNaziv() {
        if (zastceniNaziv == null) {
            zastceniNaziv = new StringFilter();
        }
        return zastceniNaziv;
    }

    public void setZastceniNaziv(StringFilter zastceniNaziv) {
        this.zastceniNaziv = zastceniNaziv;
    }

    public StringFilter getFarmaceutskiOblikLijeka() {
        return farmaceutskiOblikLijeka;
    }

    public StringFilter farmaceutskiOblikLijeka() {
        if (farmaceutskiOblikLijeka == null) {
            farmaceutskiOblikLijeka = new StringFilter();
        }
        return farmaceutskiOblikLijeka;
    }

    public void setFarmaceutskiOblikLijeka(StringFilter farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public StringFilter getJacinaLijeka() {
        return jacinaLijeka;
    }

    public StringFilter jacinaLijeka() {
        if (jacinaLijeka == null) {
            jacinaLijeka = new StringFilter();
        }
        return jacinaLijeka;
    }

    public void setJacinaLijeka(StringFilter jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
    }

    public StringFilter getPakovanje() {
        return pakovanje;
    }

    public StringFilter pakovanje() {
        if (pakovanje == null) {
            pakovanje = new StringFilter();
        }
        return pakovanje;
    }

    public void setPakovanje(StringFilter pakovanje) {
        this.pakovanje = pakovanje;
    }

    public IntegerFilter getTrazenaKolicina() {
        return trazenaKolicina;
    }

    public IntegerFilter trazenaKolicina() {
        if (trazenaKolicina == null) {
            trazenaKolicina = new IntegerFilter();
        }
        return trazenaKolicina;
    }

    public void setTrazenaKolicina(IntegerFilter trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
    }

    public DoubleFilter getTrazenaJedinicnaCijena() {
        return trazenaJedinicnaCijena;
    }

    public DoubleFilter trazenaJedinicnaCijena() {
        if (trazenaJedinicnaCijena == null) {
            trazenaJedinicnaCijena = new DoubleFilter();
        }
        return trazenaJedinicnaCijena;
    }

    public void setTrazenaJedinicnaCijena(DoubleFilter trazenaJedinicnaCijena) {
        this.trazenaJedinicnaCijena = trazenaJedinicnaCijena;
    }

    public DoubleFilter getProcijenjenaVrijednost() {
        return procijenjenaVrijednost;
    }

    public DoubleFilter procijenjenaVrijednost() {
        if (procijenjenaVrijednost == null) {
            procijenjenaVrijednost = new DoubleFilter();
        }
        return procijenjenaVrijednost;
    }

    public void setProcijenjenaVrijednost(DoubleFilter procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }

    public DoubleFilter getPonudjenaJedinicnaCijena() {
        return ponudjenaJedinicnaCijena;
    }

    public DoubleFilter ponudjenaJedinicnaCijena() {
        if (ponudjenaJedinicnaCijena == null) {
            ponudjenaJedinicnaCijena = new DoubleFilter();
        }
        return ponudjenaJedinicnaCijena;
    }

    public void setPonudjenaJedinicnaCijena(DoubleFilter ponudjenaJedinicnaCijena) {
        this.ponudjenaJedinicnaCijena = ponudjenaJedinicnaCijena;
    }

    public DoubleFilter getPonudjenaVrijednost() {
        return ponudjenaVrijednost;
    }

    public DoubleFilter ponudjenaVrijednost() {
        if (ponudjenaVrijednost == null) {
            ponudjenaVrijednost = new DoubleFilter();
        }
        return ponudjenaVrijednost;
    }

    public void setPonudjenaVrijednost(DoubleFilter ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public IntegerFilter getRokIsporuke() {
        return rokIsporuke;
    }

    public IntegerFilter rokIsporuke() {
        if (rokIsporuke == null) {
            rokIsporuke = new IntegerFilter();
        }
        return rokIsporuke;
    }

    public void setRokIsporuke(IntegerFilter rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public StringFilter getNazivPonudjaca() {
        return nazivPonudjaca;
    }

    public StringFilter nazivPonudjaca() {
        if (nazivPonudjaca == null) {
            nazivPonudjaca = new StringFilter();
        }
        return nazivPonudjaca;
    }

    public void setNazivPonudjaca(StringFilter nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
    }

    public DoubleFilter getBodIsporuka() {
        return bodIsporuka;
    }

    public DoubleFilter bodIsporuka() {
        if (bodIsporuka == null) {
            bodIsporuka = new DoubleFilter();
        }
        return bodIsporuka;
    }

    public void setBodIsporuka(DoubleFilter bodIsporuka) {
        this.bodIsporuka = bodIsporuka;
    }

    public StringFilter getBrojUgovora() {
        return brojUgovora;
    }

    public StringFilter brojUgovora() {
        if (brojUgovora == null) {
            brojUgovora = new StringFilter();
        }
        return brojUgovora;
    }

    public void setBrojUgovora(StringFilter brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public LocalDateFilter getDatumUgovora() {
        return datumUgovora;
    }

    public LocalDateFilter datumUgovora() {
        if (datumUgovora == null) {
            datumUgovora = new LocalDateFilter();
        }
        return datumUgovora;
    }

    public void setDatumUgovora(LocalDateFilter datumUgovora) {
        this.datumUgovora = datumUgovora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PrvorangiraniCriteria that = (PrvorangiraniCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(sifraPonude, that.sifraPonude) &&
            Objects.equals(brojPartije, that.brojPartije) &&
            Objects.equals(atc, that.atc) &&
            Objects.equals(inn, that.inn) &&
            Objects.equals(zastceniNaziv, that.zastceniNaziv) &&
            Objects.equals(farmaceutskiOblikLijeka, that.farmaceutskiOblikLijeka) &&
            Objects.equals(jacinaLijeka, that.jacinaLijeka) &&
            Objects.equals(pakovanje, that.pakovanje) &&
            Objects.equals(trazenaKolicina, that.trazenaKolicina) &&
            Objects.equals(trazenaJedinicnaCijena, that.trazenaJedinicnaCijena) &&
            Objects.equals(procijenjenaVrijednost, that.procijenjenaVrijednost) &&
            Objects.equals(ponudjenaJedinicnaCijena, that.ponudjenaJedinicnaCijena) &&
            Objects.equals(ponudjenaVrijednost, that.ponudjenaVrijednost) &&
            Objects.equals(rokIsporuke, that.rokIsporuke) &&
            Objects.equals(nazivPonudjaca, that.nazivPonudjaca) &&
            Objects.equals(bodIsporuka, that.bodIsporuka) &&
            Objects.equals(brojUgovora, that.brojUgovora) &&
            Objects.equals(datumUgovora, that.datumUgovora)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sifraPostupka,
            sifraPonude,
            brojPartije,
            atc,
            inn,
            zastceniNaziv,
            farmaceutskiOblikLijeka,
            jacinaLijeka,
            pakovanje,
            trazenaKolicina,
            trazenaJedinicnaCijena,
            procijenjenaVrijednost,
            ponudjenaJedinicnaCijena,
            ponudjenaVrijednost,
            rokIsporuke,
            nazivPonudjaca,
            bodIsporuka,
            brojUgovora,
            datumUgovora
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrvorangiraniCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (sifraPonude != null ? "sifraPonude=" + sifraPonude + ", " : "") +
            (brojPartije != null ? "brojPartije=" + brojPartije + ", " : "") +
            (atc != null ? "atc=" + atc + ", " : "") +
            (inn != null ? "inn=" + inn + ", " : "") +
            (zastceniNaziv != null ? "zastceniNaziv=" + zastceniNaziv + ", " : "") +
            (farmaceutskiOblikLijeka != null ? "farmaceutskiOblikLijeka=" + farmaceutskiOblikLijeka + ", " : "") +
            (jacinaLijeka != null ? "jacinaLijeka=" + jacinaLijeka + ", " : "") +
            (pakovanje != null ? "pakovanje=" + pakovanje + ", " : "") +
            (trazenaKolicina != null ? "trazenaKolicina=" + trazenaKolicina + ", " : "") +
            (trazenaJedinicnaCijena != null ? "trazenaJedinicnaCijena=" + trazenaJedinicnaCijena + ", " : "") +
            (procijenjenaVrijednost != null ? "procijenjenaVrijednost=" + procijenjenaVrijednost + ", " : "") +
            (ponudjenaJedinicnaCijena != null ? "ponudjenaJedinicnaCijena=" + ponudjenaJedinicnaCijena + ", " : "") +
            (ponudjenaVrijednost != null ? "ponudjenaVrijednost=" + ponudjenaVrijednost + ", " : "") +
            (rokIsporuke != null ? "rokIsporuke=" + rokIsporuke + ", " : "") +
            (nazivPonudjaca != null ? "nazivPonudjaca=" + nazivPonudjaca + ", " : "") +
            (bodIsporuka != null ? "bodIsporuka=" + bodIsporuka + ", " : "") +
            (brojUgovora != null ? "brojUgovora=" + brojUgovora + ", " : "") +
            (datumUgovora != null ? "datumUgovora=" + datumUgovora + ", " : "") +
            "}";
    }
}
