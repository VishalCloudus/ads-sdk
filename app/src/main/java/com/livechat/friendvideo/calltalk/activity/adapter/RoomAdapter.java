package com.livechat.friendvideo.calltalk.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.livechat.friendvideo.calltalk.R;
import com.livechat.friendvideo.calltalk.activity.VipActivity;
import com.livechat.friendvideo.calltalk.extras.Constants;
import com.pesonal.adsdk.AppManage;
import com.preference.PowerPreference;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int AD_TYPE = 10;
    private final int CONTENT_TYPE = 20;
    private final Activity context;
    private final ArrayList<RoomModel> girlsArrayList;
    private final VipActivity.MyAdpCallback myAdpCallback1;

    public RoomAdapter(Activity context1, ArrayList<RoomModel> girlsArrayList1, VipActivity.MyAdpCallback myAdpCallback) {
        this.context = context1;
        this.girlsArrayList = girlsArrayList1;
        this.myAdpCallback1 = myAdpCallback;
    }

    @Override
    public int getItemViewType(int position) {
        if (PowerPreference.getDefaultFile().getBoolean(Constants.ADS_ONOFF)) {
            if (girlsArrayList.get(position).isAd()) {
                return AD_TYPE;
            } else {
                return CONTENT_TYPE;
            }
        } else {
            return CONTENT_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AD_TYPE) {
            return new ViewHolderr1(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_native_layout, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vip_room_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == AD_TYPE) {
            ViewHolderr1 footerHolder = (ViewHolderr1) holder;
            if (footerHolder.bg_frame1.getChildCount() <= 0) {
                if (PowerPreference.getDefaultFile().getBoolean(Constants.ADS_ONOFF)) {
                    AppManage.getInstance(context).showNative(footerHolder.bg_frame1);
                }
            }

            if (position == 0) {
                footerHolder.bg_frame1.setVisibility(View.GONE);
            } else {
                footerHolder.bg_frame1.setVisibility(View.VISIBLE);
            }

        } else if (viewType == CONTENT_TYPE) {
            ViewHolder myHold = (ViewHolder) holder;
            RoomModel item = girlsArrayList.get(position);

            myHold.room_name.setText(item.getRoom());
            myHold.item_desc.setText(item.getRoomDesc());

            Glide.with(context)
                    .load(item.getRoomImage())
                    .into(myHold.item_img);

            myHold.main_item.setOnClickListener(v ->
                    myAdpCallback1.callbackCallAdp()

            );
        }
    }

    @Override
    public int getItemCount() {
        return this.girlsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_img;
        TextView room_name, item_desc, item_online;
        RelativeLayout main_item;

        public ViewHolder(View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_image);
            room_name = itemView.findViewById(R.id.item_room);
            item_desc = itemView.findViewById(R.id.item_desc);
            item_online = itemView.findViewById(R.id.item_online);
            main_item = itemView.findViewById(R.id.main_card);

        }
    }


    class ViewHolderr1 extends RecyclerView.ViewHolder {
        LinearLayout bg_frame1;


        public ViewHolderr1(@NonNull View itemView) {
            super(itemView);
            bg_frame1 = itemView.findViewById(R.id.native_layout);

        }


    }

}
