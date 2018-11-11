package daggerok.events;

import java.io.Serializable;
import java.util.UUID;

public interface DomainEvent extends Serializable {
  UUID getAggregateId();

  default String getType() {
    return getClass().getSimpleName();
  }
}
