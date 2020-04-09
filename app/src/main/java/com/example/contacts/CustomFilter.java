package com.example.contacts;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {

    ContactsAdapter adapter;
    List<Contact> filterList;

    public CustomFilter(ContactsAdapter adapter, List<Contact> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            List<Contact> filteredContacts=new ArrayList<>();

            for(int i = 0; i<filterList.size(); i++){
                if(filterList.get(i).getContactFirstName().toUpperCase().contains(constraint))
                {
                    filteredContacts.add(filteredContacts.get(i));
                }
            }
            results.count = filteredContacts.size();
            results.values = filteredContacts;

        }else {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.contacts = (List<Contact>) results.values;
        adapter.notifyDataSetChanged();
    }
}
