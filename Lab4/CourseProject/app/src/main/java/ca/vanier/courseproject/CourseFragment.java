package ca.vanier.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class CourseFragment extends Fragment {

    int index = 0;
    int len = 4;

    Course[] courses = new Course[len];

    Button start, stop, link;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if(savedInstanceState != null){
            index = savedInstanceState.getInt("currentIndex", 0);
        }

        courses[0] = new Course("MIS 101", "Intro to Info Systems", 140);
        courses[1] = new Course("MIS 301", "Systems Analysis", 35);
        courses[2] = new Course("MIS 441", "Database Management", 12);
        courses[3] = new Course("CS 155", "Programming in C++", 90);
    }

    ActivityResultLauncher<Intent> courseActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    if (data != null) {
                        String updatedCourseNo = data.getStringExtra("updatedCourseNo");
                        String updatedCourseName = data.getStringExtra("updatedCourseName");
                        int updatedMaxEnroll = data.getIntExtra("updatedMaxEnroll", -1);
                        courses[index].setCourse_no(updatedCourseNo);
                        courses[index].setCourse_name(updatedCourseName);
                        courses[index].setMax_enrl(updatedMaxEnroll);
                    }
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        // Get the view of courseToolbar
        Toolbar courseToolbar = (Toolbar) v.findViewById(R.id.courseToolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(courseToolbar);

        TextView currentCourseTextView = (TextView) v.findViewById(R.id.currentCourse);
        TextView totalFeesTextView = (TextView) v.findViewById(R.id.totalFees);
        Button calculateFeesButton = (Button) v.findViewById(R.id.totalFeesBtn);
        Button nextCouseButton = (Button) v.findViewById(R.id.nextCourseBtn);
        Button detailsBtn = (Button) v.findViewById(R.id.detailsBtn);
        start = v.findViewById(R.id.buttonStartService);
        stop = v.findViewById(R.id.buttonStopService);
        link = v.findViewById(R.id.buttonCourseLink);

        StringBuilder all_courses = new StringBuilder();
        for (Course course : courses) {
            all_courses.append(course.toString());
        }
        currentCourseTextView.setText(String.format("%s", all_courses.toString()));

        calculateFeesButton.setOnClickListener(view -> {
                totalFeesTextView.setText(String.format("Total course fees: %.2f", courses[index].calculateCourseTotalFees()));
        });

        nextCouseButton.setOnClickListener(view -> {
            index = (index + 1)%len;
                currentCourseTextView.setText(String.format("%s %s", courses[index].getCourse_no(), courses[index].getCourse_name()));
        });

        detailsBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), CourseActivity.class);
//            intent.putExtra("intentId", courses[index].getCourse_no());
//            intent.putExtra("intentName", courses[index].getCourse_name());
//            intent.putExtra("intentEnroll", Integer.toString(courses[index].getMax_enrl()));
//            intent.putExtra("intentCredit", Integer.toString(Course.getCredits()));
//            courseActivityResultLauncher.launch(intent);
            Toast.makeText(activity, "Course Details", Toast.LENGTH_SHORT).show();
        });

        start.setOnClickListener(view -> {
            getActivity().startService(new Intent(getActivity(), CourseService.class));
        });

        stop.setOnClickListener(view -> {
            getActivity().stopService(new Intent(getActivity(), CourseService.class));
        });

        link.setOnClickListener(view -> {
            // Use implicit intent
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aryanarora.ca/"));
            startActivity(intent);
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_course, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.courseOptionItem1) {
            Toast.makeText(getActivity(), "Item 1 Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), CourseMapsActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.courseOptionItem2) {
            Toast.makeText(getActivity(), "Item 2 Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), CourseContentActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.courseOptionItem3) {
            Toast.makeText(getActivity(), "Item 3 Selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.courseOptionItem4) {
            Toast.makeText(getActivity(), "Item 4 Selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.courseOptionItem5) {
            Toast.makeText(getActivity(), "Item 5 Selected", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}