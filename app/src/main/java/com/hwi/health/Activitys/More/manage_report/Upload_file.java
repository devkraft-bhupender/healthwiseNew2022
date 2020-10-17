package com.hwi.health.Activitys.More.manage_report;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hwi.health.FileUploader;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Upload_file extends AppCompatActivity implements BaseUrl, View.OnClickListener {
    ImageView img;
    EditText file_name;
    Button Attach, submit;
    Uri file;
    Context ctx;
    int PICKFILE_RESULT_CODE = 1;
    int REQUEST_IMAGE_CAPTURE = 11;
    String imagepath, name;
    ProgressDialog pd;
    String randnoo, user_id, treat_value;
    File file1;
    TextView pdff_text;
    Spinner Treatment_spin;
    ArrayList<String> spin_list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customac);
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Upload File");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        //back
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Attach = (Button) findViewById(R.id.Attach);
        submit = (Button) findViewById(R.id.submit);
        img = (ImageView) findViewById(R.id.img);
        file_name = (EditText) findViewById(R.id.file_name);
        Treatment_spin = (Spinner) findViewById(R.id.Treatment_spin);
        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        pdff_text = (TextView) findViewById(R.id.pdff);

        ctx = this;
        home_L.setOnClickListener(this);
        profile_L.setOnClickListener(this);
        log_L.setOnClickListener(this);
        plans_L.setOnClickListener(this);
        more_L.setOnClickListener(this);

        spin_list.add("Prescription");
        spin_list.add("Investigation");

        adapter = new ArrayAdapter<String>(Upload_file.this, android.R.layout.simple_spinner_dropdown_item, spin_list);
        Treatment_spin.setAdapter(adapter);
        treat_value = Treatment_spin.getSelectedItem().toString();

        Treatment_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                treat_value = spin_list.get(position);
                Log.e("slistttt", treat_value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SharedPreferences sp = new AllSharedPrefrences(this).UserDataget();
        user_id = sp.getString("Userid", "");

        Attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog(PICKFILE_RESULT_CODE, REQUEST_IMAGE_CAPTURE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = file_name.getText().toString();
                RandomNumber rn = new RandomNumber();
                randnoo = rn.randno();

                if (name.equals("")){
                    Toast.makeText(ctx, "File name required", Toast.LENGTH_SHORT).show();
                }else {
                    pd = new ProgressDialog(Upload_file.this);
                    pd.setMessage("Please Wait...");
                    pd.setCancelable(false);
                    pd.show();
                    new Thread(new Runnable() {
                        public void run() {

                            try {

                                uploadFile();

                            } catch (Exception e) {
                                Log.e("error", e.toString());
                            }

                        }
                    }).start();
                }


            }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
    }


    void AlertDialog(final int gallery, final int Cam) {
        final Dialog dialog = new Dialog(ctx, R.style.CustomDialog);
        dialog.setContentView(R.layout.imageselection);
        dialog.setCancelable(true);
        Button gall = (Button) dialog.findViewById(R.id.offer1);
        Button camera = (Button) dialog.findViewById(R.id.offer2);
        Button close = (Button) dialog.findViewById(R.id.close);
        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrouseImage(gallery);
                dialog.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakePictureIntent(Cam);
                dialog.dismiss();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    void BrouseImage(int code) {
//               Intent intent = new Intent();
//        intent.setType("*/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), code);

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimetypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                        "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "text/plain",
                        "application/pdf",
                        "application/zip","image/*"};
      //  String[] mimetypes = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword", "image/*", "application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), code);
    }

    public void TakePictureIntent(int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        File directory = cw.getDir("uploads", Context.MODE_PRIVATE);
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "CameraDemo" + File.separator);
        root.mkdirs();
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                200);
        startActivityForResult(intent, code);
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                timeStamp);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "KW", null);
        return Uri.parse(path);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICKFILE_RESULT_CODE) {
            try {
                Uri selectedFileUri = data.getData();
                imagepath = getPath(this, selectedFileUri);

                Picasso.get().load(selectedFileUri).into(img);

                try {
                    String[] bits = imagepath.split("/");
                    String lastOne = bits[bits.length-1];
                    pdff_text.setText(lastOne);
                    Log.e("imagepath", lastOne + "....");
                }catch (Exception e){

                }
            } catch (Exception e) {
                Log.e("img err = ", e.toString());
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), file);
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                imagepath = getPath(this, tempUri);
                Log.e("imagepatht", tempUri + "");
                Picasso.get().load(tempUri).into(img);

            } catch (Exception e) {
                Log.e("img err = ", e.toString());
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public void uploadFile() {

        String resp = null;
        String charset = "UTF-8";

        try {

            if (imagepath.equals("null")) {

            } else {
                file1 = new File(imagepath);
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }


        try {
            FileUploader multipart = new FileUploader(URLS + upload_files + randnoo, charset);


            Log.e("Data =", URLS + upload_files + randnoo);


            multipart.addFormField("access_keys", AccessToken);
            multipart.addFormField("user_id", user_id);
            multipart.addFormField("file_name", name);
            multipart.addFormField("treatment", treat_value);

            //  Log.e("keyyyyyyy")
            //multipart.addFormField("image", email_edit);


            try {

                if (imagepath.equals("null")) {

                } else {
                    multipart.addFilePart("upload_file", file1);
                    Log.e("edit_image", "" + file1);
                }
            } catch (Exception e) {
                Log.e("error", e.toString());
            }
            List<String> response = multipart.finish();

            //  System.out.println("SERVER REPLIED:");

            for (String line : response) {
                resp = line;
            }
            try {
                Log.e("hdhdjahd", "" + resp);
                final JSONObject obj = new JSONObject(resp);
                final String st = obj.getString("status");
                final String msg = obj.getString("message");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (st.equals("1")) {
                            pd.dismiss();
                            try {

                                Toast.makeText(Upload_file.this, "" + msg, Toast.LENGTH_LONG).show();
                                Intent in = new Intent(Upload_file.this, Upload_file_list.class);
                                startActivity(in);
                                finish();

                            } catch (Exception e) {
                                Log.e("error", e.toString());

                            }

                        } else {
                            pd.dismiss();
                            Toast.makeText(Upload_file.this, "" + msg, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            } catch (Exception e) {
                Log.e("error", e.toString());
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(Upload_file.this, Upload_file_list.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Upload_file_list.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == home_L) {
            new MyIntent(Upload_file.this, HomeActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == profile_L) {
            new MyIntent(Upload_file.this, User_Profile.class, R.anim.enter, R.anim.exit);
        }
        if (v == log_L) {
            new MyIntent(Upload_file.this, LogActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == plans_L) {
            new MyIntent(Upload_file.this, PlansActivity.class, R.anim.enter, R.anim.exit);
        }
        if (v == more_L) {
            new MyIntent(Upload_file.this, MoreActivity.class, R.anim.enter, R.anim.exit);
        }
    }
}
