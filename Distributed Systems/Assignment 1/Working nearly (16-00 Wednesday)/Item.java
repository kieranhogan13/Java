public class Item
   {
      String itemName;
      int currentBid;
      int reservePrice;
      boolean isSold;
      int position;

      public Item (String itemName_, int currentBid_, int reservePrice_, int position_)
      {
         itemName = itemName_;
         currentBid = currentBid_;
         reservePrice = reservePrice_;
         boolean isSold = false;
         position = position_;
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

      public boolean getIsSold()
      {
        return isSold;
      }

      public int getPosition()
      {
         return position;
      }

      @Override
      public String toString() 
      {
         return ("Item: " + itemName +" | Sold: " + isSold + " | Current Bid: $" + currentBid + " | Reserve: $" + reservePrice + " |");
      }
   }