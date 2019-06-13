package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.Kweet;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.KweetService;
import com.joel.KwetterApp.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Scope("request")
@RequestMapping("/kweet")
public class KweetController {

    @Autowired
    private KweetService kweetService;

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, value = "/add")
    public void add(HttpServletRequest req, @RequestBody Kweet kweet){

        User user = userService.whoami(req);
        kweet.setPoster(user);
        kweetService.add(kweet);

    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
    @ResponseBody
    public List<Kweet> search(@PathVariable(value = "searchString") String searchString){

        List<Kweet> foundKweets = kweetService.search(searchString);

        return foundKweets;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/like/{kweetId}/{likerId}")
    public void likeKweet(@PathVariable (value = "kweetId") int kweetId, @PathVariable (value = "likerId") int likerId){

        kweetService.likeKweet(kweetId, likerId);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/unLike/{kweetId}/{likerId}")
    public void unLikeKweet(@PathVariable (value = "kweetId") int kweetId, @PathVariable (value = "likerId") int likerId){

        kweetService.unLikeKweet(kweetId, likerId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLatest/{id}")
    public List<Kweet> getLatest(@PathVariable (value = "id") int id){

        return kweetService.getLatest(id);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{kweetId}")
    public void removeKweet(@PathVariable (value = "kweetId") int kweetId){

        kweetService.removeKweet(kweetId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTimeline")
    public List<Kweet> getUserTimeline(HttpServletRequest req){

        User user = userService.whoami(req);

        return kweetService.getUserTimeline(user.getId());

    }

    @RequestMapping(value = "/liked/{kweetId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean ifUserLikedKweet(HttpServletRequest req, @PathVariable(value = "kweetId") int kweetId){

        User user = userService.whoami(req);

        return kweetService.ifUserLikedKweet(user.getId(), kweetId);

    }


}
