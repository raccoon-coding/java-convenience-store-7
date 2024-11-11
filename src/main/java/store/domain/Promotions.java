package store.domain;

import store.exception.InvalidPromotion;

import java.util.List;
import java.util.Optional;

public class Promotions {
    private final List<Promotion> promtions;

    public Promotions(List<Promotion> promtions) {
        this.promtions = promtions;
    }

    public Promotion findByName(String name) {
        Optional<Promotion> optional = promtions.stream().filter(promotion -> promotion.getName().equals(name)).findFirst();
        return verifyObject(optional);
    }

    private Promotion verifyObject(Optional<Promotion> optional) {
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
