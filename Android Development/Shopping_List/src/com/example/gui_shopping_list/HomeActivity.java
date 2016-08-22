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

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.content.Intent;

public class HomeActivity extends Activity  implements View.OnClickListener
{
	//Declaring
	private Button viewList;
	private ListView listView1;
	public ArrayList<Product> arrayList;
	private String title = null; 
	private String desc = null;
	private String price = null;
	public String name, budget, email;
	private Product clickedProduct = new Product(title, desc, price);
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		//Obtain extras from intent
		Bundle intentExtras = getIntent().getExtras();
		if (intentExtras == null)
		{
			return;
		}
		name = intentExtras.getString("name");
		budget = intentExtras.getString("budget");
		email = intentExtras.getString("email");
		//ArrayList of object Product
		//Reference: http://www.vogella.com/tutorials/AndroidListView/article.html
		this.arrayList = new ArrayList<Product>();
		//Reference complete
		TextView introText = (TextView) findViewById(R.id.introText);
		introText.setText("Click to add to the list, " + name + "!");
		TextView budgetText = (TextView) findViewById(R.id.budgetText);
		budgetText.setText("Budget: €" + budget);
		//TextView emailText = (TextView) findViewById(R.id.emailText);
		//emailText.setText(email);
		
		viewList=(Button)findViewById(R.id.viewList);
		viewList.setOnClickListener(this);
		
		//suggested to use final
		final Product values[] = new Product[]
		{
            new Product("Bread","Brennans White Sliced Pan", "1.99"),
            new Product("Milk","Avonmore Low Fat 2l", "1.49"),
            new Product("Cheese","Dubliner Red Cheddar", "2.99"),
            new Product("Eggs","Free Range Half Dozen", "1.79"),
            new Product("Ham","Dennys thinly sliced", "1.29"),
            new Product("Chips","McCains Oven Chips", "2.49")
        };
		//Used some information from this site regarding launching ListView clicks
		//Reference: http://forum.xda-developers.com/showthread.php?t=2265000
		
	    //Defining custom adapter
        CustomAdapter adapter = new CustomAdapter(this, 
                R.layout.row, values);
        
        listView1 = (ListView)findViewById(R.id.listView1);
        
        listView1.setAdapter(adapter);
        
        //Allow list to be clickable
        listView1.setOnItemClickListener(new OnItemClickListener() 
        {
        	public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) 
        	{
        		//Toast to say added
        		Toast.makeText(getApplicationContext(),"Item added to list", Toast.LENGTH_LONG).show();
        		//Text views for list rows
        		TextView txtTitle = (TextView)view.findViewById(R.id.txtTitle);
        		title = txtTitle.getText().toString();
        		TextView txtDesc = (TextView)view.findViewById(R.id.txtDesc);
        		desc = txtDesc.getText().toString();
        		TextView txtPrice = (TextView)view.findViewById(R.id.txtPrice);
        		price = txtPrice.getText().toString();
        		//Store clicked product details
        		clickedProduct.setTitle(title);
        		clickedProduct.setDesc(desc);
        		clickedProduct.setPrice(price);
        		arrayList.add(clickedProduct);
        		//Old implementation
        		//Intent listIntent = new Intent(HomeActivity.this, ListActivity.class);
    			//listIntent.putExtra(values.get(position), values.toString());
        	} 
         });
        
	}

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent(HomeActivity.this,ListActivity.class);
		Bundle b = new Bundle();
		//Serialized object to send to other activity
		b.putSerializable("ShoppingList", arrayList);
		//Tried to use parcel but failed
		//b.putParcelableArrayList("ShoppingList", arrayList);
		int _index = 0;
		b.putInt("index", _index);
		//Carry details across to final screen
		intent.putExtras(b);
		intent.putExtra("name", name);
		intent.putExtra("email", email);
		intent.putExtra("budget", budget);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
