package ie.kieranhogan.milist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ActiveItemViewHolder extends RecyclerView.ViewHolder {
    CheckBox itemStatus;
    TextView itemName;
    TextView itemQuantity;
    ImageView itemAction;

    public ActiveItemViewHolder(View itemView, CheckBox itemStatus, TextView itemName, TextView itemQuantity, ImageView itemAction) {
        super(itemView);
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemAction = itemAction;
    }
}
