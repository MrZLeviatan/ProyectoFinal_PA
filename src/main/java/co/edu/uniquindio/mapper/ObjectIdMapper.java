package co.edu.uniquindio.mapper;

import org.bson.types.ObjectId;

public class ObjectIdMapper {
    // Mapea ObjectId a String
    public static String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    // Mapea String a ObjectId
    public static ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null;
    }
}
