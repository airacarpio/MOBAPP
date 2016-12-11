package ph.edu.apc.mytenant.activity.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ph.edu.apc.mytenant.R;
import ph.edu.apc.mytenant.activity.Detail_Activity;
import ph.edu.apc.mytenant.model.Tenant;

/**
 * Created by Gail on 12/11/2016.
 */

public class recyclerAdapter extends RecyclerView.Adapter<viewHolder> {

    Context c;
    ArrayList<Tenant> tenants;
    public int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public recyclerAdapter(Context c, ArrayList<Tenant> tenants) {
        this.c = c;
        this.tenants = tenants;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_item,parent,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        final Tenant t = tenants.get(position);

        holder.rnum.setText(t.getRoom_number());
        holder.rfee.setText(t.getRental_fee());
        holder.emeter.setText(t.getElectricity_meter());
        holder.wmeter.setText(t.getWater_meter());
        holder.name.setText(t.getName());
        holder.sdate.setText(t.getSelect_date());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(t.getRoom_number(),t.getRental_fee(),t.getElectricity_meter(),t.getWater_meter(),t.getName(),t.getSelect_date());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });



    }

    private void openDetailActivity(String...details) {
        Intent i = new Intent(c,Detail_Activity.class);
        i.putExtra("ROOM_NUM",details[0]);
        i.putExtra("RENTAL_FEE",details[1]);
        i.putExtra("W_METER",details[2]);
        i.putExtra("E_METER",details[3]);
        i.putExtra("NAME",details[4]);
        i.putExtra("SELECT_DATE",details[5]);

        c.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return tenants.size();
    }


}
