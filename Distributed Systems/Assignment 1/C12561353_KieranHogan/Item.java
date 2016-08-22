/*Kieran Hogan - C12561353 - DT228/4 - November 2015*/

import java.net.*;
import java.io.*;

/* Class for creating the Item objects, 
They are made up of the name, current bid,
reserve price, and index */
public class Item
{
    String itemName;
    int currentBid;
    int reservePrice;
    int index;

    public Item (String itemName_, int currentBid_, int reservePrice_, int index_)
    {
        itemName = itemName_; // Name of the item up for auction
        currentBid = currentBid_; // Current highest bid so far
        reservePrice = reservePrice_; // Take home price
        index = index_; // Stores location in list
    }
    public String getName() 
    { 
        return itemName; 
    }
    public int getCurrentBid() 
    { 
        return currentBid; 
    }  
    public int getReservePrice() 
    { 
        return reservePrice; 
    }
    public int getIndex()
    {
        return index;
    }

    // Use the two string to display the client and server side information
    @Override 
    public String toString() 
    {
        return ("Item: " + itemName + " | Current Bid: $" + currentBid + " | Reserve: $" + reservePrice);
    }
}