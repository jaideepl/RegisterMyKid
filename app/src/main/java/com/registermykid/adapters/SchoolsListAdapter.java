package com.registermykid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.registermykid.R;
import com.registermykid.data.tos.SchoolData;

import java.util.ArrayList;

/**
 * Created by Jaideep.Lakshminaray on 27-02-2017.
 */

public class SchoolsListAdapter extends HeaderRecyclerViewAdapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<SchoolData> schoolDataArrayList;
    private OnSchoolItemClickListener onSchoolItemClickListener;

    public ArrayList<SchoolData> getGroupDataArrayList() {
        return schoolDataArrayList;
    }

    public void setGroupDataArrayList(ArrayList<SchoolData> groupDataArrayList) {
        this.schoolDataArrayList = groupDataArrayList;
    }

    public SchoolsListAdapter(Context mContext, ArrayList<SchoolData> schoolDataArrayList, OnSchoolItemClickListener onSchoolItemClickListener) {
        this.mContext = mContext;
        this.schoolDataArrayList = schoolDataArrayList;
        this.onSchoolItemClickListener = onSchoolItemClickListener;
    }

    @Override
    public int getItemCount() {
        int itemCount = getBasicItemCount();
        if (useHeader()) {
            itemCount += 1;
        }
        if (useFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public boolean useHeader() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_row, parent, false);

        return new GroupsListFooterViewHolder(itemView);
    }

    @Override
    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_row, parent, false);

        return new MyViewHolder(itemView, onSchoolItemClickListener);
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder holder, int position) {
        final SchoolData schoolData = schoolDataArrayList.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.bind(schoolData, onSchoolItemClickListener);

        myViewHolder.name.setText(schoolData.getName());
        myViewHolder.address.setText(schoolData.getAddress());
        Glide.with(mContext).load("")
                .thumbnail(0.5f).fitCenter().override(200, Target.SIZE_ORIGINAL).fitCenter()
                .crossFade().placeholder(R.drawable.img1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.thumbnailView);

        myViewHolder.contact_number_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + myViewHolder.contactNumberView.getText().toString().trim()));
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getBasicItemCount() {
        if (schoolDataArrayList != null) {
            return schoolDataArrayList.size();
        }
        return 0;
    }

    @Override
    public int getBasicItemType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView address;
        public ImageView thumbnailView;
        TextView contactNumberView;
        OnSchoolItemClickListener onSchoolItemClickListener;
        SchoolData schoolData;
        LinearLayout contact_number_layout;

        public MyViewHolder(View view, OnSchoolItemClickListener onSchoolItemClickListener) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_schoolname);
            address = (TextView) view.findViewById(R.id.tv_school_address);
            this.thumbnailView = view.findViewById(R.id.iv_school_thumbnail);
            this.onSchoolItemClickListener = onSchoolItemClickListener;
            this.contactNumberView = view.findViewById(R.id.tv_contact_number);
            this.contact_number_layout = view.findViewById(R.id.contact_number_layout);
        }

        public void bind(final SchoolData item, final OnSchoolItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSchoolClicked(item);
                }
            });
        }
    }

    public class GroupsListFooterViewHolder extends RecyclerView.ViewHolder {

        public GroupsListFooterViewHolder(View view) {
            super(view);
        }
    }

    public interface OnSchoolItemClickListener {
        void onSchoolClicked(SchoolData schoolData);
    }
}
