package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private static final String TAG = "ItemAdapter";
    //List<String> movies = new ArrayList<>();
    private final List<Movie> movies;

    public ItemAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    //우리가 직접실행 시킬 수는 없고, 알아서 콜백되는 함수들
    // size
    @Override
    public int getCount() {
        return movies.size();
    }

    // 클릭하거나 어떤 이벤트 발생시에 필요
    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    // 처음에, 화면에 보이는 만큼만 그림을 그림
    // 객체를 생성해서 그림을 그리는 함수
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "getView position: " + position);
        // 1. 부모 컨텍스트 가져오기
        // Context 는 모든 Activity 들의 부모이다.
        Context context = parent.getContext();  // parent = ListView

        //그래서 완전하게 Activity를 사용하려면 다운캐스팅을 해줘야 한다.
        MainActivity mainActivityContext = (MainActivity) parent.getContext();

        // 2. 인플레이터 객체 생성 완료
        // 인플레이터가 어느 객체에 연결 되어있는지 반드시 알려줘야 함 ( parent 를 가져올때 알아서 처리 됨 )
        // 인플레이터 객체 = xml을 자바 객체로 만들어주는 도구

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        convertView = inflater.inflate(R.layout.list_item, parent, false);

        //view 의 id를 찾음 ( . 연산 )
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvSubTitle = convertView.findViewById(R.id.tv_subtitle);

        // 데이터 바인딩
        tvTitle.setText(movies.get(position).getTitle());
        tvSubTitle.setText(movies.get(position).getSubTitle());

        // inflate 된 view에 Listener(이벤트) 를 걸어줘야함
        convertView.setOnClickListener(v -> {
            //Toast.makeText(context, "클림됨"+position, Toast.LENGTH_SHORT).show();
            // 원래 이 클래스는 Activity가 아니라서 start 할 수 없지만 context가 -> parent.getContext 했기 때문에 가능

            // Intent( 현재 내화면, 이동할 화면 )
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", movies.get(position).getTitle());
            context.startActivity(intent);
        });

        convertView.setOnLongClickListener(v -> {
            movies.remove(position);
            this.notifyDataSetChanged(); //화면 갱신   , 함수는 baseAdapter 가 갖고있음
            return true;
        });

        return convertView;
    }
}
