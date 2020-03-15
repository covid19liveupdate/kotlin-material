package com.panaceasoft.pskotlinmaterial.adapter.feature.dashboard.directory

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.panaceasoft.pskotlinmaterial.R
import com.panaceasoft.pskotlinmaterial.`object`.DirectoryHome7Category
import com.panaceasoft.pskotlinmaterial.utils.Utils
import kotlinx.android.synthetic.main.feature_dashboard_directory_dashboard_7_item.view.*
import java.util.*

/**
 * Created by Panacea-Soft on 15/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
class FeatureDashboardDirectoryDashboard7Adapter(private val catArrayList: ArrayList<DirectoryHome7Category>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    private var context: Context? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: DirectoryHome7Category, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feature_dashboard_directory_dashboard_7_item, parent, false)

        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(reholder: RecyclerView.ViewHolder, position: Int) {

        if (reholder is PlaceViewHolder) {
            val cats = catArrayList[position]

            reholder.catNameTextView.text = cats.name

            context = reholder.catImageView.context

            val idCatImage = Utils.getDrawableInt(context!!, cats.image)
            Utils.setImageToImageView(context!!, reholder.catImageView, idCatImage)

            Utils.setCircleImageToImageView(context!!, reholder.catbackgroundImageView, R.drawable.white_background, 0, 0)

            val color = Color.parseColor(cats.color)
            reholder.catbackgroundImageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)

            reholder.catbackgroundImageView.setOnClickListener { view ->
                    itemClickListener.onItemClick(view, catArrayList[position], position)

            }

        }

    }

    override fun getItemCount(): Int {
        return catArrayList.size
    }

    inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var catImageView: ImageView
        var catNameTextView: TextView
        var catbackgroundImageView: ImageView

        init {
            catImageView = view.catImageView
            catNameTextView = view.catNameTextView
            catbackgroundImageView = view.catbackgroundImageView
        }
    }

}
