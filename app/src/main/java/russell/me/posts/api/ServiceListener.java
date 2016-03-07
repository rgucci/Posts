package russell.me.posts.api;

import russell.me.posts.json.Results;
import russell.me.posts.model.FeedItem;

import java.util.List;

/**
 * Created by russell.gutierrez on 7/3/16.
 */
public interface ServiceListener {
    void beforeServiceCall();
    void afterServiceCall(List<FeedItem> feedItems);
}
