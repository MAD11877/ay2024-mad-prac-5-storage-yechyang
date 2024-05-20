package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    ImageView bigImage;
    TextView name;
    TextView description;

    public UserViewHolder(View itemView){
        super(itemView);
        //Locations of image, name and description found in custom_activity_list.xml
        smallImage = itemView.findViewById(R.id.ivSmallImage);
        name = itemView.findViewById(R.id.ivName);
        description = itemView.findViewById(R.id.ivDescription);
        bigImage = itemView.findViewById(R.id.bigImage);
        bigImage.setVisibility(View.GONE);

        smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = (User) v.getTag();
                Intent mainActivity = new Intent(smallImage.getContext(), MainActivity.class);
                mainActivity.putExtra("id", user.getId());
                mainActivity.putExtra("name", user.getName());
                mainActivity.putExtra("description", user.getDescription());
                mainActivity.putExtra("followed", user.getFollowed());

                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle("Profile");
                builder.setMessage(name.getText());
                builder.setCancelable(false);
                builder.setPositiveButton("VIEW", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(itemView.getContext(), mainActivity, null);
                            }
                        });

                builder.setNegativeButton("CLOSE", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    // Alert Dialogue Here

}
