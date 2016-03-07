package russell.me.posts.api;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;
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
        Log.d("DummyService", "getPosts:" + page);
        final List<FeedItem> list = new ArrayList<>();

        if (page > pages.length) {
            return list;
        }

        InputStream is = context.getResources().openRawResource(pages[page-1]);
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

    @Override
    public void asyncGetPosts(final Category category, final int page, @NonNull final ServiceListener listener) {
        listener.beforeServiceCall();


    }

    public static class GetFeedItemsTask extends AsyncTask<Void, Void, List<FeedItem>> {

        private final Category category;
        private final int page;
        private final Service service;
        private final ServiceListener listener;

        public GetFeedItemsTask(final Context context, final Category category, final int page, final ServiceListener listener) {
            this.category = category;
            this.page = page;
            this.service = ServiceFactory.getService(context);
            this.listener = listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (listener != null) {
                listener.beforeServiceCall();
            }
        }

        @Override
        protected List<FeedItem> doInBackground(final Void... params) {
            return service.getPosts(category, page);
        }

        @Override
        protected void onPostExecute(final List<FeedItem> feedItems) {
            super.onPostExecute(feedItems);
            listener.afterServiceCall(feedItems);
        }
    }
}
