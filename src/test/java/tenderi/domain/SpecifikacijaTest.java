package tenderi.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tenderi.web.rest.TestUtil;

class SpecifikacijaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specifikacija.class);
        Specifikacija specifikacija1 = new Specifikacija();
        specifikacija1.setId(1L);
        Specifikacija specifikacija2 = new Specifikacija();
        specifikacija2.setId(specifikacija1.getId());
        assertThat(specifikacija1).isEqualTo(specifikacija2);
        specifikacija2.setId(2L);
        assertThat(specifikacija1).isNotEqualTo(specifikacija2);
        specifikacija1.setId(null);
        assertThat(specifikacija1).isNotEqualTo(specifikacija2);
    }
}
