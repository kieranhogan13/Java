//____________________
//					//
//  Kieran Hogan	//
//					//
//  C12561353 		//
//					//
//  DT228/Year3		//
//					//
//	GUI Assignment	//
//__________________//

package com.example.gui_shopping_list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Product>{

    Context context; 
    int layoutResourceId;    
    Product values[] = null;
    
    public CustomAdapter(Context context, int layoutResourceId, Product[] values) {
        super(context, layoutResourceId, values);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.values = values;
    }

    //Provides view for adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder holder = null;
        
        if(row == null)
        {
        	//Inflater needed for holder
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            //Holder smoothened out design
            //Reference: http://java.dzone.com/articles/android-listview-optimizations
            holder = new ProductHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtDesc = (TextView)row.findViewById(R.id.txtDesc);
            holder.txtPrice = (TextView)row.findViewById(R.id.txtPrice);
            row.setTag(holder);
            //Reference complete
        }
        else
        {
            holder = (ProductHolder)row.getTag();
        }
        
        Product product = values[position];
        holder.txtTitle.setText(product.getTitle());
        holder.txtDesc.setText(product.getDesc());
        holder.txtPrice.setText(product.getPrice());
        
        return row;
    }
    //Useful for layout
    static class ProductHolder
    {
        TextView txtTitle;
        TextView txtDesc;
        TextView txtPrice;
    }
}