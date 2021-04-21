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
 * Criteria class for the {@link tenderi.domain.ViewVrednovanje} entity. This class is used
 * in {@link tenderi.web.rest.ViewVrednovanjeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /view-vrednovanjes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ViewVrednovanjeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter sifraPonude;

    private IntegerFilter brojPartije;

    private StringFilter nazivPonudjaca;

    private DoubleFilter procijenjenaVrijednost;

    private DoubleFilter ponudjenaVrijednost;

    private IntegerFilter kolicina;

    private StringFilter atc;

    private StringFilter inn;

    private StringFilter zastceniNaziv;

    private StringFilter farmaceutskiOblikLijeka;

    private StringFilter jacinaLijeka;

    private StringFilter pakovanje;

    private DoubleFilter bodIsporuka;

    private IntegerFilter rokIsporuke;

    private DoubleFilter bodCijena;

    private DoubleFilter bodUkupno;

    public ViewVrednovanjeCriteria() {}

    public ViewVrednovanjeCriteria(ViewVrednovanjeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.sifraPonude = other.sifraPonude == null ? null : other.sifraPonude.copy();
        this.brojPartije = other.brojPartije == null ? null : other.brojPartije.copy();
        this.nazivPonudjaca = other.nazivPonudjaca == null ? null : other.nazivPonudjaca.copy();
        this.procijenjenaVrijednost = other.procijenjenaVrijednost == null ? null : other.procijenjenaVrijednost.copy();
        this.ponudjenaVrijednost = other.ponudjenaVrijednost == null ? null : other.ponudjenaVrijednost.copy();
        this.kolicina = other.kolicina == null ? null : other.kolicina.copy();
        this.atc = other.atc == null ? null : other.atc.copy();
        this.inn = other.inn == null ? null : other.inn.copy();
        this.zastceniNaziv = other.zastceniNaziv == null ? null : other.zastceniNaziv.copy();
        this.farmaceutskiOblikLijeka = other.farmaceutskiOblikLijeka == null ? null : other.farmaceutskiOblikLijeka.copy();
        this.jacinaLijeka = other.jacinaLijeka == null ? null : other.jacinaLijeka.copy();
        this.pakovanje = other.pakovanje == null ? null : other.pakovanje.copy();
        this.bodIsporuka = other.bodIsporuka == null ? null : other.bodIsporuka.copy();
        this.rokIsporuke = other.rokIsporuke == null ? null : other.rokIsporuke.copy();
        this.bodCijena = other.bodCijena == null ? null : other.bodCijena.copy();
        this.bodUkupno = other.bodUkupno == null ? null : other.bodUkupno.copy();
    }

    @Override
    public ViewVrednovanjeCriteria copy() {
        return new ViewVrednovanjeCriteria(this);
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

    public IntegerFilter getKolicina() {
        return kolicina;
    }

    public IntegerFilter kolicina() {
        if (kolicina == null) {
            kolicina = new IntegerFilter();
        }
        return kolicina;
    }

    public void setKolicina(IntegerFilter kolicina) {
        this.kolicina = kolicina;
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

    public DoubleFilter getBodCijena() {
        return bodCijena;
    }

    public DoubleFilter bodCijena() {
        if (bodCijena == null) {
            bodCijena = new DoubleFilter();
        }
        return bodCijena;
    }

    public void setBodCijena(DoubleFilter bodCijena) {
        this.bodCijena = bodCijena;
    }

    public DoubleFilter getBodUkupno() {
        return bodUkupno;
    }

    public DoubleFilter bodUkupno() {
        if (bodUkupno == null) {
            bodUkupno = new DoubleFilter();
        }
        return bodUkupno;
    }

    public void setBodUkupno(DoubleFilter bodUkupno) {
        this.bodUkupno = bodUkupno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ViewVrednovanjeCriteria that = (ViewVrednovanjeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(sifraPonude, that.sifraPonude) &&
            Objects.equals(brojPartije, that.brojPartije) &&
            Objects.equals(nazivPonudjaca, that.nazivPonudjaca) &&
            Objects.equals(procijenjenaVrijednost, that.procijenjenaVrijednost) &&
            Objects.equals(ponudjenaVrijednost, that.ponudjenaVrijednost) &&
            Objects.equals(kolicina, that.kolicina) &&
            Objects.equals(atc, that.atc) &&
            Objects.equals(inn, that.inn) &&
            Objects.equals(zastceniNaziv, that.zastceniNaziv) &&
            Objects.equals(farmaceutskiOblikLijeka, that.farmaceutskiOblikLijeka) &&
            Objects.equals(jacinaLijeka, that.jacinaLijeka) &&
            Objects.equals(pakovanje, that.pakovanje) &&
            Objects.equals(bodIsporuka, that.bodIsporuka) &&
            Objects.equals(rokIsporuke, that.rokIsporuke) &&
            Objects.equals(bodCijena, that.bodCijena) &&
            Objects.equals(bodUkupno, that.bodUkupno)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sifraPostupka,
            sifraPonude,
            brojPartije,
            nazivPonudjaca,
            procijenjenaVrijednost,
            ponudjenaVrijednost,
            kolicina,
            atc,
            inn,
            zastceniNaziv,
            farmaceutskiOblikLijeka,
            jacinaLijeka,
            pakovanje,
            bodIsporuka,
            rokIsporuke,
            bodCijena,
            bodUkupno
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewVrednovanjeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (sifraPonude != null ? "sifraPonude=" + sifraPonude + ", " : "") +
            (brojPartije != null ? "brojPartije=" + brojPartije + ", " : "") +
            (nazivPonudjaca != null ? "nazivPonudjaca=" + nazivPonudjaca + ", " : "") +
            (procijenjenaVrijednost != null ? "procijenjenaVrijednost=" + procijenjenaVrijednost + ", " : "") +
            (ponudjenaVrijednost != null ? "ponudjenaVrijednost=" + ponudjenaVrijednost + ", " : "") +
            (kolicina != null ? "kolicina=" + kolicina + ", " : "") +
            (atc != null ? "atc=" + atc + ", " : "") +
            (inn != null ? "inn=" + inn + ", " : "") +
            (zastceniNaziv != null ? "zastceniNaziv=" + zastceniNaziv + ", " : "") +
            (farmaceutskiOblikLijeka != null ? "farmaceutskiOblikLijeka=" + farmaceutskiOblikLijeka + ", " : "") +
            (jacinaLijeka != null ? "jacinaLijeka=" + jacinaLijeka + ", " : "") +
            (pakovanje != null ? "pakovanje=" + pakovanje + ", " : "") +
            (bodIsporuka != null ? "bodIsporuka=" + bodIsporuka + ", " : "") +
            (rokIsporuke != null ? "rokIsporuke=" + rokIsporuke + ", " : "") +
            (bodCijena != null ? "bodCijena=" + bodCijena + ", " : "") +
            (bodUkupno != null ? "bodUkupno=" + bodUkupno + ", " : "") +
            "}";
    }
}
