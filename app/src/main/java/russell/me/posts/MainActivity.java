package russell.me.posts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import russell.me.posts.api.Service;
import russell.me.posts.api.ServiceFactory;
import russell.me.posts.model.Category;
import russell.me.posts.model.FeedItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FeedItem> listFeedItems;
    private RecyclerView recyclerViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPosts = (RecyclerView) findViewById(R.id.recylcerViewPosts);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPosts.setLayoutManager(layoutManager);

        final Service service = ServiceFactory.getService(this);
        listFeedItems = service.getPosts(Category.Hot, 1);

        final FeedItemRecyclerAdapter adapter = new FeedItemRecyclerAdapter(this, listFeedItems);
        recyclerViewPosts.setAdapter(adapter);

        recyclerViewPosts.addOnScrollListener(new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(final int current_page) {
                final List<FeedItem> feedItems = service.getPosts(Category.Hot, current_page);
                if (feedItems.size() > 0) {
                    adapter.addFeedItems(feedItems);
                }
            }
        });
    }
}
