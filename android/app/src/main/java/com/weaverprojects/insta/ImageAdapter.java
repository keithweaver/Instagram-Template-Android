package com.weaverprojects.insta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kweaver on 20/07/15.
 */
public class ImageAdapter extends ArrayAdapter<UserPost> {
    public static String TAG = "WEAVER_ImageAdapter_";

    Context context;
    int resource;
    ArrayList<UserPost> data = new ArrayList<UserPost>();

    public ImageAdapter(Context context, int resource, ArrayList<UserPost> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.v(TAG, "Row");

        View row = convertView;
        UserHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new UserHolder();

            holder.img = (ImageView) row.findViewById(R.id.img);
            holder.textTitle = (TextView) row.findViewById(R.id.textTitle);
            holder.textAbout = (TextView) row.findViewById(R.id.textAbout);
            holder.textLikes = (TextView) row.findViewById(R.id.textLikes);

            holder.likeBtn = (ImageView) row.findViewById(R.id.likeBtn);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }

        UserPost post = data.get(position);
        Bitmap temp = post.getImg();
        if(temp == null){
            Log.v(TAG, "Bitmap is still nulllll....");
        }
        holder.img.setImageBitmap(temp);
        holder.textTitle.setText(post.getTitle());
        holder.textAbout.setText(post.getAbout());
        holder.textLikes.setText(post.getLikes());

        //final UserHolder finalHolder = holder;
        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("BTN1 WAS CLICKED:", ""+position);
                //Toast.makeText(context, "Btn1 Clicked", Toast.LENGTH_SHORT).show();
                //finalHolder.likeBtn.setImageDrawable(R.drawable.abc_btn_check_to_on_mtrl_015);
                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                //send liked action
            }
        });



        return row;
    }
    static class UserHolder{
        ImageView img;
        TextView textTitle;
        TextView textAbout;
        TextView textLikes;
        ImageView likeBtn;
    }
}