package com.panaceasoft.pskotlinmaterial.activity.feature.basket

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.panaceasoft.pskotlinmaterial.R
import com.panaceasoft.pskotlinmaterial.`object`.Basket
import com.panaceasoft.pskotlinmaterial.adapter.feature.basket.FeatureBasketECommerceBasket1Adapter
import com.panaceasoft.pskotlinmaterial.repository.ecommerce.BasketItemRepository
import kotlinx.android.synthetic.main.feature_basket_ecommerce_basket_1_activity.*

class FeatureBasketECommerceBasket1Activity : AppCompatActivity() {

    // data and adapter
    internal lateinit var basketList: List<Basket>
    internal lateinit var adapter: FeatureBasketECommerceBasket1Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature_basket_ecommerce_basket_1_activity)

        initData()

        initUI()

        initDataBindings()

        initActions()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        // get place list
        basketList = BasketItemRepository.busketItemList
    }

    private fun initUI() {
        initToolbar()

        // get list adapter
        adapter = FeatureBasketECommerceBasket1Adapter(basketList)

        // get recycler view

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

    }

    private fun initDataBindings() {
        // bind adapter to recycler
        recyclerView.adapter = adapter
    }

    private fun initActions() {
        adapter.setOnItemClickListener(object : FeatureBasketECommerceBasket1Adapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Basket, position: Int) {
                Toast.makeText(applicationContext, "Clicked " + obj.name, Toast.LENGTH_SHORT).show()
            }

            override fun onDeleteClick(view: View, obj: Basket, position: Int) {
                Toast.makeText(applicationContext, "Clicked Delete. ", Toast.LENGTH_SHORT).show()
            }
        })

        checkoutButton.setOnClickListener {  Toast.makeText(this, "Clicked Checkout.", Toast.LENGTH_SHORT).show() }
    }

    //region Init Toolbar
    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24)

        if (toolbar.navigationIcon != null) {
           toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Basket 1"

        try {
          toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
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
}
