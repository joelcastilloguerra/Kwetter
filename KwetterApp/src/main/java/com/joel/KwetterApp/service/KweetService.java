package com.joel.KwetterApp.service;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.KweetRepo;
import com.joel.KwetterApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KweetService {

    @Autowired
    private KweetRepo kweetRepo;

    @Autowired
    private UserRepo userRepo;

    public void add(Kweet kweet) {

        //Get the full profile of the poster
        User poster = userRepo.getById(kweet.getPoster().getId());

        //Replace the current (id only) profile with the full profile
        kweet.setPoster(poster);

        //Replace all likedBy profiles with their full versions
        for(int i = 0; i < kweet.getLikedBy().size(); i++){

            //Get the full profile from the db
            User liker = userRepo.getById(kweet.getLikedBy().get(i).getId());

            //Replace the current profile with the new one
            kweet.getLikedBy().set(i, liker);

        }

        kweetRepo.save(kweet);

    }


    public void likeKweet(int kweetId, int likerId) {

        Kweet likedKweet = kweetRepo.getById(kweetId);
        User liker = userRepo.getById(likerId);

        likedKweet.addLikedBy(liker);

        kweetRepo.save(likedKweet);

    }

    public List<Kweet> getLatest(int id) {

        return kweetRepo.findTop10ByPosterId(id);

    }


    public void removeKweet(int kweetId) {

        kweetRepo.deleteById(kweetId);

    }

    public List<Kweet> search(String searchString) {

        return kweetRepo.findByContentContaining(searchString);

    }
}
