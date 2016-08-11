package ca.keithweaver.openinstagram.View.Windows.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.Main.Post.PostActivity;

/**
 * Created by keithweaver on 16-07-19.
 */
public class PostFragment extends Fragment {
    protected Activity mActivity;
    private static final String TAG = PostFragment.class.getSimpleName();

    int PICK_PHOTO_FOR_AVATAR = 1;

    ImageView mainImageView;

    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;

    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        view = declareUIObjects(view);



        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        openPostWindow();
    }
    protected void openPostWindow(){
        Intent openPostWindow = new Intent(mActivity, PostActivity.class);
        mActivity.startActivity(openPostWindow);
    }



    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = mActivity.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    protected View declareUIObjects(View view){
        return view;
    }
}
