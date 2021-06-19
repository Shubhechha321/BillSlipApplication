package com.example.billslipapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1000;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ImageView imageView;
    private Uri imageUri;
    private String m_Text = "";
    private TextView current;
    private Boolean clear;

    ArrayList<Bitmap> rev = new ArrayList<Bitmap>();
    ArrayList<Double> price = new ArrayList<Double>();
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = this.findViewById(R.id.cameraIcon);
        this.current = this.findViewById(R.id.current);
        TextView done = findViewById(R.id.tv_done);
        TextView tvOne = findViewById(R.id.tv1);
        TextView tvTwo = findViewById(R.id.tv2);
        TextView tvThree = findViewById(R.id.tv3);
        TextView tvFour = findViewById(R.id.tv4);
        TextView tvFive = findViewById(R.id.tv5);
        TextView tvSix = findViewById(R.id.tv6);
        TextView tvSeven = findViewById(R.id.tv7);
        TextView tvEight = findViewById(R.id.tv8);
        TextView tvNine = findViewById(R.id.tv9);
        TextView tvZero = findViewById(R.id.tv0);
        TextView tvEquals = findViewById(R.id.tv_equals);
        TextView tvAdd = findViewById(R.id.tv_add);
        TextView tvMultiply = findViewById(R.id.tv_multiply);
        TextView tvDot = findViewById(R.id.tv_dot);
        TextView tvClear = findViewById(R.id.tv_clear);
        TextView tvTotal = findViewById(R.id.total);
        ImageButton imageButton = findViewById(R.id.end);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                current.setText("");
            }
        });

        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("1", clear = true);
            }
        });
        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("2", clear = true);
            }
        });
        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("3", clear = true);
            }
        });
        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("4", clear = true);
            }
        });
        tvFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("5", clear = true);
            }
        });
        tvSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("6", clear = true);
            }
        });
        tvSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("7", clear = true);
            }
        });
        tvEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("8", clear = true);
            }
        });
        tvNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("9", clear = true);
            }
        });
        tvZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateNumber("0", clear = true);
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateExp("+", clear = true);
            }

        });
        tvMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateExp("*", clear = true);
            }
        });
        tvDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    current.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setText("");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });
        tvEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    // Read the expression
                    String txt = current.getText().toString();
                    // Create an Expression (A class from exp4j library)
                    Expression expression = new ExpressionBuilder(txt).build();
                    try {
                        // Calculate the result and display
                        double result = expression.evaluate();
                        current.setText(Double.toString(result));
                        lastDot = true; // Result contains a dot
                    } catch (ArithmeticException ex) {
                        // Display an error message
                        current.setText("Error");
                        stateError = true;
                        lastNumeric = false;
                    }
                    double d=Double.parseDouble(current.getText().toString());
                    price.add(d);
                    double sum = 0;
                    for(int i = 0; i < price.size(); i++)
                        sum += price.get(i);
                    tvTotal.setText(Double.toString(sum));
                }
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                current.setText("");
                n--;

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                BillData.getInstance().setBitmaps(rev);
                BillData.getInstance().setDoubles(price);
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void evaluateExp(String s, Boolean aBoolean) {
        if (lastNumeric && !stateError) {
            current.append(s);
            lastNumeric = false;
            lastDot = false;    // Reset the DOT flag
        }
    }
    private void evaluateNumber(String s, Boolean canClear) {
        if (stateError) {
            // If current state is Error, replace the error message
            current.setText(s);
            stateError = false;
        } else {
            // If not, already there is a valid expression so append to it
            current.append(s);
        }
        // Set the flag
        lastNumeric = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {


            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
           // byte[] byteArray = stream.toByteArray();
            rev.add(photo);
            /*try {
                //Write file
                String filename = "bitmap.png";
                FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);

                //Cleanup
                stream.close();
                photo.recycle();

                //Pop intent

            } catch (Exception e) {
                e.printStackTrace();
            }*/
            imageView.setImageBitmap(photo);

            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter the price");

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    current.setText("₹"+m_Text);
                    rev.add(photo);
                    double d=Double.parseDouble(m_Text);
                    n.add(d);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            //current.setText(m_Text);

            builder.show();
             */
        }
    }
}