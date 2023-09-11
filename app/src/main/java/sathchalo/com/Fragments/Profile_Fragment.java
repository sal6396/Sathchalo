package sathchalo.com.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import sathchalo.com.R;
import sathchalo.com.SharedPrefManager;

public class Profile_Fragment extends Fragment {

    private ShapeableImageView userImage;
    private TextView textViewUsername, textViewPhone, textViewCity, textViewGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        userImage = view.findViewById(R.id.userImage);
        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewCity = view.findViewById(R.id.textViewCity);
        textViewGender = view.findViewById(R.id.textViewGander);

//        // Replace with actual user data fetched from the server
//        String username = "John Doe";
//        String phoneNumber = "+1234567890";
//        String city = "New York";
//        String gender = "Male";
//        String imageUrl = "https://example.com/user_image.jpg";

        String username = SharedPrefManager.getInstance(getActivity()).getUsername();
        String number = SharedPrefManager.getInstance(getActivity()).getNumber();
        String gender = SharedPrefManager.getInstance(getActivity()).getGender();
        String image = SharedPrefManager.getInstance(getActivity()).getImage();


        if (username != null) {
            textViewUsername.setText(username);
        }
        if (number != null) {
            textViewPhone.setText(number);
        }
        if (gender != null) {
            textViewGender.setText(gender);
        }
        if (image != null) {
            Picasso.get().load(image).into(userImage); // Using Picasso to load image from URL into ImageView
        }

//        textViewUsername.setText(username);
//        textViewPhone.setText(phoneNumber);
//        textViewCity.setText(city);
//        textViewGender.setText(gender);
//        userImage

//        Picasso.get()
//                .load(imageUrl)
//                .placeholder(R.drawable.account) // Placeholder image if loading fails
//                .into(userImage);

        return view;
    }
}
