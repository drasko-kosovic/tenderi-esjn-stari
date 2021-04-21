package tenderi.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link tenderi.domain.Specifikacija} entity. This class is used
 * in {@link tenderi.web.rest.SpecifikacijaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /specifikacijas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SpecifikacijaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter brojPartije;

    private StringFilter atc;

    private StringFilter inn;

    private StringFilter farmaceutskiOblikLijeka;

    private StringFilter jacinaLijeka;

    private IntegerFilter trazenaKolicina;

    private StringFilter pakovanje;

    private DoubleFilter procijenjenaVrijednost;

    private DoubleFilter trazenaJedinicnaCijena;

    public SpecifikacijaCriteria() {}

    public SpecifikacijaCriteria(SpecifikacijaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.brojPartije = other.brojPartije == null ? null : other.brojPartije.copy();
        this.atc = other.atc == null ? null : other.atc.copy();
        this.inn = other.inn == null ? null : other.inn.copy();
        this.farmaceutskiOblikLijeka = other.farmaceutskiOblikLijeka == null ? null : other.farmaceutskiOblikLijeka.copy();
        this.jacinaLijeka = other.jacinaLijeka == null ? null : other.jacinaLijeka.copy();
        this.trazenaKolicina = other.trazenaKolicina == null ? null : other.trazenaKolicina.copy();
        this.pakovanje = other.pakovanje == null ? null : other.pakovanje.copy();
        this.procijenjenaVrijednost = other.procijenjenaVrijednost == null ? null : other.procijenjenaVrijednost.copy();
        this.trazenaJedinicnaCijena = other.trazenaJedinicnaCijena == null ? null : other.trazenaJedinicnaCijena.copy();
    }

    @Override
    public SpecifikacijaCriteria copy() {
        return new SpecifikacijaCriteria(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SpecifikacijaCriteria that = (SpecifikacijaCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(brojPartije, that.brojPartije) &&
            Objects.equals(atc, that.atc) &&
            Objects.equals(inn, that.inn) &&
            Objects.equals(farmaceutskiOblikLijeka, that.farmaceutskiOblikLijeka) &&
            Objects.equals(jacinaLijeka, that.jacinaLijeka) &&
            Objects.equals(trazenaKolicina, that.trazenaKolicina) &&
            Objects.equals(pakovanje, that.pakovanje) &&
            Objects.equals(procijenjenaVrijednost, that.procijenjenaVrijednost) &&
            Objects.equals(trazenaJedinicnaCijena, that.trazenaJedinicnaCijena)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sifraPostupka,
            brojPartije,
            atc,
            inn,
            farmaceutskiOblikLijeka,
            jacinaLijeka,
            trazenaKolicina,
            pakovanje,
            procijenjenaVrijednost,
            trazenaJedinicnaCijena
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecifikacijaCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (brojPartije != null ? "brojPartije=" + brojPartije + ", " : "") +
            (atc != null ? "atc=" + atc + ", " : "") +
            (inn != null ? "inn=" + inn + ", " : "") +
            (farmaceutskiOblikLijeka != null ? "farmaceutskiOblikLijeka=" + farmaceutskiOblikLijeka + ", " : "") +
            (jacinaLijeka != null ? "jacinaLijeka=" + jacinaLijeka + ", " : "") +
            (trazenaKolicina != null ? "trazenaKolicina=" + trazenaKolicina + ", " : "") +
            (pakovanje != null ? "pakovanje=" + pakovanje + ", " : "") +
            (procijenjenaVrijednost != null ? "procijenjenaVrijednost=" + procijenjenaVrijednost + ", " : "") +
            (trazenaJedinicnaCijena != null ? "trazenaJedinicnaCijena=" + trazenaJedinicnaCijena + ", " : "") +
            "}";
    }
}
