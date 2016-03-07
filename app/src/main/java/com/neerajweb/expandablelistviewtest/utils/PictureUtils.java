package com.neerajweb.expandablelistviewtest.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.neerajweb.expandablelistviewtest.R;

public class PictureUtils {

	public static final int REQUEST_CODE_CAMERA = 1;
	public static final int REQUEST_CODE_GALLERY = 2;
	public static final int REQUEST_CODE_GALLERY_MULTIPLE = 3;

	public static final String APP_DIR_MAIN = "Sendfie/images/";
	public static final String FILE_NAME = "pic_temp.png";

	public static void openPictureDialog(final FragmentActivity context, final int tag) {
		final Dialog pictureDialog = new Dialog(context);
		pictureDialog.setCanceledOnTouchOutside(true);
		pictureDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//		pictureDialog.setContentView(R.layout.layout_profile_image_popup);
//		TextView captureCameraBtn = (TextView) pictureDialog.findViewById(R.id.takePhotoButton);
//		captureCameraBtn.setTypeface(SendfieApp.getHelveticaRegular());
//
//		captureCameraBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				openCamera(context);
//				pictureDialog.dismiss();
//			}
//		});
//
//		TextView addFromGallery = (TextView) pictureDialog.findViewById(R.id.uploadPhotoButton);
//		addFromGallery.setTypeface(SendfieApp.getHelveticaRegular());
//		addFromGallery.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
////				if(tag == Constants.NewsFeed.MULTIPLE_GALLERY_IMAGE)
////					openGalleryForMultiple(context);
////				else
//				openGallery(context);
//				pictureDialog.dismiss();
//			}
//		});
//
//		TextView cancelButton = (TextView) pictureDialog.findViewById(R.id.cancelBtn);
//		cancelButton.setTypeface(SendfieApp.getHelveticaRegular());
//		cancelButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				pictureDialog.dismiss();
//			}
//		});
//
//		pictureDialog.show();

	}



	public static Bitmap getRoundedShape(Bitmap bitmap) {
		if (bitmap != null) {
			Bitmap targetBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(targetBitmap);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);

			Path path = new Path();
			path.addCircle(((float) bitmap.getWidth() - 1) / 2,((float) bitmap.getHeight() - 1) / 2,
					(Math.min(((float) bitmap.getWidth()),((float) bitmap.getHeight())) / 2),Path.Direction.CCW);
			canvas.clipPath(path);
			Bitmap sourceBitmap = bitmap;
			Rect rect = new Rect(0, 0, sourceBitmap.getWidth(),sourceBitmap.getHeight());
			canvas.drawBitmap(bitmap, rect, rect, paint);
			return targetBitmap;
		}
		return null;

	}

	public static byte[] getByteArray(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray;
	}


	public static File createDirectory() {

		File mainDir = new File(Environment.getExternalStorageDirectory(),APP_DIR_MAIN);
		if (!mainDir.exists()) {
			mainDir.mkdirs();
		}
		return mainDir;
	}

	public static File getFileFromUri(Context context, Uri uri) {

		if (uri == null) {
			return null;
		}

		String[] filePath = { MediaStore.Images.Media.DATA };
		Cursor c = context.getContentResolver().query(uri, filePath, null,null, null);
		c.moveToFirst();
		int columnIndex = c.getColumnIndex(filePath[0]);
		String picturePath = c.getString(columnIndex);
		File file = new File(picturePath);
		c.close();
//		LogUtils.debug("image Path", picturePath + "");
		return file;
	}

	public static String SaveImageToDevice(Bitmap mImage) {
		String mImagePath = null;
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),APP_DIR_MAIN);

		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("/sendfie/Profile Image", "Oops! Failed create "+ " directory");
			}
		}
		String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss",Locale.getDefault()).format(new Date());

		File mediaFile;
		mediaFile = new File(mediaStorageDir.getPath() + File.separator+ timeStamp + ".jpg");
		FileOutputStream out;
		try {
			out = new FileOutputStream(mediaFile);
			mImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			mImage.recycle();

			mImagePath = mediaFile.getAbsolutePath();
			return mImagePath;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mImagePath;
	}

	public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight,FragmentActivity context) {
		final float densityMultiplier = context.getResources().getDisplayMetrics().density;
		int h = (int) (newHeight * densityMultiplier);
		int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));
		photo = Bitmap.createScaledBitmap(photo, w, h, true);
		return photo;
	}


	public static Bitmap compressImage(String filePath) {
		Bitmap scaledBitmap = null;
		String filename = null;

		try {
			BitmapFactory.Options options = new BitmapFactory.Options();

			// by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
			// you try the use the bitmap here, you will get null.
			options.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

			int actualHeight = options.outHeight;
			int actualWidth = options.outWidth;

			if (actualHeight == 0) {
				actualHeight = bmp.getHeight();
			}
			if (actualWidth == 0) {
				actualWidth = bmp.getWidth();
			}

			// max Height and width values of the compressed image is taken as 816x612

			float maxHeight = 250.0f;
			float maxWidth = 300.0f;

			System.out.println("heig actualWidth" + actualWidth);
			System.out.println("heig actualHeight" + actualHeight);
			float imgRatio = actualWidth / actualHeight;
			float maxRatio = maxWidth / maxHeight;

			// width and height values are set maintaining the aspect ratio of the image

			if (actualHeight > maxHeight || actualWidth > maxWidth) {
				if (imgRatio < maxRatio) {
					imgRatio = maxHeight / actualHeight;
					actualWidth = (int) (imgRatio * actualWidth);
					actualHeight = (int) maxHeight;
				} else if (imgRatio > maxRatio) {
					imgRatio = maxWidth / actualWidth;
					actualHeight = (int) (imgRatio * actualHeight);
					actualWidth = (int) maxWidth;
				} else {
					actualHeight = (int) maxHeight;
					actualWidth = (int) maxWidth;
				}
			}

			// setting inSampleSize value allows to load a scaled down version of the original image

			options.inSampleSize = calculateInSampleSize(options, actualWidth,actualHeight);

			// inJustDecodeBounds set to false to load the actual bitmap
			options.inJustDecodeBounds = false;

			// this options allow android to claim the bitmap memory if it runs low on memory
			options.inPurgeable = true;
			options.inInputShareable = true;
			options.inTempStorage = new byte[16 * 1024];

			// load the bitmap from its path
			bmp = BitmapFactory.decodeFile(filePath, options);
			scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,Bitmap.Config.ARGB_8888);

			float ratioX = actualWidth / (float) options.outWidth;
			float ratioY = actualHeight / (float) options.outHeight;
			float middleX = actualWidth / 2.0f;
			float middleY = actualHeight / 2.0f;

			Matrix scaleMatrix = new Matrix();
			scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

			Canvas canvas = new Canvas(scaledBitmap);
			canvas.setMatrix(scaleMatrix);
			canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2,middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

			// check the rotation of the image and display it properly
			ExifInterface exif;
			exif = new ExifInterface(filePath);

			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
			Log.d("EXIF", "Exif: " + orientation);
			Matrix matrix = new Matrix();
			if (orientation == 6) {
				matrix.postRotate(90);
				Log.d("EXIF", "Exif: " + orientation);
			} else if (orientation == 3) {
				matrix.postRotate(180);
				Log.d("EXIF", "Exif: " + orientation);
			} else if (orientation == 8) {
				matrix.postRotate(270);
				Log.d("EXIF", "Exif: " + orientation);
			}
			scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,true);

			FileOutputStream out = null;
			filename = filePath;
			out = new FileOutputStream(filename);

			// write the compressed bitmap at the destination specified by filename.
			scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledBitmap;
	}

    public static String fileExtenctionresult(String fileName)
    {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }





    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		final float totalPixels = width * height;
		final float totalReqPixelsCap = reqWidth * reqHeight * 2;
		while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
			inSampleSize++;
		}

		return inSampleSize;
	}

//	public static Bitmap generateBarCode(String barcodeData) {
//		try {
//			Bitmap bitmap = BarcodeGenerator.encodeAsBitmap(barcodeData,BarcodeFormat.CODE_128, 600, 300);
//			return bitmap;
//		} catch (WriterException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

}
