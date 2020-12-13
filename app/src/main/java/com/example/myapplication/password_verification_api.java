package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import am.appwise.components.ni.NoInternetDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class password_verification_api extends AppCompatActivity {
    EditText enter_phn_number, enter_password;
    TextView get_started_button;
    String myResponse;
    CoordinatorLayout coordinator;
    ProgressBar Progress_Login_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verification_api);
        NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(this).build();
        noInternetDialog.setCancelable(false);
        Progress_Login_in=findViewById(R.id.Progress_Login_in);
        enter_phn_number = findViewById(R.id.enter_phn_number);
        enter_password = findViewById(R.id.enter_password);
        get_started_button = findViewById(R.id.get_started_button);
        coordinator = findViewById(R.id.coordinator);
        enter_phn_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer textlength1 = enter_phn_number.getText().length();

                if (textlength1 > 9) {
                    enter_password.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enter_phn_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer textlength1 = enter_phn_number.getText().length();

                if (textlength1 > 9) {
                    enter_password.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        get_started_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enter_phn_number.getText().toString().trim().equalsIgnoreCase("")) {
                    enter_phn_number.setError("This field can not be blank");
                    enter_phn_number.requestFocus();
                } else if (enter_phn_number.getText().toString().length() < 10) {
                    enter_phn_number.setError("Enter Proper Phone Number!!!");
                    enter_phn_number.requestFocus();
                } else if (enter_password.getText().toString().trim().equalsIgnoreCase("")) {
                    enter_password.setError("This field can not be blank");
                    enter_password.requestFocus();
                } else {
                    String mobile = enter_phn_number.getText().toString();
                    String password = enter_password.getText().toString();
                    CheckLoginApi(mobile, password);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    Progress_Login_in.setVisibility(View.VISIBLE);
                    get_started_button.setText("");
                    get_started_button.setEnabled(false);
                }
            }
        });

    }

    private void CheckLoginApi(String mobile, String password) {
        Log.i("Mobile", mobile);
        Log.i("Password", password);
        RequestBody formBody = new FormBody.Builder()
                .add("mobile", mobile)
                .add("password", password)
                .build();

        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://mekvahan.com/api/android_intern_task")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("Exception", e.getMessage());
                Toast.makeText(password_verification_api.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Progress_Login_in.setVisibility(View.GONE);
                get_started_button.setText("PROCEED");
                get_started_button.setEnabled(true);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("Response", String.valueOf(response));
                    myResponse = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(myResponse);

                        try {
                            Log.i("Status", jsonObject.getString("status"));
                        } catch (Exception e) {

                        }


                        if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                            password_verification_api.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Snackbar snackbar = Snackbar.make(coordinator, "Login Success!!!", Snackbar.LENGTH_INDEFINITE);
                                    snackbar.show();
                                    Progress_Login_in.setVisibility(View.GONE);
                                    get_started_button.setText("PROCEED");
                                    get_started_button.setEnabled(true);
                                }
                            });

                        } else {
                            password_verification_api.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Progress_Login_in.setVisibility(View.GONE);
                                        get_started_button.setText("PROCEED");
                                        get_started_button.setEnabled(true);
                                        Snackbar snackbar = Snackbar.make(coordinator, jsonObject.getString("status").toUpperCase(), Snackbar.LENGTH_INDEFINITE)
                                                .setAction("Retry", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        enter_phn_number.setText("");
                                                        enter_password.setText("");
                                                        enter_phn_number.requestFocus();
                                                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                                                    }
                                                })
                                                .setActionTextColor(Color.RED);
                                        snackbar.show();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } catch (Exception e) {
                        password_verification_api.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(password_verification_api.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Progress_Login_in.setVisibility(View.GONE);
                                get_started_button.setText("PROCEED");
                                get_started_button.setEnabled(true);
                            }
                        });
                    }
                }
            }
        });


    }


}