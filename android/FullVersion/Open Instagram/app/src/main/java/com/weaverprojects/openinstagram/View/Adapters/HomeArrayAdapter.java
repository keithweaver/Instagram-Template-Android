package com.weaverprojects.openinstagram.View.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weaverprojects.openinstagram.Controller.image.CircleTransform;
import com.weaverprojects.openinstagram.Model.Comment;
import com.weaverprojects.openinstagram.Model.Post;
import com.weaverprojects.openinstagram.R;

import java.util.ArrayList;

/**
 * Created by kweaver on 18/12/15.
 */
public class HomeArrayAdapter extends ArrayAdapter<Post> {
    Context context;
    int layoutResourceId;
    ArrayList<Post> data = new ArrayList<Post>();

    public HomeArrayAdapter(Context context, int layoutResourceId, ArrayList<Post> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();

            holder.profileImageView = (ImageView) row.findViewById(R.id.profileImageView);
            holder.userNameTextView = (TextView) row.findViewById(R.id.userNameTextView);
            holder.followTextView = (TextView) row.findViewById(R.id.followTextView);
            holder.sponsoredTextView = (TextView) row.findViewById(R.id.sponsoredTextView);
            holder.mainImageView = (ImageView) row.findViewById(R.id.mainImageView);
            holder.heartImageView = (ImageView) row.findViewById(R.id.heartImageView);
            holder.commentImageView = (ImageView) row.findViewById(R.id.commentImageView);
            holder.likesTextView = (TextView) row.findViewById(R.id.likesTextView);
            holder.captionTextView = (TextView) row.findViewById(R.id.captionTextView);
            holder.commentTextView = (TextView) row.findViewById(R.id.commentTextView);
            holder.addCommentTextView = (TextView) row.findViewById(R.id.addCommentTextView);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        Post singlePost = data.get(position);

        Picasso.with(context).load(singlePost.getProfileUrl()).transform(new CircleTransform()).into(holder.profileImageView);

        holder.userNameTextView.setText(singlePost.getUserName());
        if(singlePost.isFollowing()){
           holder.followTextView.setVisibility(View.GONE);
        }
        if(!singlePost.isSponsored()){
            holder.sponsoredTextView.setVisibility(View.GONE);
        }

        Picasso.with(context).load(singlePost.getImgUrl()).into(holder.mainImageView);

        holder.captionTextView.setText(singlePost.getUserName() + " - " + singlePost.getCaption());
        holder.likesTextView.setText(String.valueOf(singlePost.getLikes()));
        String commentsStr = "";
        ArrayList<Comment> listOfComments = singlePost.getComments();

        for(int i = 0;i < listOfComments.size();i++){//Wasnt accepting for each
            Comment singleComment = listOfComments.get(i);
            if(commentsStr.length() > 0){
                commentsStr += "\n";
            }
            commentsStr += singleComment.getUserName() + " " + singleComment.getComment();
        }
        holder.commentTextView.setText(commentsStr);



        holder.heartImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Log.v("BTN1 WAS CLICKED:", "" + position);
                //Toast.makeText(context, "Btn1 Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        holder.commentImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Log.v("BTN2 WAS CLICKED",""+position);
                //Toast.makeText(context, "Btn2 Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        holder.addCommentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return row;
    }
    static class UserHolder{
        ImageView profileImageView;
        TextView userNameTextView;
        TextView followTextView;
        TextView sponsoredTextView;
        ImageView mainImageView;
        ImageView heartImageView;
        ImageView commentImageView;
        TextView likesTextView;
        TextView captionTextView;
        TextView commentTextView;
        TextView addCommentTextView;
    }
}
