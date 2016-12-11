package ph.edu.apc.mytenant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ph.edu.apc.mytenant.R;

/**
 * Created by Aira Joyce on 12/3/2016.
 */

public class Header_Activity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView tvUserEmail;
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.nav_header_content_);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        tvUserEmail=(TextView) findViewById(R.id.tvUserEmail);
        tvUserEmail.setText("Welcome " + user.getEmail() );

    }
}
