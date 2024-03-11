package ca.vanier.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CourseFragment extends Fragment {

    int index = 0;
    int len = 4;

    Course[] courses = new Course[len];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        TextView currentCourseTextView = (TextView) v.findViewById(R.id.currentCourse);
        TextView totalFeesTextView = (TextView) v.findViewById(R.id.totalFees);
        Button calculateFeesButton = (Button) v.findViewById(R.id.totalFeesBtn);
        Button nextCouseButton = (Button) v.findViewById(R.id.nextCourseBtn);
        Button detailsBtn = (Button) v.findViewById(R.id.detailsBtn);

        String all_courses = "";
        for(int i = 0; i < courses.length; i++){
            all_courses += courses[i].toString();
        }
        currentCourseTextView.setText(String.format("%s", all_courses));

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
        });

        return v;
    }
}