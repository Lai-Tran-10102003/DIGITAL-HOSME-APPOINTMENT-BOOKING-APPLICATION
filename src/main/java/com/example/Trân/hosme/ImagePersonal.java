package com.example.Trân.hosme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.Trân.model.User;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityImagePersonalBinding;

import java.io.ByteArrayOutputStream;

public class ImagePersonal extends AppCompatActivity {

    ActivityImagePersonalBinding binding;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "UserData";
    public static final String USER_ID = "UserID";
    public static final String USER_NAME = "UserName";
    public static final String USER_PHONE = "PhoneNumber";
    public static final String USER_BIRTH = "DayofBirth";
    public static final String USER_GENDER = "Gender";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASS = "Password";
    public static final String USER_IMAGE = "UserImage";

    ActivityResultLauncher<Intent> activityResultLauncher;
    boolean openCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagePersonalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDt();
        addEvents();



        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                if(openCam){
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    binding.imvPhoto.setImageBitmap(photo);
                }else{
                    Uri selectedPhotoUri = result.getData().getData();
                    binding.imvPhoto.setImageURI(selectedPhotoUri);
                }
                showButtons(true);
            }
        });
    }

    User u;
    private void getDt() {
        Intent intent = getIntent();
        u = (User) intent.getSerializableExtra("data");
        byte[] imageBytes = intent.getByteArrayExtra("image");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        binding.imvPhoto.setImageBitmap(imageBitmap);

    }

    private void showButtons(boolean show) {
        if (show) {
            binding.btnSave.setVisibility(View.VISIBLE);
            binding.imvDelete.setVisibility(View.VISIBLE);
        } else {
            binding.btnSave.setVisibility(View.GONE);
            binding.imvDelete.setVisibility(View.GONE);
        }
    }

    private void addEvents() {
        binding.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đặt hình ảnh trên ImageView thành null hoặc một hình ảnh mặc định khác
                binding.imvPhoto.setImageResource(R.drawable.icon_user); // hoặc binding.imvPhoto.setImageResource(R.drawable.default_image);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToDatabase();
            }
        });

        binding.btnCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadImage();
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void showLoadImage() {
        Dialog dialog = new Dialog(ImagePersonal.this);
        dialog.setContentView(R.layout.load_image);

        LinearLayout bsCam = dialog.findViewById(R.id.bs_camera);
        bsCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open camera
                openCam = true;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivity(intent);
                activityResultLauncher.launch(intent);
                dialog.dismiss();
            }
        });
        LinearLayout bsGal = dialog.findViewById(R.id.bs_gallery);
        bsGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open Gallery
                openCam = false;
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    private void saveImageToDatabase() {
        try {
            // Convert image to byte array
            BitmapDrawable drawable = (BitmapDrawable) binding.imvPhoto.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] imageData = stream.toByteArray();

            // Open database for writing
            db = openOrCreateDatabase(MainActivity5.DB_NAME, MODE_PRIVATE, null);

            // Store image data into UserImage column in the database for a specific user (specified by UserID)
            ContentValues values = new ContentValues();
            values.put(MainActivity5.USER_IMAGE, imageData);
            long result = db.update(TBL_NAME, values, MainActivity5.USER_ID + " = ?", new String[]{String.valueOf(u.getUserID())}); // Replace userId with the actual user ID

            // Close the database after performing the query
            db.close();

            // Check if the update was successful and show toast accordingly
            if (result != -1) {
                Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }


}