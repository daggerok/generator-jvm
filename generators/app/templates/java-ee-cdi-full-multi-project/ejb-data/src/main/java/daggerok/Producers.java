package daggerok;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Producers {

  @Produces
  @PersistenceContext
  private static EntityManager em;
}
