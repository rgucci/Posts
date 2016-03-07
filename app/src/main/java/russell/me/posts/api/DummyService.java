package russell.me.posts.api;

import android.content.Context;
import com.google.gson.Gson;
import russell.me.posts.R;
import russell.me.posts.json.Results;
import russell.me.posts.model.Category;
import russell.me.posts.model.FeedItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by russell.gutierrez on 6/3/16.
 */
public class DummyService implements Service {

    private static Service instance;

    private final Context context;
    private final Gson gson = new Gson();

    private final int[] pages = {R.raw.posts, R.raw.posts_2};

    private DummyService(final Context context) {
        this.context = context;
    }

    public static synchronized Service getInstance(final Context context) {
        if (instance == null) {
            instance = new DummyService(context);
        }
        return instance;
    }

    @Override
    public List<FeedItem> getPosts(final Category category, final int page) {
        final List<FeedItem> list = new ArrayList<>();

        if (page > pages.length) {
            return list;
        }

        InputStream is = context.getResources().openRawResource(pages[page]);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        Results results = gson.fromJson(br, Results.class);

        for (Results.Data data : results.data) {
            FeedItem feedItem = new FeedItem();
            feedItem.setId(data.id);
            feedItem.setCaption(data.caption);
            feedItem.setImageUrl(data.images.cover);
            list.add(feedItem);
        }
        return list;
    }
}
