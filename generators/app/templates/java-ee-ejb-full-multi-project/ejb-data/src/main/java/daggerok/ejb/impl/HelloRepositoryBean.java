package daggerok.ejb.impl;

import daggerok.ejb.api.HelloRepository;
import daggerok.entity.Hello;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class HelloRepositoryBean implements HelloRepository {

  @PersistenceContext
  EntityManager em;

  @Override
  public Hello save(final Hello hello) {
    return em.merge(hello);
  }

  @Override
  public void delete(final Hello hello) {
    em.remove(em.merge(hello));
  }

  @Override
  public void delete(final Long id) {
    delete(findById(id));
  }

  @Override
  public List<Hello> findAll() {
    return em.createQuery("SELECT h from Hello h", Hello.class)
             .getResultList();
  }

  @Override
  public Hello findById(final Long id) {
    return em.find(Hello.class, id);
  }

  @Override
  public Hello findOne(final Long id) {
    return findById(id);
  }
}
