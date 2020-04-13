package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> implements Filterable {

    List<Contact> contacts, contactListFiltered;
    Context context;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.contactListFiltered = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvFirstNameContacts.setText(contact.getContactFirstName());
        holder.tvLastNameContacts.setText(contact.getContactLastName());

    }

    @Override
    public int getItemCount() {
        if (contacts == null) return 0;
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contacts;
                } else {
                    List<Contact> filteredList = new ArrayList<>();
                    for (Contact row : contacts) {
                        if (row.getContactFirstName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    contactListFiltered = (ArrayList<Contact>) results.values;
                    notifyDataSetChanged();
            }
        };
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //        private ImageView ivImageContacts;
        private TextView tvFirstNameContacts;
        private TextView tvLastNameContacts;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
//            ivImageContacts = itemView.findViewById(R.id.ivImageContacts);
            tvFirstNameContacts = itemView.findViewById(R.id.tvFirstNameContacts);
            tvLastNameContacts = itemView.findViewById(R.id.tvLastNameContacts);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Contact contact = contacts.get(position);

            Intent intent = new Intent(itemView.getContext(), DetailContactActivity.class);
            intent.putExtra("firstName", contact.getContactFirstName());
            intent.putExtra("phoneNumber", contact.getContactPhone());
            itemView.getContext().startActivity(intent);
        }
    }


}
