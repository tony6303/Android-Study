package com.example.mysharedmovie;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private static final String TAG = "MovieAdapter";
    private final List<Integer> movies;
    private float ratingSaved;

    public MovieAdapter(List<Integer> movies) {
        this.movies = movies;
    }


    // 7번 getView의 역할을 하는녀석
    // 차이점 : ListView 에서는  최초 로딩시에 화면에 나오는 개수만큼만 로딩함. (최초화면에 6개가 필요하다면 getView가 6번 호출됨)
    // 그 다음 스크롤해서 추가로 필요하면 개수 만큼 다시 getView 를 호출함
    // ViewHolder 의 특징
    // onCreateViewHolder 는 해당 액티비티 실행 시에만 호출됨
    // 그 다음 스크롤해서 추가로 필요하면 onBindViewHolder 를 호출함
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // mainActivity에 연결됨 ( RecyclerActivity )
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view); // ViewHolder에 view를 꼽아서 반환함 ~
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // MyViewHolder 를 제일 먼저 만들어야함!!!!!!!!!!!!!!!!!!!
    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_item);

            ivItem.setOnClickListener(v -> {
                //                    MainActivity.this
                View dialog = v.inflate(v.getContext(), R.layout.dialog_item, null);
                ImageView ivItem = dialog.findViewById(R.id.iv_item);
                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                TextView tvRating = dialog.findViewById(R.id.rating);
                int pos = getAdapterPosition();

                SharedPreferences preferences = v.getContext().getSharedPreferences("pref", MainActivity.MODE_PRIVATE);
                ratingBar.setRating(preferences.getFloat("rating"+pos, 0));
                tvRating.setText(preferences.getFloat("rating"+pos, 0)+"");

                ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                    tvRating.setText(rating + "");
                    ratingSaved = rating;
                });

                ivItem.setImageResource(movies.get(pos));
                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                dlg.setTitle("큰 포스터");
                dlg.setIcon(R.drawable.movie_icon);
                dlg.setView(dialog);
                dlg.setNegativeButton("닫기", null);
                dlg.setPositiveButton("확인", (dialog1, which) -> {
                    // v.getContext() = MainActivity
                    //SharedPreferences preferences = v.getContext().getSharedPreferences("pref", MainActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putFloat("rating"+pos, ratingSaved);
                    editor.commit();
                });
                dlg.show();
            });
        }

        public void setItem(Integer i) {
            ivItem.setImageResource(i);
        }


    }


}
