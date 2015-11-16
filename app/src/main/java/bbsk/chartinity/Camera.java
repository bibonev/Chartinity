package bbsk.chartinity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Boyan on 10/24/2015.
 */
public class Camera extends android.support.v4.app.Fragment {

    public static final int CAMERA_REQUEST = 10;
    public static final int SELECT_PHOTO = 20;

    private ImageView imgPreview;

    public Camera()
    {
        // Required empty public constructor
    }

    public InputStream contentResolver(Context context, Uri uri) throws Exception {
        return context.getContentResolver().openInputStream(uri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_camera_chartinity, container, false);

        view.findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //when the user click on the button with label "Choose Photo"
        view.findViewById(R.id.btnChoosePhoto).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //invoke the image gallery using an implicit intent
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                //where to find the data
                File pictureDirectory =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                //get a URI representation
                Uri selectedImageUri = Uri.parse(pictureDirectoryPath);

                //set the data and the type - get all images types
                photoPickerIntent.setDataAndType(selectedImageUri, "image/*");

                //invoke the activity and get the image back from it
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        //get a reference to the image view that holds the image that the user will see
        imgPreview = (ImageView) view.findViewById(R.id.imgPreview);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    if (requestCode == CAMERA_REQUEST)
                    {
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                        File destination = new File(Environment.getExternalStorageDirectory(),
                                System.currentTimeMillis() + ".jpg");
                        FileOutputStream fo;

                        try
                        {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        imgPreview.setImageBitmap(thumbnail);
                    }
                }
            case SELECT_PHOTO:
                if(resultCode == Activity.RESULT_OK)
                {
                    //the address of the image on the SD card
                    Uri imageUri = data.getData();
                    //declare a stream to read the image data from the storage
                    InputStream inputStream;

                    //get an input stream, based on the URI of the image
                    try
                    {
                        inputStream = contentResolver(getContext(), imageUri);
                        //get a bitmap from the stream
                        Bitmap image = BitmapFactory.decodeStream(inputStream);
                        //show the image to the user
                        imgPreview.setImageBitmap(image);
                    }
                    catch (Exception e)
                    {
                        //Toast.makeText(Context.).show();
                    }
                }
        }

    }
}
