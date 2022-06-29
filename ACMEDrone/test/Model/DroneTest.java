package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    @Test
    void setIdentificador() {
        Drone newDrone = new Drone(1, 45.23,
                32.0, null);
        int oldID = newDrone.getIdentificador();

        newDrone.setIdentificador(4);

        assertNotEquals(newDrone.getIdentificador(), oldID);
    }

    @Test
    void setCargaMaxima() {
        Drone newDrone = new Drone(1, 45.23,
                32.0, null);
        double old = newDrone.getCargaMaxima();

        newDrone.setCargaMaxima(4.0);

        assertNotEquals(newDrone.getCargaMaxima(), old);
    }

    @Test
    void setAutonomiaKm() {
        Drone newDrone = new Drone(1, 45.23,
                32.0, null);
        double old = newDrone.getAutonomiaKm();

        newDrone.setAutonomiaKm(4.0);

        assertNotEquals(newDrone.getAutonomiaKm(), old);
    }
}