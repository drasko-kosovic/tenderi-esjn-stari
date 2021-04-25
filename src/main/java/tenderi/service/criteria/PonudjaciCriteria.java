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
 * Criteria class for the {@link tenderi.domain.Ponudjaci} entity. This class is used
 * in {@link tenderi.web.rest.PonudjaciResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ponudjacis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PonudjaciCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter sifraPonude;

    private StringFilter nazivPonudjaca;

    public PonudjaciCriteria() {}

    public PonudjaciCriteria(PonudjaciCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.sifraPonude = other.sifraPonude == null ? null : other.sifraPonude.copy();
        this.nazivPonudjaca = other.nazivPonudjaca == null ? null : other.nazivPonudjaca.copy();
    }

    @Override
    public PonudjaciCriteria copy() {
        return new PonudjaciCriteria(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PonudjaciCriteria that = (PonudjaciCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(sifraPonude, that.sifraPonude) &&
            Objects.equals(nazivPonudjaca, that.nazivPonudjaca)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sifraPostupka, sifraPonude, nazivPonudjaca);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PonudjaciCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (sifraPonude != null ? "sifraPonude=" + sifraPonude + ", " : "") +
            (nazivPonudjaca != null ? "nazivPonudjaca=" + nazivPonudjaca + ", " : "") +
            "}";
    }
}
