package user;

import postable.IPostable;

import java.util.List;
public interface IUser {

    int getId();
    String getProfilePicture();
    void setProfilePicture(String profilePicture);
    String getUsername();
    void setUsername(String username);
    String getName();
    void setName(String name);
    String getBio();
    void setBio(String bio);
    String getLocation();
    void setLocation(String location);
    String getWebsite();
    void setWebsite(String website);
    List<IPostable> getTweets();
    void addTweet(IPostable tweet);
    List<IUser> getFollowing();
    void addFollowing(IUser user);
    void deleteFollowing(IUser user);
    List<IUser> getFollowers();
    void addFollowers(IUser user);
    void deleteFollowers(IUser user);
    List<IPostable> getLikedTweets();
    void addLikedTweet(IPostable tweet);
    void deleteLikedTweet(IPostable tweet);

}
