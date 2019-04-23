package com.joel.KwetterApp.repo;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KweetRepo extends CrudRepository<Kweet, Integer> {

    Kweet getById(Integer id);

    List<Kweet> findTop10ByPosterId(int id);

    List<Kweet> findByContentContaining(String searchString);

    List<Kweet> findByPosterId(int id);

}
