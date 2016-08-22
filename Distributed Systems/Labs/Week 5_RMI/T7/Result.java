class Result implements java.io.Serializable
{
   private String surname;
   private int mark;

   public Result(String name, int score)
   {
      surname = name;
      mark = score;
   }

   public String getName()
   {
      return surname;
   }

   public void setName(String name)
   {
      surname = name;
   }

   public int getMark()
   {
      return mark;
   }

   public void setMark(int score)
   {
      if ((score>=0) && (score<=100))
         mark = score;
   }
}
