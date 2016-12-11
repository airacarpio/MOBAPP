package ph.edu.apc.mytenant.activity.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import ph.edu.apc.mytenant.R;

/**
 * Created by Gail on 12/11/2016.
 */

public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    TextView rnum, rfee, wmeter, emeter, name, sdate;
    ItemClickListener itemClickListener;

    public viewHolder(View itemView) {
        super(itemView);

        rnum = (TextView) itemView.findViewById(R.id.textView_roomNum);
        rfee = (TextView) itemView.findViewById(R.id.textView_rentalFee);
        wmeter = (TextView) itemView.findViewById(R.id.textView_waterMeter);
        emeter = (TextView) itemView.findViewById(R.id.textView_electricMeter);
        name = (TextView) itemView.findViewById(R.id.textView_name);
        sdate = (TextView) itemView.findViewById(R.id.textView_dateEntered);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        menu.add(Menu.NONE, 1,
                Menu.NONE, "EDIT");
        menu.add(Menu.NONE, 2,
                Menu.NONE, "DELETE");
    }




}
