package user;

import postable.IPostable;

import java.util.List;

public class Mod implements IUser {

    private int id;
    private String profilePicture;
    private String username;
    private String name;
    private String bio;
    private String location;
    private String website;
    private List<IUser> following;
    private List<IUser> followers;
    private List<IPostable> likedTweets;
    private List<IPostable> tweets;

    public Mod(int id, String profilePicture, String username, String name, String bio, String location, String website, List<IUser> following, List<IUser> followers, List<IPostable> likedTweets, List<IPostable> tweets) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.following = following;
        this.followers = followers;
        this.likedTweets = likedTweets;
        this.tweets = tweets;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getProfilePicture() {
        return this.profilePicture;
    }

    @Override
    public void setProfilePicture(String profilePicture) {

        this.profilePicture = profilePicture;

    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {

        this.username = username;

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {

        this.name = name;

    }

    @Override
    public String getBio() {
        return this.bio;
    }

    @Override
    public void setBio(String bio) {

        this.bio = bio;

    }

    @Override
    public String getLocation() {

        return this.location;

    }

    @Override
    public void setLocation(String location) {

        this.location = location;

    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public void setWebsite(String website) {

        this.website = website;

    }

    @Override
    public List<IPostable> getTweets() {
        return this.getTweets();
    }

    @Override
    public void addTweet(IPostable tweet) {

        this.tweets.add(tweet);

    }

    @Override
    public List<IUser> getFollowing() {
        return this.following;
    }

    @Override
    public void addFollowing(IUser user) {

        this.following.add(user);

    }

    @Override
    public void deleteFollowing(IUser user) {

        this.following.remove(user);

    }

    @Override
    public List<IUser> getFollowers() {
        return this.followers;
    }

    @Override
    public void addFollowers(IUser user) {

        this.followers.add(user);

    }

    @Override
    public void deleteFollowers(IUser user) {

        this.followers.remove(user);

    }

    @Override
    public List<IPostable> getLikedTweets() {

        return this.likedTweets;

    }

    @Override
    public void addLikedTweet(IPostable tweet) {

        tweet.addLike(this);
        this.likedTweets.add(tweet);

    }

    @Override
    public void deleteLikedTweet(IPostable tweet) {

        this.likedTweets.remove(tweet);
        tweet.deleteLike(this);

    }

    public void deleteTweet(IPostable tweet){

        this.tweets.remove(tweet);

    }

}
