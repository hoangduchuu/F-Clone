package net.hoangduchuu.foody;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.ivPhoto);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

//        streamUploadingFromLocalFile();
        uploadFileFromUri();
    }

    private void uploadFileFromUri() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = new URL("http://duanvinpearl.org/wp-content/uploads/2016/06/nuoi-me-xui-hay-khong.jpg").openStream();
                    storageReference.child("uri").child("dep.jpg").putStream(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void streamUploadingFromLocalFile() {
        Drawable drawable = imageView.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] data = byteArrayOutputStream.toByteArray();

        storageReference.child("photos").child("aaa.jpg").putBytes(data);
        storageReference.child("photos1").child("bb.jpg").putBytes(data);
        storageReference.child("photos2").child("cc.jpg").putBytes(data);
        storageReference.child("photos3").child("dd.jpg").putBytes(data);


        storageReference.child("photos").child("aaa.jpg").putBytes(data);
        storageReference.child("photos").child("bb.jpg").putBytes(data);
        storageReference.child("photos").child("cc.jpg").putBytes(data);
        storageReference.child("photos").child("dd.jpg").putBytes(data);

    }
}
