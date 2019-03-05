package com.joel.KwetterApp.repo;

import com.joel.KwetterApp.model.Kweet;
import org.springframework.data.repository.CrudRepository;

public interface KweetRepo extends CrudRepository<Kweet, Integer> {
}
