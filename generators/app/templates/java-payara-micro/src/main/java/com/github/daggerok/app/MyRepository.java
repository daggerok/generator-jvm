package com.github.daggerok.app;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static javax.ejb.ConcurrencyManagementType.BEAN;

@Log
@Startup
@Singleton
@ApplicationScoped
@ConcurrencyManagement(BEAN)
public class MyRepository {

  private Map<UUID, String> db;

  @PostConstruct
  public void init() {
    db = new ConcurrentHashMap<>();
    log.info("database initialized.");
  }

  public Collection<String> getStrings() {
    return Collections.unmodifiableCollection(db.values());
  }

  public void addString(final String string) {
    db.put(UUID.randomUUID(), string);
  }
}
