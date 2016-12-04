package com.surverymonkey.githubemojis.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.surverymonkey.githubemojis.R;
import com.surverymonkey.githubemojis.activities.DetailsActivity;
import com.surverymonkey.githubemojis.models.Emoji;

import java.util.List;

/**
 * Created by aditi on 12/2/2016.
 */

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.VH> {

    private Activity mContext;
    private List<Emoji> mEmojis;

    public EmojiAdapter(Activity context, List<Emoji> emojis) {
        mContext = context;
        if (emojis == null) {
            throw new IllegalArgumentException("emojis must not be null");
        }
        mEmojis = emojis;
    }

    // Inflate the view based on the viewType provided.
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emoji, parent, false);
        return new VH(itemView, mContext);
    }

    // Display data at the specified position
    @Override
    public void onBindViewHolder(final VH holder, int position) {
        Emoji emoji = mEmojis.get(position);
        holder.ivProfile.setTag(emoji);
        Picasso.with(mContext).load(emoji.getmThumbnailImage()).into(holder.ivProfile);
    }

    @Override
    public int getItemCount() {
        return mEmojis.size();
    }

    // Provide a reference to the views for each contact item
    public final static class VH extends RecyclerView.ViewHolder {
        final ImageView ivProfile;

        public VH(View itemView, final Context context) {
            super(itemView);
            ivProfile = (ImageView)itemView.findViewById(R.id.ivProfile);

            // Navigate to contact details activity on click of card view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Emoji emoji = (Emoji) v.getTag();
                    if (emoji != null) {
                        // Fire an intent when a contact is selected
                        Intent i = new Intent(context, DetailsActivity.class);
                        // Pass contact object in the bundle and populate details activity.
                        i.putExtra("emo_name", emoji.getmName());
                        i.putExtra("emo_img", emoji.getmThumbnailImage());

                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((Activity) context, (View)ivProfile, "image");
                        context.startActivity(i, options.toBundle());
                    }
                }
            });
        }
    }
}
