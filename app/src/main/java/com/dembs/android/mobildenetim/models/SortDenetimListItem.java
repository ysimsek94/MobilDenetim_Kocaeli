package com.dembs.android.mobildenetim.models;

import java.util.Comparator;

public class SortDenetimListItem implements Comparator<DenetimLine> {
   // @Override

   // Method of this class
   // To compare datetime objects
   public int compare(DenetimLine a, DenetimLine b)
   {

       // Returning the value after comparing the objects
       // this will sort the data in Descending order
       return b.getDenetim().getDenetimTarihi().compareTo(a.getDenetim().getDenetimTarihi());
   }
}