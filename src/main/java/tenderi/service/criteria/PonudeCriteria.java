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
 * Criteria class for the {@link tenderi.domain.Ponude} entity. This class is used
 * in {@link tenderi.web.rest.PonudeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ponudes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PonudeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter sifraPonude;

    private IntegerFilter brojPartije;

    private StringFilter nazivPonudjaca;

    private StringFilter naziProizvodjaca;

    private StringFilter zastceniNaziv;

    private DoubleFilter ponudjenaVrijednost;

    private DoubleFilter ponudjenaJedinicnaCijena;

    private IntegerFilter rokIsporuke;

    public PonudeCriteria() {}

    public PonudeCriteria(PonudeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.sifraPonude = other.sifraPonude == null ? null : other.sifraPonude.copy();
        this.brojPartije = other.brojPartije == null ? null : other.brojPartije.copy();
        this.nazivPonudjaca = other.nazivPonudjaca == null ? null : other.nazivPonudjaca.copy();
        this.naziProizvodjaca = other.naziProizvodjaca == null ? null : other.naziProizvodjaca.copy();
        this.zastceniNaziv = other.zastceniNaziv == null ? null : other.zastceniNaziv.copy();
        this.ponudjenaVrijednost = other.ponudjenaVrijednost == null ? null : other.ponudjenaVrijednost.copy();
        this.ponudjenaJedinicnaCijena = other.ponudjenaJedinicnaCijena == null ? null : other.ponudjenaJedinicnaCijena.copy();
        this.rokIsporuke = other.rokIsporuke == null ? null : other.rokIsporuke.copy();
    }

    @Override
    public PonudeCriteria copy() {
        return new PonudeCriteria(this);
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

    public StringFilter getNaziProizvodjaca() {
        return naziProizvodjaca;
    }

    public StringFilter naziProizvodjaca() {
        if (naziProizvodjaca == null) {
            naziProizvodjaca = new StringFilter();
        }
        return naziProizvodjaca;
    }

    public void setNaziProizvodjaca(StringFilter naziProizvodjaca) {
        this.naziProizvodjaca = naziProizvodjaca;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PonudeCriteria that = (PonudeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(sifraPonude, that.sifraPonude) &&
            Objects.equals(brojPartije, that.brojPartije) &&
            Objects.equals(nazivPonudjaca, that.nazivPonudjaca) &&
            Objects.equals(naziProizvodjaca, that.naziProizvodjaca) &&
            Objects.equals(zastceniNaziv, that.zastceniNaziv) &&
            Objects.equals(ponudjenaVrijednost, that.ponudjenaVrijednost) &&
            Objects.equals(ponudjenaJedinicnaCijena, that.ponudjenaJedinicnaCijena) &&
            Objects.equals(rokIsporuke, that.rokIsporuke)
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
            naziProizvodjaca,
            zastceniNaziv,
            ponudjenaVrijednost,
            ponudjenaJedinicnaCijena,
            rokIsporuke
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PonudeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (sifraPonude != null ? "sifraPonude=" + sifraPonude + ", " : "") +
            (brojPartije != null ? "brojPartije=" + brojPartije + ", " : "") +
            (nazivPonudjaca != null ? "nazivPonudjaca=" + nazivPonudjaca + ", " : "") +
            (naziProizvodjaca != null ? "naziProizvodjaca=" + naziProizvodjaca + ", " : "") +
            (zastceniNaziv != null ? "zastceniNaziv=" + zastceniNaziv + ", " : "") +
            (ponudjenaVrijednost != null ? "ponudjenaVrijednost=" + ponudjenaVrijednost + ", " : "") +
            (ponudjenaJedinicnaCijena != null ? "ponudjenaJedinicnaCijena=" + ponudjenaJedinicnaCijena + ", " : "") +
            (rokIsporuke != null ? "rokIsporuke=" + rokIsporuke + ", " : "") +
            "}";
    }
}
