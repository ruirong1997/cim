package com.project.order.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.skydoves.powermenu.MenuBaseAdapter;

public class CenterMenuAdapter extends MenuBaseAdapter<String> {

    public CenterMenuAdapter() {
        super();
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
//    final Context context = viewGroup.getContext();

//    if (view == null) {
//      LayoutInflater inflater = LayoutInflater.from(context);
//      assert inflater != null;
//      view = inflater.inflate(R.layout.item_title_menu, viewGroup, false);
//    }
//
//    String item = (String) getItem(index);
//    final TextView title = view.findViewById(R.id.item_title);
//    title.setText(item);
//    title.setTextColor(ContextCompat.getColor(context, R.color.md_grey_800));
        return super.getView(index, view, viewGroup);
    }

    @Override
    public void setSelectedPosition(int position) {
        notifyDataSetChanged();
    }
}

