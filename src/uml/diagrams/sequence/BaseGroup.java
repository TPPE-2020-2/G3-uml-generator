package uml.diagrams.sequence;

import java.util.Collection;
import java.util.function.Predicate;

public abstract class BaseGroup {

    protected <T> T findNode(Collection<T> collection, Predicate<T> filter) {
        return collection
            .stream()
            .filter(filter)
            .findFirst()
            .orElse(null);
    }
}
