package com.cos.retrofit2movieapp.model;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.cos.retrofit2movieapp.R;
import com.google.gson.annotations.SerializedName;
import com.makeramen.roundedimageview.RoundedImageView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private static final String TAG = "Movie";
    private long id;
    private String url;
    private String title;
    private long year;
    private double rating;
    private long runtime;
    private String summary;
    @SerializedName("medium_cover_image")
    private String mediumCoverImage;

    @BindingAdapter("mediumCoverImage")
    public static void loadImage(RoundedImageView view, String mediumCoverImage){
        Log.d(TAG, "loadImage: called ");
        Glide.with(view.getContext())
            .load(mediumCoverImage)
            .into(view);
    }
}
