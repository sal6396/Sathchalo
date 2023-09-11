    package sathchalo.com;

    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.inputmethod.InputMethodManager;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.Toast;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;

    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.HashMap;
    import java.util.Map;

    public class Login_Activity extends AppCompatActivity implements View.OnClickListener{


        private EditText edNumber, edPassword;
        private Button btn,btn_New_User;

        private ProgressDialog progressDialog;
        private CheckBox saveLoginCheckBox;

        private SharedPreferences loginPreferences;
        private SharedPreferences.Editor loginPrefsEditor;
        private boolean saveLogin;
        private String number, password;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            btn_New_User = findViewById(R.id.textViewNewUser);
            btn = findViewById(R.id.buttonLogin);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);


            edNumber = findViewById(R.id.editTextTextLoginNumber);
            edPassword = findViewById(R.id.editTextTextLoginPassword);
            saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);
            btn.setOnClickListener(this);

            btn_New_User.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Login_Activity.this, Signup_Activity.class));
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login_Activity.this, MainActivity.class));
                }
            });

            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();

            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin) {
                edNumber.setText(loginPreferences.getString("number", ""));
                edPassword.setText(loginPreferences.getString("password", ""));
                saveLoginCheckBox.setChecked(true);
            }
        }

        @Override
        public void onClick(View view) {
            if (view == btn) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edNumber.getWindowToken(), 0);

                number = edNumber.getText().toString().trim();
                password = edPassword.getText().toString().trim();

                if (saveLoginCheckBox.isChecked()) {
                    saveLoginCredentials(number, password);
                } else {
                    clearLoginCredentials();
                }

                userLogin();
            }
        }

        private void saveLoginCredentials(String number, String password) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("number", number);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.apply();
        }

        private void clearLoginCredentials() {
            loginPrefsEditor.clear();
            loginPrefsEditor.apply();
        }

        private void userLogin() {
            final String number = edNumber.getText().toString().trim();
            final String password = edPassword.getText().toString().trim();

            if (number.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Log.d("Response", response);


                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.d("Server Response", jsonObject.toString());

                                if (jsonObject.has("success")) {
                                    boolean success = jsonObject.getBoolean("success");
                                    if (success) {
                                        // Process successful login
                                        // ...
                                    } else {
                                        // Handle unsuccessful login
                                        String message = jsonObject.getString("message");
                                        Toast.makeText(Login_Activity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Handle missing "success" field
                                    Toast.makeText(Login_Activity.this, "Invalid server response", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e)
                            {
                            e.printStackTrace();
                            Toast.makeText(Login_Activity.this, "Error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Login_Activity.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("number", number);
                    params.put("password", password);
                    return params;
                }
            };

            // Add the request to the RequestQueue
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }