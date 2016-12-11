package ph.edu.apc.mytenant.activity;

/**
 * Created by Gail on 12/4/2016.
 */

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import ph.edu.apc.mytenant.R;
import ph.edu.apc.mytenant.model.Tenant;

public class FirebaseHelper extends Content_Activity {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<Tenant> tenants =new ArrayList<>();
    CustomAdapter adapter; //added for notifyDataSetChanged

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE
    public Boolean save(Tenant tenant)
    {
        if(tenant==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Tenant").push().setValue(tenant);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        tenants.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Tenant tenant = ds.getValue(Tenant.class);
            tenants.add(tenant);
          //  adapter.notifyDataSetChanged();

        }

    }
    //READ
    public ArrayList<Tenant> retrieve()
    {
        db.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

               /* String key = dataSnapshot.getKey();
                Tenant newTenant = dataSnapshot.getValue(Tenant.class);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   if(ds.getKey().equals(key)){
                     //  ds.setValues(newT enant);
                   }

                } */
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
              //  dataSnapshot.removeValue();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return tenants;
    }

}