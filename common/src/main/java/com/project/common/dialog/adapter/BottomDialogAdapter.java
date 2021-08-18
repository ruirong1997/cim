package com.project.common.dialog.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.common.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BottomDialogAdapter extends RecyclerView.Adapter<BottomDialogAdapter.BottomDialogHolder> {

    private static final String TAG = "BottomDialogAdapter";

    private Context mContext;
    private List<String> mItem = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public BottomDialogAdapter(Context context, List<String> mItem){
        this.mContext = context;
        this.mItem = mItem;
    }


    @NonNull
    @Override
    public BottomDialogAdapter.BottomDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bottom_dialog,null);
        return new BottomDialogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomDialogAdapter.BottomDialogHolder holder, int position) {

        holder.mTextView.setText(mItem.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Onclick : " + mItem.get(position));
                mOnItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItem == null ? 0 : mItem.size();
    }

    public class BottomDialogHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public BottomDialogHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
