package sg.edu.np.mad.madpractical5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.graphics.Color;
import com.google.android.material.imageview.ShapeableImageView;


// Add imports here
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseHandler dbhandler;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbhandler = new DatabaseHandler(this, "users.db", null, 1);

        Intent receivingEnd = getIntent();
        int id = receivingEnd.getIntExtra("id", -1);
        String name = receivingEnd.getStringExtra("name");
        String description = receivingEnd.getStringExtra("description");
        boolean followed = receivingEnd.getBooleanExtra("followed", false);

        Log.d("TAG", "User ID: " + id + ", Name: " + name + ", Description: " + description + ", Followed: " + followed);


        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.followbtn);
        Button btnMessage = findViewById(R.id.msgbtn);

        User clickeduser = new User(name, description, id, followed);

        // Set the TextViews with the User's name, description and default button message
        tvName.setText(name);
        tvDescription.setText(description);
        // Set the TextViews with the User's name, description and default button message
        tvName.setText(name);
        tvDescription.setText(description);
        if (followed){
            btnFollow.setText("Unfollow");
        }
        else{
            btnFollow.setText("Follow");
        }
        btnFollow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (clickeduser.getFollowed()){
                    btnFollow.setText("Follow");
                    clickeduser.setFollowed(false);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    dbhandler.updateUser(clickeduser);
                }
                else if (!clickeduser.getFollowed()){
                    btnFollow.setText("Unfollow");
                    clickeduser.setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    dbhandler.updateUser(clickeduser);
                }
            }
        });
//
//        Intent messageGroup = new Intent(MainActivity.this, MessageGroup.class);
//        btnMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(messageGroup);
//            }
//        });
    }
}