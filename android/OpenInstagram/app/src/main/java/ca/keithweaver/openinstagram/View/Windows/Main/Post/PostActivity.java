package ca.keithweaver.openinstagram.View.Windows.Main.Post;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.Main.MainActivity;

/**
 * Created by keithweaver on 16-08-11.
 *
 * Taken from https://github.com/kweaver00/Android-Samples/tree/master/Camera/CameraExampleHDImages
 */
public class PostActivity extends AppCompatActivity {

    ImageView mainImageView;
    Button nextBtn;
    Button takePhotoBtn;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Uri mImageUri;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_post);

        mainImageView = (ImageView) findViewById(R.id.mainImageView);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        takePhotoBtn = (Button) findViewById(R.id.takePhotoBtn);

        handlePermissions();
    }
    protected void handlePermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.v("WEAVER_", "higher than 23");
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
            int hasStoragePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED && hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
                Log.v("WEAVER_", "Does not have permission");
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
        handleButtonClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Accepted
                    handleButtonClick();
                } else {
                    // Denied
                    Toast.makeText(PostActivity.this, "We don't have camera permissions", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    protected void handleButtonClick() {
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takeAndSavePhoto();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNext = new Intent(PostActivity.this, PostInfoActivity.class);
                finish();
                startActivity(openNext);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //... some code to inflate/create/find appropriate ImageView to place grabbed image
            this.grabImage(mainImageView);
        } else {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mainImageView.setImageBitmap(imageBitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
    public void grabImage(ImageView imageView) {
        this.getContentResolver().notifyChange(mImageUri, null);
        ContentResolver cr = this.getContentResolver();
        Bitmap bitmap;
        try {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("WEAVER_", "Failed to load", e);
        }
    }

    protected void takeAndSavePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                mImageUri = Uri.fromFile(photoFile);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(PostActivity.this, "Couldnt create image.\n Maybe missing permissions to write to external storage.", Toast.LENGTH_SHORT).show();

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
