package russell.me.posts.api;

import android.content.Context;

/**
 * Created by russell.gutierrez on 6/3/16.
 */
public class ServiceFactory {

    public static Service getService(final Context context) {
        return DummyService.getInstance(context);
    }
}
