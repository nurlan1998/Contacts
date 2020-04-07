package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<Contact> contacts;
    Context context;

    public ContactsAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
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
        return contacts.size();
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

            Intent intent = new Intent(itemView.getContext(),DetailContactActivity.class);
            intent.putExtra("firstName", contact.getContactFirstName());
            intent.putExtra("phoneNumber", contact.getContactPhone());
            itemView.getContext().startActivity(intent);
        }
    }

}
