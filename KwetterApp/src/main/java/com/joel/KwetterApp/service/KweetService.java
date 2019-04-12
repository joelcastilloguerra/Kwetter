package com.joel.KwetterApp.service;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.KweetRepo;
import com.joel.KwetterApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KweetService {

    @Autowired
    private KweetRepo kweetRepo;

    @Autowired
    private UserRepo userRepo;

    public void add(Kweet kweet) {

        //Get the full profile of the poster
        User poster = userRepo.getById(kweet.getPoster());

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

    public List<Kweet> getUserTimeline(int userId) {

        List<Kweet> timeline = new ArrayList<>();

        //Get the full profile of the user
        User user = userRepo.getById(userId);

        for(int i = 0; i < user.getFollowing().size(); i++){

            timeline.addAll(kweetRepo.findByPosterId(user.getFollowing().get(i)));

        }

        timeline.addAll(kweetRepo.findByPosterId(userId));

        Collections.sort(timeline);

        Collections.reverse(timeline);

        return timeline;

    }

    public Boolean ifUserLikedKweet(int userId, int kweetId) {

        Kweet kweet = kweetRepo.getById(kweetId);

        for(int i = 0 ; i < kweet.getLikedBy().size() ; i++){

            if(kweet.getLikedBy().get(i).getId() == userId){

                return true;

            }

        }

        return false;

    }

    public void unLikeKweet(int kweetId, int likerId) {

        Kweet unLikedKweet = kweetRepo.getById(kweetId);
        User unLiker = userRepo.getById(likerId);

        unLikedKweet.removeLikedBy(unLiker);

        kweetRepo.save(unLikedKweet);

    }
}
