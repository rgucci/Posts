package russell.me.posts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import russell.me.posts.model.FeedItem;

import java.util.List;

/**
 * Created by russell.gutierrez on 7/3/16.
 */
public class FeedItemRecyclerAdapter extends RecyclerView.Adapter<FeedItemRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final List<FeedItem> feedItems;

    public FeedItemRecyclerAdapter(Context context, List<FeedItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FeedItem feedItem = feedItems.get(position);
        holder.captionTextView.setText(feedItem.getCaption());
        Glide.with(context).load(feedItem.getImageUrl())
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void addFeedItems(List<FeedItem> newItems) {
        feedItems.addAll(newItems);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView captionTextView;
        private final ImageView thumbnailImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.captionTextView = (TextView) itemView.findViewById(R.id.textViewCaption);
            this.thumbnailImageView = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
        }
    }

}
