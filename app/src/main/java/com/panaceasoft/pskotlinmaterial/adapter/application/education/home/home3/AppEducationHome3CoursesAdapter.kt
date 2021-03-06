package com.panaceasoft.pskotlinmaterial.adapter.application.education.home.home3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.panaceasoft.pskotlinmaterial.R
import com.panaceasoft.pskotlinmaterial.`object`.Course
import com.panaceasoft.pskotlinmaterial.utils.Utils
import kotlinx.android.synthetic.main.app_education_home_3_course_item.view.*

/**
 * Created by Panacea-Soft on 8/19/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


class AppEducationHome3CoursesAdapter(private val wallpaperCategoryArrayList: List<Course>?, limitCount: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    private var limitCount = 0

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Course, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    init {
        this.limitCount = limitCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.app_education_home_3_course_item, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val newsCategory = wallpaperCategoryArrayList!![position]

            viewHolder.titleTextView.text = newsCategory.title
            viewHolder.lengthTextView.text = "Course - " + newsCategory.length
            viewHolder.viewCountTextView.text = newsCategory.viewCount + " Viewers"

            val context = viewHolder.constraintLayout.context

            val id = Utils.getDrawableInt(context, newsCategory.image)
            Utils.setImageToImageView(context, viewHolder.courseImageView, id)


                viewHolder.constraintLayout.setOnClickListener { v: View -> itemClickListener.onItemClick(v, wallpaperCategoryArrayList[position], position) }

        }
    }

    override fun getItemCount(): Int {

        return if (wallpaperCategoryArrayList != null) {

            if (limitCount > 0) {
                if (limitCount > wallpaperCategoryArrayList.size) {
                    wallpaperCategoryArrayList.size
                } else {
                    limitCount
                }
            } else {
                wallpaperCategoryArrayList.size
            }

        } else {
            0
        }
    }

    inner class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var titleTextView: TextView = view.titleTextView
        internal var lengthTextView: TextView = view.lengthTextView
        internal var viewCountTextView: TextView = view.viewCountTextView
        internal var courseImageView: ImageView = view.courseImageView
        internal var bookmarkImageView: ImageView = view.bookmarkImageView
        internal var constraintLayout: ConstraintLayout = view.constraintLayout

    }
}

