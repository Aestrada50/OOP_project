package factory;
import model.SpaceObject;
import java.util.Map;

public abstract class SpaceObjectFactory {
    public abstract SpaceObject create(Map<String, String> data);

    public static SpaceObjectFactory getFactory(String type) {
        return switch (type.toUpperCase()) {
            case "DEBRIS" -> new DebrisFactory();
            case "PAYLOAD" -> new PayloadFactory();
            case "ROCKET BODY" -> new RocketBodyFactory();
            default -> new UnknownFactory();
        };
    }
}

