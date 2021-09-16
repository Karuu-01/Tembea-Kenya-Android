package com.moringaschool.coffeeplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.coffeeplace.R;
import com.moringaschool.coffeeplace.models.Business;
import com.moringaschool.coffeeplace.ui.GalleryDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder> {
    private List<Business> mHotels;
    private Context mContext;

    public GalleryListAdapter(Context context, List<Business> hotels) {
        mContext = context;
        mHotels = hotels;
    }

    public GalleryListAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_list_item, parent, false);
        GalleryViewHolder viewHolder = new  GalleryViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(GalleryListAdapter.GalleryViewHolder holder, int position) {
           holder.bindHotel(mHotels.get(position));
    }

    @Override
    public int getItemCount() {
        return mHotels.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.hotelImageView)
        ImageView mHotelImageView;
        @BindView(R.id.hotelNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindHotel(Business hotel) {
            mNameTextView.setText(hotel.getName());
            mCategoryTextView.setText(hotel.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + hotel.getRating() + "/5");
            Picasso.get().load(hotel.getImageUrl()).into(mHotelImageView);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GalleryDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("hotels", Parcels.wrap(mHotels));
            mContext.startActivity(intent);
        }
    }
}