package ca.vanier.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CourseContentActivity extends AppCompatActivity {

    TextView content;
    Button service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        content = findViewById(R.id.textViewCourseContent);
        service = findViewById(R.id.buttonCourseContent);

        service.setOnClickListener(view -> {
            // consume android web service
            String uri = "https://dogapi.dog/api/v2/facts";
            new AsyncHttpClient().get(uri, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String strResponse = new String (responseBody);
                    content.setText(strResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    content.setText("Error in web service");
                }
            });
        });
    }
}