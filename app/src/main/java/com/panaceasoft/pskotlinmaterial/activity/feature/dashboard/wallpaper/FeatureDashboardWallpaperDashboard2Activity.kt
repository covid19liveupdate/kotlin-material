package com.panaceasoft.pskotlinmaterial.activity.feature.dashboard.wallpaper

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.panaceasoft.pskotlinmaterial.R
import com.panaceasoft.pskotlinmaterial.`object`.WallpaperCategory
import com.panaceasoft.pskotlinmaterial.`object`.WallpaperItem
import com.panaceasoft.pskotlinmaterial.adapter.feature.dashboard.wallpaper.FeatureDashboardWallpaperDashboard2CategoryAdapter
import com.panaceasoft.pskotlinmaterial.adapter.feature.dashboard.wallpaper.FeatureDashboardWallpaperDashboard2ItemAdapter
import com.panaceasoft.pskotlinmaterial.repository.wallpaper.WallpaperCategoryRepository
import com.panaceasoft.pskotlinmaterial.repository.wallpaper.WallpaperItemRepository
import kotlinx.android.synthetic.main.feature_dashboard_wallpaper_dashboard_2_activity.*

class FeatureDashboardWallpaperDashboard2Activity : AppCompatActivity() {

    internal lateinit var itemArrayList: List<WallpaperItem>
    internal lateinit var categoryList: List<WallpaperCategory>
    internal lateinit var wallpaperHome2CategoryAdapter: FeatureDashboardWallpaperDashboard2CategoryAdapter
    internal lateinit var itemAdapter: FeatureDashboardWallpaperDashboard2ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature_dashboard_wallpaper_dashboard_2_activity)

        initData()

        initUI()

        initDataBinding()

        initActions()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_more, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    //endregion


    //region Init Functions

    private fun initData() {

        // get data
        itemArrayList = WallpaperItemRepository.wallpaperItemList

        categoryList = WallpaperCategoryRepository.wallpaperCategoryList

    }

    private fun initUI() {

        initToolbar()

        wallpaperHome2CategoryAdapter = FeatureDashboardWallpaperDashboard2CategoryAdapter(categoryList)

        itemAdapter = FeatureDashboardWallpaperDashboard2ItemAdapter(itemArrayList)

        val mLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        categoryRecyclerView.layoutManager = mLayoutManager
        categoryRecyclerView.itemAnimator = DefaultItemAnimator()
        categoryRecyclerView.isNestedScrollingEnabled = false

        val mLayoutManagerForItems = GridLayoutManager(applicationContext, 2)

        photoRecyclerView.layoutManager = mLayoutManagerForItems
        photoRecyclerView.itemAnimator = DefaultItemAnimator()
        photoRecyclerView.isNestedScrollingEnabled = false

    }

    private fun initDataBinding() {
        // bind wallpaperHome2CategoryAdapter to recycler
        categoryRecyclerView.adapter = wallpaperHome2CategoryAdapter

        // bind items
        photoRecyclerView.adapter = itemAdapter
    }

    private fun initActions() {
        wallpaperHome2CategoryAdapter.setOnItemClickListener(object : FeatureDashboardWallpaperDashboard2CategoryAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: WallpaperCategory, position: Int) {
                Toast.makeText(applicationContext, "Clicked " + obj.name, Toast.LENGTH_SHORT).show()
            }
        })
        itemAdapter.setOnItemClickListener(object : FeatureDashboardWallpaperDashboard2ItemAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: WallpaperItem, position: Int) {
                Toast.makeText(this@FeatureDashboardWallpaperDashboard2Activity, "Selected : " + obj.imageName, Toast.LENGTH_SHORT).show()
            }
        })

        viewAllCategoryTextView.setOnClickListener { Toast.makeText(applicationContext, "Clicked View All Categories.", Toast.LENGTH_SHORT).show() }

        viewAllPhotoTextView.setOnClickListener { Toast.makeText(applicationContext, "Clicked View All Wallpapers.", Toast.LENGTH_SHORT).show() }

    }


    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this,R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)

        }

        toolbar.title = "Wallpaper Dashboard 2"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }

    }

    //endregion
}
