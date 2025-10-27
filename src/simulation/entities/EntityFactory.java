package simulation.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class EntityFactory {
    Entity nothingInstance = new Nothing();

    public Entity createEntity(EntityType entityType) {
        if(entityType.equals(EntityType.NOTHING)){
            return nothingInstance;
        }
        try {
            Constructor<?> constructor = entityType.getEntityClass().getConstructor();
            return (Entity) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
