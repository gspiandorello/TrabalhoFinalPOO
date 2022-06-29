package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalizacaoTest {

    @Test
    void setCodigo() {
        Localizacao newObject = new Localizacao(1, "rua",
                32.0, 34.5);
        int old = newObject.getCodigo();

        newObject.setCodigo(41);

        assertNotEquals(newObject.getCodigo(), old);
    }

    @Test
    void setLogradouro() {
    }

    @Test
    void setLatitude() {
        Localizacao newObject = new Localizacao(1, "rua",
                32.0, 34.5);
        double old = newObject.getLatitude();

        newObject.setLatitude(41.123);

        assertNotEquals(newObject.getLatitude(), old);
    }

    @Test
    void setLongitude() {
        Localizacao newObject = new Localizacao(1, "rua",
                32.0, 34.5);
        double old = newObject.getLongitude();

        newObject.setLongitude(12.312);

        assertNotEquals(newObject.getLongitude(), old);
    }

    @Test
    void getDistance() {
        Localizacao newObject = new Localizacao(1, "rua",
                32.0, 34.5);

        Localizacao other = new Localizacao(1, "rua",
                32.0, 34.5);

        assertEquals(newObject.getDistance(other), 0.0);
    }
}