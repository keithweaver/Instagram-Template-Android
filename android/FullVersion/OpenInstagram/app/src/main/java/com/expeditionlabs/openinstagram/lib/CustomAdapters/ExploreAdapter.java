package com.expeditionlabs.openinstagram.lib.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.expeditionlabs.openinstagram.R;
import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramSinglePostActivity;
import com.expeditionlabs.openinstagram.lib.CustomElements.ExplorePost;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-08-26.
 */
public class ExploreAdapter extends ArrayAdapter<ExplorePost> {
    public static final String TAG = "OpenInstagram_ExploreAdapter_";

    Context context;
    int resource;
    ArrayList<ExplorePost> data = new ArrayList<>();

    public ExploreAdapter(Context context, int resource, ArrayList<ExplorePost> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        Log.v(TAG, "...row");
        View row = convertView;
        ExploreHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new ExploreHolder();

            holder.img1 = (ImageView) row.findViewById(R.id.img1);
            holder.img2 = (ImageView) row.findViewById(R.id.img2);
            holder.img3 = (ImageView) row.findViewById(R.id.img3);

            row.setTag(holder);
        }else{
            holder = (ExploreHolder) row.getTag();
        }

        final ExplorePost explorePost = data.get(position);
        if(explorePost.getImg1() != null) {
            holder.img1.setImageBitmap(explorePost.getImg1());
        }
        if(explorePost.getImg2() != null) {
            holder.img2.setImageBitmap(explorePost.getImg2());
        }
        if(explorePost.getImg3() != null) {
            holder.img3.setImageBitmap(explorePost.getImg3());
        }
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInstagramSinglePostActivity.postImgCode = explorePost.getPostCode1();
                Intent openSinglePostWindow = new Intent(v.getContext(),
                        OpenInstagramSinglePostActivity.class);
                v.getContext().startActivity(openSinglePostWindow);
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInstagramSinglePostActivity.postImgCode = explorePost.getPostCode2();
                Intent openSinglePostWindow = new Intent(v.getContext(),
                        OpenInstagramSinglePostActivity.class);
                v.getContext().startActivity(openSinglePostWindow);
            }
        });
        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInstagramSinglePostActivity.postImgCode = explorePost.getPostCode3();
                Intent openSinglePostWindow = new Intent(v.getContext(),
                        OpenInstagramSinglePostActivity.class);
                v.getContext().startActivity(openSinglePostWindow);
            }
        });

        return row;
    }
    static class ExploreHolder{
        ImageView img1;
        ImageView img2;
        ImageView img3;
    }
}
