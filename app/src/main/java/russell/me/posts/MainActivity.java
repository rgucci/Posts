package russell.me.posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        final Service service = ServiceFactory.getService(this);
        listFeedItems = service.getPosts(Category.Hot, 0);

        final FeedItemRecyclerAdapter adapter = new FeedItemRecyclerAdapter(this, listFeedItems);
        recyclerViewPosts.setAdapter(adapter);

        adapter.addFeedItems(service.getPosts(Category.Hot, 1));
    }
}
