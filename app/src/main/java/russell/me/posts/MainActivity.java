package russell.me.posts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import russell.me.posts.api.DummyService;
import russell.me.posts.api.Service;
import russell.me.posts.api.ServiceFactory;
import russell.me.posts.api.ServiceListener;
import russell.me.posts.model.Category;
import russell.me.posts.model.FeedItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPosts;
    private FeedItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPosts = (RecyclerView) findViewById(R.id.recylcerViewPosts);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPosts.setLayoutManager(layoutManager);

//        final Service service = ServiceFactory.getService(this);
//        listFeedItems = service.getPosts(Category.Hot, 1);

        new DummyService.GetFeedItemsTask(this, Category.Hot, 1, new ServiceListener() {
            @Override
            public void beforeServiceCall() {

            }

            @Override
            public void afterServiceCall(final List<FeedItem> feedItems) {
                adapter = new FeedItemRecyclerAdapter(MainActivity.this, feedItems);
                recyclerViewPosts.setAdapter(adapter);
            }
        }).execute();

        recyclerViewPosts.addOnScrollListener(new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(final int current_page) {
                new DummyService.GetFeedItemsTask(MainActivity.this, Category.Hot, current_page, new ServiceListener() {
                    @Override
                    public void beforeServiceCall() {

                    }

                    @Override
                    public void afterServiceCall(final List<FeedItem> feedItems) {
                        adapter.addFeedItems(feedItems);
                    }
                }).execute();
            }
        });
    }
}
