package sathchalo.com.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import sathchalo.com.R;

public class Dashboard_Fragment extends Fragment {

    private String[][] group_detail1 =
            {
                    {"Family Members", "Admin change group setting"},
                    {"Brothers", ""},
                    {"Collage Friends", "Admin change group Profile"},
                    {"School Friends", "Admin change group theme"},
                    {"Office Group", "Photo"},
                    {"Friend Ship  ", "Audio"},
                    {"Tech Fest Students ", "Admin only can Message"},
                    {"Brothers", ""},
                    {"Family Members", "Admin change group setting"},
                    {"Office Group", "Photo"},
                    {"Family Members", "Admin change group setting"},
                    {"Collage Friends", "Admin change group Profile"},
                    {"School Friends", "Admin change group theme"},
                    {"Friend Ship  ", "Audio"},
            };

    FloatingActionButton floatingActionButton;
    private String[][] group_details;
    private ArrayList<HashMap<String, String>> list = new ArrayList<>();
    private SimpleAdapter sa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new CreateGroup_Fragment(), null)
                        .commit();
            }
        });

        group_details = group_detail1;  // Update this based on your logic

        for (int i = 0; i < group_details.length; i++) {
            HashMap<String, String> projectItem = new HashMap<>();
            projectItem.put("line1", group_details[i][0]);
            projectItem.put("line2", group_details[i][1]);


            list.add(projectItem);
        }

        sa = new SimpleAdapter(
                requireContext(),
                list,
                R.layout.user_sample,
                new String[]{"line1", "line2"},
                new int[]{R.id.group_name, R.id.last_message}
        );

//        // Set a custom ViewBinder to dynamically load profile images
//        sa.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                if (view.getId() == R.id.group_profile_image && data instanceof String) {
//                    // Load the profile image dynamically
//                    int resourceId = getResources().getIdentifier((String) data, "drawable", requireContext().getPackageName());
//                    if (resourceId != 0) {
//                        ((ImageView) view).setImageResource(resourceId);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

        ListView lst = view.findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new Chat_Fragment(), null)
                        .commit();
            }
        });

        return view;
    }
}
