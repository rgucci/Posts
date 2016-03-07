package russell.me.posts.api;

import russell.me.posts.model.Category;
import russell.me.posts.model.FeedItem;

import java.util.List;

/**
 * Created by russell.gutierrez on 6/3/16.
 */
public interface Service {
    public List<FeedItem> getPosts(Category category, int page);
    public void asyncGetPosts(Category category, int page, ServiceListener listener);
}
