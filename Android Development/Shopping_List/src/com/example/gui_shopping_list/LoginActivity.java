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
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;
import android.content.Intent;

public class LoginActivity extends Activity implements View.OnClickListener {

	private Button submit;
	@SuppressWarnings("unused")
	private Spinner gender;
	@SuppressWarnings("unused")
	private Spinner jobtitle;
	@SuppressWarnings("unused")
	private EditText name, age, email, password, budget;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//obtains gender and jobtitle spinner arrays
		Resources res = getResources();
		
		gender = (Spinner) findViewById(R.array.gender_list);
		String[] genders = res.getStringArray(R.array.gender_list);
		
		//Adapter for gender spinner
		//Reference: http://www.mkyong.com/android/android-spinner-drop-down-list-example/
		ArrayAdapter<String> genderAdapter; 
		genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
		genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		jobtitle = (Spinner) findViewById(R.array.job_list);
		String[] jobs = res.getStringArray(R.array.job_list);
		//Adapter for job spinner, learned from notes
		ArrayAdapter<String> jobAdapter;
		jobAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jobs);
		jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//Takes inputs for name, age, email and budget
		name = (EditText) findViewById(R.id.name);
		age = (EditText) findViewById(R.id.age);
		email = (EditText) findViewById(R.id.email);
		budget = (EditText) findViewById(R.id.budget);
		//Submit button
		submit=(Button)findViewById(R.id.SignUpButton);
		submit.setOnClickListener(this);
	}
	
	
	public void onClick(View v)
	{
		//Used for finding empty string inputs
		String noInput1 = name.getText().toString();
		String noInput2 = age.getText().toString();
		String noInput3 = email.getText().toString();
		String noInput4 = budget.getText().toString();

		
		//Error checking for name, can't be blank
		if (noInput1.matches("")) 
		{
		    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
		    return;
		}
		//Error checking for age, only accepts 0-99, can't be blank
		else if (noInput2.matches(""))
		{
		    Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
		    return;
		}
		//Error checking for email (just gives user email keyboard type, can't be blank
		else if (noInput3.matches(""))
		{
		    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
		    return;
		}
		//Error checking for budget, doesn't accept huge numbers, and can't be blank
		else if (noInput4.matches(""))
		{
		    Toast.makeText(this, "Please enter your budget", Toast.LENGTH_SHORT).show();
		    return;
		}
		//If all are in order, proceeds to intent the details to the next activity
		else
		{
			Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			intent.putExtra("name", name.getText().toString());
			intent.putExtra("email", email.getText().toString());
			intent.putExtra("budget", budget.getText().toString());
			startActivity(intent);
		}
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
