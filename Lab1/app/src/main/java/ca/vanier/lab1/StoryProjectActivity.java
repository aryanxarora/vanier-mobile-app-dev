package ca.vanier.lab1;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

public class StoryProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_project);

        EditText storyName = (EditText) findViewById(R.id.storyName);
        EditText storyAge = (EditText) findViewById(R.id.storyAge);
        EditText storyCity = (EditText) findViewById(R.id.storyCity);
        EditText storyCollege = (EditText) findViewById(R.id.storyCollege);
        EditText storyProfession = (EditText) findViewById(R.id.storyProfession);
        EditText storyAnimal = (EditText) findViewById(R.id.storyAnimal);
        EditText storyPet = (EditText) findViewById(R.id.storyPet);
        TextView storyOutput = (TextView) findViewById(R.id.storyOutput);
        Button storyBtn = (Button) findViewById(R.id.storyBtn);

        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyOutput.setText(
                        String.format("There once was a person named %s who lived in %s. At the age of %s, %s went to college at %s. %s graduated and went to work as a %s. Then, %s adopted a(n) %s named %s.They both lived happily ever after!", storyName.getText(), storyCity.getText(), storyAge.getText(), storyName.getText(), storyCollege.getText(), storyName.getText(), storyProfession.getText(), storyName.getText(), storyAnimal.getText(), storyPet.getText())
                );
            }
        });

    }

}
