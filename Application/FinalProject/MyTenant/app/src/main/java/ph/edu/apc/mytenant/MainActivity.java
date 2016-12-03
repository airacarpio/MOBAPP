package ph.edu.apc.mytenant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Aira Joyce on 11/30/2016.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView tvUserEmail;
    private Button bSignout;

    @Override
    protected void onCreate(Bundle savedInstancesState){
         super.onCreate(savedInstancesState);
         setContentView(R.layout.content_);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Signin_Activity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        tvUserEmail=(TextView) findViewById(R.id.tvUserEmail);
        tvUserEmail.setText("Welcome "+ user.getEmail() );

        bSignout=(Button)findViewById(R.id.bSignout);
        bSignout.setOnClickListener(this);
     }
    @Override
    public void onClick(View view){
        if(view == bSignout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Signin_Activity.class));
        }
    }
}
