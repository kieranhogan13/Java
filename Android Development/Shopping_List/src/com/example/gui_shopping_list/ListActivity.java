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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class ListActivity extends Activity implements View.OnClickListener
{
	private Button emailB;
	public ArrayList<Product> arrayList;
	public String email, subject, message;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		//Retrieve intent extras
		Bundle intentExtras = getIntent().getExtras();
		if (intentExtras == null)
		{
			return;
		}
		String name = intentExtras.getString("name");
		String budget = intentExtras.getString("budget");
		Double budget2 = null;
		email = intentExtras.getString("email");
		@SuppressWarnings("unused")
		double vatRate = 21/100;
		
		TextView headText = (TextView) findViewById(R.id.headText);
		headText.setText("Test the email button, " + name + "!");
		TextView budgetText = (TextView) findViewById(R.id.budgetText);
		budgetText.setText("Budget: €" + budget);
		
		//email stuff
		subject = "Shopping List";
		message = "This is the shopping list: \n";
		
		emailB=(Button)findViewById(R.id.emailB);
		emailB.setOnClickListener(this);
		
		//Take in ArrayList of objects Product
		Bundle b = this.getIntent().getExtras();
		//Pass in arraylist of objects
		arrayList = new ArrayList<Product>();
		arrayList = (ArrayList<Product>) b.get("ShoppingList");
		
		//Display Shopping List
		TextView title = (TextView) findViewById(R.id.txtTitle);
		TextView desc = (TextView) findViewById(R.id.txtDesc);
		TextView price = (TextView) findViewById(R.id.txtPrice);
		//Reference: http://javatechig.com/android/android-listview-tutorial
		String itemsTitle = new String();
		String itemsDesc = new String();
		String itemsPrice = new String();
		//Reference complete
		//Reference: http://stackoverflow.com/questions/6700717/how-to-iterate-through-an-array-list-arrayindexoutofboundsexception
		for(Product product : arrayList)
		{
			//Attempted solution to loop issue
			if((itemsTitle).equals(product.title))
			{
				break;
			}
			//Reference complete
			else
			{	//help format the product info
				itemsTitle += "\n| " + product.title;
				itemsDesc += "\nInfo: " + product.desc;
				itemsPrice += "\n €" + product.price;
			}
		}
		
		//Budget implementation, commented out due to non functional list
		//Accumulative total of all the items
		TextView budget2Text = (TextView) findViewById(R.id.budget2Text);
		budget2Text.setText("Total: €" + budget2);
		//vatAmount = budget2*vatRate
		
		//if (budget2 > budget)
		//{
			//headText.setText("Budget exceeded, remove items, " + name + "!");
		//}
		title.setText(itemsTitle);
		desc.setText(itemsDesc);
		price.setText(itemsPrice);
		
	}
		@Override
		public void onClick(View v) 
		{
			//Sending an email using intents:
			//Reference: http://javatechig.com/android/how-to-send-email-in-android
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			//passes email from login through
			emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			//If final list functioned properly, would pass in to message
			emailIntent.putExtra(Intent.EXTRA_TEXT, message);
			emailIntent.setType("message/rfc822");
			
			startActivity(Intent.createChooser(emailIntent, "Choose client"));
			//Reference complete
			
		}
		
		//Rough work and testing for List (found very difficult)
		
		//ArrayList<Product> data = b.getParcelableArrayList("product");
		//int index = b.getInt("index");
		
		//CustomAdapter adapter = new CustomAdapter(this, R.layout.listview_item_row, data);
        
        //listView1 = (ListView)findViewById(R.id.listView1);
		
        //listView1.setAdapter(adapter);

		//TextView emailText = (TextView) findViewById(R.id.emailText);
		//emailText.setText(email);
		//TextView mTextView = (TextView) findViewById(R.id.mTextView);
		//Product mProduct = (Product)getIntent().getParcelableExtra("ShoppingList");
		//ArrayList<Product> Product = getIntent().getParcelableArrayList("ShoppingList");
		//b.putParcelableArrayList("ShoppingList", data);
		//setContentView(mTextView);
		
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

