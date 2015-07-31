package com.expeditionlabs.openinstagram.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expeditionlabs.openinstagram.CustomElements.Post;
import com.expeditionlabs.openinstagram.R;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-07-30.
 */
public class PostAdapter extends ArrayAdapter<Post>{
    public static String TAG = "OpenInstagram_PostAdapter_";

    Context context;
    int resource;
    ArrayList<Post> data = new ArrayList<Post>();

    public PostAdapter(Context context, int resource, ArrayList<Post> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row = convertView;
        PostHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new PostHolder();
            holder.img = (ImageView) row.findViewById(R.id.img);
            holder.textTitle = (TextView) row.findViewById(R.id.textTitle);
            holder.usernameText = (TextView) row.findViewById(R.id.usernameText);
            holder.usernameText2 = (TextView) row.findViewById(R.id.usernameText2);
            holder.textLikes = (TextView) row.findViewById(R.id.textLikes);
            holder.profileImg = (ImageView) row.findViewById(R.id.profileImg);

            holder.likeBtn = (ImageView) row.findViewById(R.id.likeBtn);

            row.setTag(holder);
        }else{
            holder = (PostHolder) row.getTag();
        }

        Post post = data.get(position);
        if(post.getImg() != null){
            holder.img.setImageBitmap(post.getImg());
        }
        holder.textTitle.setText(post.getCaption());
        holder.usernameText.setText(post.getUsername());
        holder.usernameText2.setText(post.getUsername());
        holder.textLikes.setText(String.valueOf(post.getLikes()));
        if(post.getUserProfileImg() != null){
            holder.profileImg.setImageBitmap(post.getUserProfileImg());
        }

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return row;
    }
    static class PostHolder{
        ImageView img;
        TextView textTitle;
        TextView usernameText;
        TextView usernameText2;
        ImageView profileImg;
        TextView textLikes;
        ImageView likeBtn;
    }
}
