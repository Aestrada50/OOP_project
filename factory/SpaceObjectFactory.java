/**
 * The {@code SpaceObjectFactory} class is an abstract factory for creating instances of {@code SpaceObject}.
 * It provides a method to create space objects based on a data map and a static method to retrieve
 * specific factory instances based on the type of space object.
 */
package factory;

import java.util.Map;
import model.SpaceObject;

public abstract class SpaceObjectFactory {

    /**
     * Creates a new {@code SpaceObject} using the provided data map.
     * This method must be implemented by subclasses to create specific types of space objects.
     *
     * @param data A map containing the data required to create a {@code SpaceObject}.
     * @return A new instance of {@code SpaceObject}.
     */
    public abstract SpaceObject create(Map<String, String> data);

    /**
     * Retrieves a specific factory instance based on the type of space object.
     *
     * @param type The type of space object (e.g., "DEBRIS", "PAYLOAD", "ROCKET BODY").
     * @return A factory instance for creating the specified type of space object.
     */
    public static SpaceObjectFactory getFactory(String type) {
        return switch (type.toUpperCase()) {
            case "DEBRIS" -> new DebrisFactory();
            case "PAYLOAD" -> new PayloadFactory();
            case "ROCKET BODY" -> new RocketBodyFactory();
            default -> new UnknownFactory();
        };
    }
}

