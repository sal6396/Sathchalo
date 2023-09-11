package sathchalo.com.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import sathchalo.com.R;

public class CreateGroup_Fragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private EditText editTextGPNumbers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group_, container, false);

//        editTextGPNumbers = view.findViewById(R.id.editTextGPNumbers);

//        Button selectContactsButton = view.findViewById(R.id.selectContactsButton);
//        selectContactsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openContactPicker();
//            }
//        });

        return view;
    }

//    private void openContactPicker() {
//        if (hasPermissionToReadContacts()) {
//            ArrayList<String> contactNumbers = getContactNumbers();
//            if (!contactNumbers.isEmpty()) {
//                // Pass the selected contact numbers to ContactList_Fragment
//                ContactList_Fragment fragment = new ContactList_Fragment();
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("selectedContacts", contactNumbers);
//                fragment.setArguments(bundle);
//
//                // Replace the current fragment with ContactList_Fragment
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frameLayout, fragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        } else {
//            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
//        }
//    }
//
//    public void updateSelectedContacts(ArrayList<String> selectedContacts) {
//        StringBuilder selectedNumbers = new StringBuilder();
//        for (String contact : selectedContacts) {
//            selectedNumbers.append(contact).append("\n");
//        }
//
//        editTextGPNumbers.setText(selectedNumbers.toString());
//    }
//
//    private boolean hasPermissionToReadContacts() {
//        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private ArrayList<String> getContactNumbers() {
//        ArrayList<String> contactNumbers = new ArrayList<>();
//        Cursor cursor = null;
//
//        try {
//            cursor = getActivity().getContentResolver().query(
//                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//                    String phoneNumber = cursor.getString(numberIndex);
//                    contactNumbers.add(phoneNumber);
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//
//        return contactNumbers;
//    }
}
