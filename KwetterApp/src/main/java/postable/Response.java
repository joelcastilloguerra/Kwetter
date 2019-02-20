package postable;

import user.IUser;

import java.util.Date;
import java.util.List;

public class Response implements IPostable{

    private int id;
    private String content;
    private Date date;
    private List<IUser> likedBy;
    private IUser poster;
    private IPostable responseTo;

    public Response(String content, Date date, List<IUser> likedBy, IUser poster, IPostable responseTo) {
        this.content = content;
        this.date = date;
        this.likedBy = likedBy;
        this.poster = poster;
        this.responseTo = responseTo;
    }

    public Response(int id, String content, Date date, List<IUser> likedBy, IUser poster, IPostable responseTo) {
        this.content = content;
        this.date = date;
        this.likedBy = likedBy;
        this.poster = poster;
        this.responseTo = responseTo;
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

    public IPostable getResponseTo(){

        return this.responseTo;

    }



}
