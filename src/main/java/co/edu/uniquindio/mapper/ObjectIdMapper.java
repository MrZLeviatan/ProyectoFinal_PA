package co.edu.uniquindio.mapper;

import org.bson.types.ObjectId;

/**
 * Clase auxiliar que proporciona métodos para mapear entre tipos `ObjectId` de MongoDB
 * y el tipo `String`, utilizados comúnmente para la manipulación de identificadores de objetos.
 */
public class ObjectIdMapper {

    /**
     * Mapea un objeto `ObjectId` a un `String`.
     *
     * @param value El `ObjectId` a convertir.
     * @return El valor de tipo `String` que representa el `ObjectId`, o `null` si el valor es `null`.
     */
    public static String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    /**
     * Mapea un `String` a un `ObjectId`.
     *
     * @param value El `String` que representa un `ObjectId`.
     * @return El objeto `ObjectId` creado a partir del `String`, o `null` si el valor es `null`.
     */
    public static ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null;
    }
}
