package com.project.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.project.contacts.R;
import com.project.contacts.mode.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragmentAdapter extends RecyclerView.Adapter<ContactsFragmentAdapter.ContactsViewHolder>{

    private Context context;
    private List<Contacts> mContacts = new ArrayList<>();


    public ContactsFragmentAdapter(Context context, List<Contacts> friends){
        this.context = context;
        this.mContacts = friends;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_contacts,null);

        return new ContactsFragmentAdapter.ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contacts user = mContacts.get(position);
        //根据position获取首字母作为目录catalog
        String catalog = mContacts.get(position).getFirstLetter();
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            holder.catalog.setVisibility(View.VISIBLE);
            holder.catalog.setText(user.getFirstLetter().toUpperCase());
        }else{
            holder.catalog.setVisibility(View.GONE);
        }
        holder.name.setText(this.mContacts.get(position).getName());
        if (!mContacts.get(position).getImgUrl().equals("")){
            Glide.with(holder.itemView)
                    .load(mContacts.get(position).getImgUrl())
                    .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts == null ? 0 : mContacts.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;      // 姓名
        private TextView catalog;   //目录
        private ImageView imageView;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            name    = itemView.findViewById(R.id.user_name);
            catalog = itemView.findViewById(R.id.catalog);
            imageView = itemView.findViewById(R.id.userImg);

        }
    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mContacts.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

    public int getCount() {
        return this.mContacts.size();
    }

}
