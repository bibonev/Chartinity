package bbsk.chartinity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;

public class Profile extends android.support.v4.app.Fragment {

    // initializing variable for SharedPreferences - Saving log in details
    public static final String PREFS_NAME = "LoginPrefs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_chartinity, container, false);
    }

    public void onStart(){
        super.onStart();

        TextView tv = (TextView)this.getActivity().findViewById(R.id.username_profile);
        tv.setText(((MainActivity) getActivity()).getCurrentAttribute("username"));
    }
    public void onLogOutButtonClick(View v){

        /**
         * Removing SharedPreferences when user want to log out
         */
        SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();
        this.getActivity().finish();

        // starting log in activity
        Intent i = new Intent(this.getActivity(), LogIn.class);
        startActivity(i);

    }
}
