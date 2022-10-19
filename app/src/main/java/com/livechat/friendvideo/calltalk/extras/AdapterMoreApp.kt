package com.livechat.friendvideo.calltalk.extras

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livechat.friendvideo.calltalk.R
import com.pesonal.adsdk.MoreApp_Data
import de.hdodenhof.circleimageview.CircleImageView

class AdapterMoreApp(var activity: Activity, var ListMore: List<MoreApp_Data>?) :
    RecyclerView.Adapter<AdapterMoreApp.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(
            R.layout.layout_more_app_item,
            parent,
            false
        )
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum = ListMore!![position]
        if (datum != null) {
            holder.tvTitle.text = datum.app_name
            Glide.with(activity).load(datum.app_logo).placeholder(R.mipmap.ic_launcher_round)
                .into(holder.ivLogo)
            holder.itemView.setOnClickListener {
                val appPackageName = datum.app_packageName
                try {
                    activity.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "market://details?id=$appPackageName"
                            )
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    activity.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                "https://play.google.com/store/apps/details?id=$appPackageName"
                            )
                        )
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (ListMore != null) {
            ListMore!!.size
        } else 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivLogo: CircleImageView
        var tvTitle: TextView

        init {
            ivLogo = itemView.findViewById(R.id.ivLogo)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvTitle.isSelected = true
        }
    }
}