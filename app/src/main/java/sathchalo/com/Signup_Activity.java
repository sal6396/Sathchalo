package sathchalo.com;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText edname, ednumber, edpassword, edconfirm, edgender, edcity;
    private Button btn;

    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edname = findViewById(R.id.editTextTextRegUsername);
        ednumber = findViewById(R.id.editTextTextPhoneNo);
        edpassword = findViewById(R.id.editTextTextRegPassword);
        edconfirm = findViewById(R.id.editTextTextRegConfirmPassword);
        edgender = findViewById(R.id.editTextTextGender);
        edcity = findViewById(R.id.editTextTextCity);
        btn = findViewById(R.id.buttonReg);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }

    private void registerUser() {
        String name = edname.getText().toString().trim();
        String number = ednumber.getText().toString().trim();
        String password = edpassword.getText().toString().trim();
        String confirm = edconfirm.getText().toString().trim();
        String gender = edgender.getText().toString().trim();
        String city = edcity.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            edname.setError("Name is required");
            edname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(number)) {
            ednumber.setError("Number is required");
            ednumber.requestFocus();
            return;
        }

        if (!isValidPassword(password)) {
            edpassword.setError("Password must be at least 8 characters long and contain letters, digits, and special characters");
            edpassword.requestFocus();
            return;
        }

        if (!password.equals(confirm)) {
            edconfirm.setError("Passwords do not match");
            edconfirm.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            edgender.setError("Gender is required");
            edgender.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(city)) {
            edcity.setError("City is required");
            edcity.requestFocus();
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        // Make a POST request to the API to register the user
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        // Print the response to the logcat
                        Log.d("Response", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Check if the response contains any specific error fields
                            if (jsonObject.has("status_code")) {
                                int statusCode = jsonObject.getInt("status_code");
                                if (statusCode == 409) {
                                    String message = jsonObject.getString("message");
                                    ednumber.setError(message);
                                    ednumber.requestFocus();
                                } else {
                                    String message = jsonObject.getString("message");

                                }
                            } else {
                                // Assuming a successful response since there's no specific error indication
                                Toast.makeText(Signup_Activity.this, "User registration successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup_Activity.this, Login_Activity.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("number", number);
                params.put("password", password);
                params.put("gender", gender);
                params.put("city", city);
                return params;
            }
        };

        // Add the request to the RequestQueue
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isValidPassword(String password) {
        // Implement your password validation logic here
        // For example, you can use the isvalid method you provided in your original code
        // or you can use regular expressions to define password requirements.
        // The current implementation is a basic check for password length and containing at least one letter, one digit, and one special character.

        int f1 = 0, f2 = 0, f3 = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                f1 = 1;
            } else if (Character.isDigit(c)) {
                f2 = 1;
            } else if (c >= 33 && c <= 46 || c == 64) {
                f3 = 1;
            }
        }

        return password.length() >= 8 && f1 == 1 && f2 == 1 && f3 == 1;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}