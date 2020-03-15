package com.panaceasoft.pskotlinmaterial.activity.application.user.profile

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.panaceasoft.pskotlinmaterial.R
import com.panaceasoft.pskotlinmaterial.`object`.UserProfile
import com.panaceasoft.pskotlinmaterial.adapter.application.user.profile.AppUserProfile3UserAdapter
import com.panaceasoft.pskotlinmaterial.repository.userprofile.UserProfileRepository
import com.panaceasoft.pskotlinmaterial.utils.Utils
import kotlinx.android.synthetic.main.app_user_profile_3_activity.*

class AppUserProfile3Activity : AppCompatActivity() {

    private lateinit var appUserProfile3UserAdapter: AppUserProfile3UserAdapter
    private lateinit var userProfileList: List<UserProfile>

    //region override functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_user_profile_3_activity)

        initData()

        initUI()

        initDataBinding()

        initActions()
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
        userProfileList = UserProfileRepository.profileList
    }

    private fun initDataBinding() {

        friendsRecyclerView.adapter = appUserProfile3UserAdapter
    }

    private fun initUI() {

        appUserProfile3UserAdapter = AppUserProfile3UserAdapter(userProfileList)

        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
        val id = R.drawable.profile2
        Utils.setCircleImageToImageView(applicationContext, profileImageView, id, 20, R.color.md_white_1000)

        val coverId = R.drawable.sg_clarke_quay
        val coverUserImageView = findViewById<ImageView>(R.id.coverUserImageView)
        Utils.setImageToImageView(applicationContext, coverUserImageView, coverId)

        val gallery1ImageView = findViewById<ImageView>(R.id.gallery1ImageView)
        Utils.setImageToImageView(applicationContext, gallery1ImageView, R.drawable.sg_gardens_by_the_bay_singapore)

        val gallery2ImageView = findViewById<ImageView>(R.id.gallery2ImageView)
        Utils.setImageToImageView(applicationContext, gallery2ImageView, R.drawable.dir_cat_1)

        val gallery3ImageView = findViewById<ImageView>(R.id.gallery3ImageView)
        Utils.setImageToImageView(applicationContext, gallery3ImageView, R.drawable.car_1)

        val gallery4ImageView = findViewById<ImageView>(R.id.gallery4ImageView)
        Utils.setImageToImageView(applicationContext, gallery4ImageView, R.drawable.sg_sentosa)

        val gallery5ImageView = findViewById<ImageView>(R.id.gallery5ImageView)
        Utils.setImageToImageView(applicationContext, gallery5ImageView, R.drawable.dir_cat_4)

        val gallery6ImageView = findViewById<ImageView>(R.id.gallery6ImageView)
        Utils.setImageToImageView(applicationContext, gallery6ImageView, R.drawable.dir_cat_3)

        val gallery7ImageView = findViewById<ImageView>(R.id.gallery7ImageView)
        Utils.setImageToImageView(applicationContext, gallery7ImageView, R.drawable.dir_cat_5)


        val mLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        friendsRecyclerView.layoutManager = mLayoutManager
        friendsRecyclerView.itemAnimator = DefaultItemAnimator()
        friendsRecyclerView.isNestedScrollingEnabled = false

    }

    private fun initActions() {

        appUserProfile3UserAdapter.setOnItemClickListener(object : AppUserProfile3UserAdapter.OnItemClickListener{
            override fun onItemClick(view: View, obj: UserProfile, position: Int) {
                Toast.makeText(applicationContext, "Clicked " + obj.name, Toast.LENGTH_SHORT).show()
            }
        })

        aboutViewAllTextView.setOnClickListener { Toast.makeText(this, "Clicked About View All.", Toast.LENGTH_SHORT).show() }

        friendsViewAllTextView.setOnClickListener { Toast.makeText(this, "Clicked Friends View All.", Toast.LENGTH_SHORT).show() }

        postsViewAllTextView.setOnClickListener { Toast.makeText(this, "Clicked Posts View All.", Toast.LENGTH_SHORT).show() }

        closeImageView.setOnClickListener { this.finish() }

        shareImageView.setOnClickListener { Toast.makeText(this, "Clicked Share.", Toast.LENGTH_SHORT).show() }

    }

    //endregion

}