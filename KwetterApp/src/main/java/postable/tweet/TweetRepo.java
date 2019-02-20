package postable.tweet;

import org.springframework.data.repository.CrudRepository;

public interface TweetRepo extends CrudRepository<Tweet, Integer> {
}
