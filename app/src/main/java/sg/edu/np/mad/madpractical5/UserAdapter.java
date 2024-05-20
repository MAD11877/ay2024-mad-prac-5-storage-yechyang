package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> list_objects;
    private static final String TAG = "list_objects";

    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_objects = list_objects;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position){
        User list_items = list_objects.get(position);
        holder.name.setText(list_items.getName());
        holder.description.setText(list_items.getDescription());
        holder.smallImage.setTag(list_items);
        String name = list_items.getName();
        int lastDigit = 0; // Default value

        try {
            // Assuming you're parsing the last character(s) to get a number.
            lastDigit = Integer.parseInt(name.substring(name.length() - 1));
        } catch (NumberFormatException e) {
            // Handle invalid parsing.
            Log.e(TAG, "Failed to parse integer from name: " + name, e);
        }
        if (lastDigit == 7){
            holder.bigImage.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigImage.setVisibility(View.GONE);
        }
    }

    public int getItemCount(){return list_objects.size();}

    // Number 7 Here

}
