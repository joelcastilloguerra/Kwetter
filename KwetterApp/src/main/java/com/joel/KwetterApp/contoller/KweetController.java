package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.KweetService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kweet")
public class KweetController {

    @Autowired
    private KweetService kweetService;

    @RequestMapping(method= RequestMethod.POST, value = "/add")
    public void add(@RequestBody Kweet kweet){

        kweetService.add(kweet);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchString}")
    public List<Kweet> search(@PathVariable (value = "searchString") String searchString){

        return kweetService.search(searchString);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/like/{kweetId}/{likerId}")
    public void likeKweet(@PathVariable (value = "kweetId") int kweetId, @PathVariable (value = "likerId") int likerId){

        kweetService.likeKweet(kweetId, likerId);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/unLike/{kweetId}/{likerId}")
    public void unLikeKweet(@PathVariable (value = "kweetId") int kweetId, @PathVariable (value = "likerId") int likerId){

        kweetService.unLikeKweet(kweetId, likerId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLatest/{userId}")
    public List<Kweet> getLatest(@PathVariable (value = "userId") int userId){

        return kweetService.getLatest(userId);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{kweetId}")
    public void removeKweet(@PathVariable (value = "kweetId") int kweetId){

        kweetService.removeKweet(kweetId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTimeline/{userId}")
    public List<Kweet> getUserTimeline(@PathVariable (value = "userId") int userId){

        return kweetService.getUserTimeline(userId);

    }

    @RequestMapping(value = "/liked/{userId}/{kweetId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean ifUserLikedKweet(@PathVariable(value = "userId") int userId, @PathVariable(value = "kweetId") int kweetId){

        return kweetService.ifUserLikedKweet(userId, kweetId);

    }


}
