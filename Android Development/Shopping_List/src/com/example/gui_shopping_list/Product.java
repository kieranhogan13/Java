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

import java.io.Serializable;


@SuppressWarnings("serial")
public class Product implements Serializable
{	
	//Constructors
    public Product()
    {
        
    }
    //Constructors with parameters
    public Product(String title, String desc, String price) 
    {
        this.setTitle(title);
        this.setDesc(desc);
        this.setPrice(price);
    }
    
    //auto created
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Info of Product
    public String title;
    public String desc;
    public String price;
	
    //getters and setters just incase
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
		
}