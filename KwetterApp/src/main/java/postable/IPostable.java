package postable;

import user.IUser;

import java.util.Date;
import java.util.List;

public interface IPostable {

    String getContent();
    Date getDate();
    List<IUser> getsLikedBy();
    void addLike(IUser user);
    void deleteLike(IUser user);
    int getId();

}
