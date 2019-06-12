package com.joel.KwetterApp.repo;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KweetRepo extends CrudRepository<Kweet, Integer> {

    Kweet getById(Integer id);

    List<Kweet> findTop10ByPosterId(int id);

    List<Kweet> findByContentContaining(String searchString);
    List<Kweet> findByPosterUsernameContainingOrContentContaining(String searchString, String searchString2);

    List<Kweet> findByPosterId(int id);

}
