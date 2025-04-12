package co.edu.uniquindio.model.vo;

import lombok.*;

/**
 * Representa una ubicación geográfica con su latitud, altitud y radio.
 * Esta clase almacena los valores necesarios para definir la posición
 * y el área de cobertura de una ubicación específica.
 */

@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {
    private float latitud,altitud,radio;

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getAltitud() {
        return altitud;
    }

    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }
}

