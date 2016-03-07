package com.neerajweb.expandablelistviewtest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.neerajweb.expandablelistviewtest.utils.FilePickUtils;


/**
 * Created by Admin on 02/03/2016.
 */
public class ProfileImageSelect extends Activity {

    // LogCat tag
    private static final String TAG = ProfileImageSelect.class.getSimpleName();

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int PICK_FROM_GALLERY=2;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Uri fileUri; // file url to store image/video
    private String strImagetype="";

    private Button btnCapturePicture, btnRecordVideo,btngallery,btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profile_imageselect);
        this.setFinishOnTouchOutside(false);

        // Changing action bar background color
        // These two lines are not needed
//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.color.action_bar))));

        btnCapturePicture = (Button) findViewById(R.id.btnCapturePicture);
        btnRecordVideo = (Button) findViewById(R.id.btnRecordVideo);
        btngallery= (Button) findViewById(R.id.btngallery);
        btncancel= (Button) findViewById(R.id.btncancel);

        /**
         * Capture image button click event
         */
        btnCapturePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // capture picture
                captureImage();
            }
        });

        /**
         * take image from gallery button click event
         */
        btngallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takegalleryImage();
            }
        });

        /**
         * Record video button click event
         */
        btnRecordVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // record video
                recordVideo();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // cancel operaation
                cancelImage();
            }
        });

            // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * cancelling capture image
     */
    private void cancelImage()
    {
        this.finish();
    }

    /**
     * Launching camera app to capture image
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        try {
            // start the image capture Intent
            startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        }
        catch (ActivityNotFoundException e)
        {
        // Do nothing for now
        }
    }

    /**
     * Launching gallery app to capture image
     */
    private void takegalleryImage() {

        Intent intent;
        if(Build.VERSION.SDK_INT >= 19){
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else{
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");

        startActivityForResult(intent, PICK_FROM_GALLERY);


//        // TODO Auto-generated method stub
//        Intent intent = new Intent();
//        // call android default gallery
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//
////        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        try {
//            startActivityForResult(intent, PICK_FROM_GALLERY);
//
////            startActivityForResult(Intent.createChooser(intent,
////                    "Complete action using"), PICK_FROM_GALLERY);
//
//        } catch (ActivityNotFoundException e) {
//            // Do nothing for now
//        }




//        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        startActivityForResult(intent, REQUEST_CODE_GALLERY_MULTIPLE);

//        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//        startActivityForResult(intent, PICK_FROM_GALLERY);

    }

    /**
     * Launching camera app to record video
     */
    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                strImagetype="CAMERA_IMAGE";

                // successfully captured the image
                // launching upload activity
                launchUploadActivity(true,strImagetype);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                strImagetype="CAMERA_VIDEO";
                // video successfully recorded
                // launching upload activity
                launchUploadActivity(false,strImagetype);

            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == PICK_FROM_GALLERY) {
            try{
                strImagetype="GALLERY_IMAGE";
                Uri uri = data.getData();
                if (uri != null && !uri.toString().isEmpty()) {
                    if(Build.VERSION.SDK_INT >= 19){
                        final int takeFlags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                        //noinspection ResourceType
                        getApplicationContext().getContentResolver()
                                .takePersistableUriPermission(uri, takeFlags);
                    }
//                    String filePath = FilePickUtils.getSmartFilePath(getApplicationContext(), uri);
                    // do what you need with it...
//                    Toast.makeText(getApplicationContext(),
//                        "Enter in PICK_FROM_GALLERY filePath is - " + filePath, Toast.LENGTH_SHORT)
//                            .show();
                    fileUri=uri;
                    launchUploadActivity(true,strImagetype);
                }


//                Uri uri = data.getData();
//                if (uri != null) {
//                    fileUri=uri;
////                    PictureUtils.showGalleryImage(mContext, uri);
//                }
//                Toast.makeText(getApplicationContext(),
//                        "Enter in PICK_FROM_GALLERY fileUri is - " + String.valueOf(fileUri), Toast.LENGTH_SHORT)
//                        .show();
//                launchUploadActivity(true);


//                Bundle extras2 = data.getExtras();
//                Toast.makeText(getApplicationContext(),
//                        "Enter in PICK_FROM_GALLERY - " + String.valueOf(extras2), Toast.LENGTH_SHORT)
//                        .show();
//                if (extras2 != null) {
//                    Toast.makeText(getApplicationContext(),
//                            "Enter in extras2", Toast.LENGTH_SHORT)
//                            .show();
//                    Bitmap photo = extras2.getParcelable("data");
//                    // successfully captured the image
//                    // launching upload activity
//                    launchUploadActivity(true);
//                }

            }
            catch(Exception Ex)
            {
                Toast.makeText(getApplicationContext(),
                        Ex.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void launchUploadActivity(boolean isImage,String strImagetype){
        Intent i = new Intent(ProfileImageSelect.this, ProfileImageUpload.class);
        String filePath = FilePickUtils.getSmartFilePath(getApplicationContext(), fileUri);

        if (strImagetype=="GALLERY_IMAGE")
        {
//            Toast.makeText(getApplicationContext(),
//                    "Enter in PICK_FROM_GALLERY filePath is - " + filePath, Toast.LENGTH_SHORT)
//                    .show();
            i.putExtra("filePath", filePath);
        }else
        {
            i.putExtra("filePath", fileUri.getPath());
        }

        i.putExtra("isImage", isImage);
        startActivity(i);
        finish();
    }

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
}

