package postable.tweet;

import postable.IPostable;
import user.IUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Tweet implements IPostable {

    private int id;
    private String content;
    private Date date;
    private List<IUser> likedBy;
    private IUser poster;

    public Tweet(String content, Date date, List<IUser> likedBy, IUser poster) {
        this.content = content;
        this.date = date;
        this.likedBy = likedBy;
        this.poster = poster;
    }

    public Tweet(int id, String content, Date date, List<IUser> likedBy, IUser poster) {

        this.id = id;
        this.content = content;
        this.date = date;
        this.likedBy = likedBy;
        this.poster = poster;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public List<IUser> getsLikedBy() {
        return this.likedBy;
    }

    @Override
    public void addLike(IUser user) {

        this.likedBy.add(user);

    }

    @Override
    public void deleteLike(IUser user) {

        this.likedBy.remove(user);

    }

    @Override
    public int getId() {
        return this.id;
    }

}
