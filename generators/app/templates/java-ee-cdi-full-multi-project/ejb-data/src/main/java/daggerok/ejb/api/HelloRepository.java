package daggerok.ejb.api;

import daggerok.entity.Hello;

import javax.ejb.Local;
import java.util.List;
import java.util.UUID;

@Local
public interface HelloRepository {

  Hello save(final Hello hello);

  void delete(final Hello hello);

  void delete(final Long id);

  Hello findById(final Long id);

  Hello findOne(final Long id);

  List<Hello> findAll();
}
