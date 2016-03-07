package russell.me.posts.model;

/**
 * Created by russell.gutierrez on 6/3/16.
 */
public class FeedItem {

    private String id;
    private String caption;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
