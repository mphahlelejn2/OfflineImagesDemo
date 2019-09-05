package com.jeff.offlineimagesdemo.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import com.jeff.offlineimagesdemo.R
import com.jeff.offlineimagesdemo.base.BaseActivity
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.repo.Repository
import com.jeff.offlineimagesdemo.viewModel.ItemListViewModel
import com.jeff.offlineimagesdemo.util.Utils
import com.jeff.offlineimagesdemo.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.annotation.Nullable
import androidx.test.espresso.IdlingResource
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting


class MainActivity : BaseActivity() {

    private lateinit var itemListViewModel: ItemListViewModel
    private lateinit var itemsAdapter: ItemsAdapter
    @Inject
    lateinit var repository:Repository
    private lateinit var recyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        checkInternetConnection()
        initViewModel()
        initRecyclerView()
        initObserve()

    }

    private fun checkInternetConnection() {
        if(!Utils.isInternetConnected(this))
        showError(resources.getString(R.string.fix_your_internet_connection))
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory.getInstance(repository)
        itemListViewModel= ViewModelProviders.of(this,viewModelFactory).get(ItemListViewModel::class.java)
    }

    private fun initRecyclerView() {
        itemsAdapter=ItemsAdapter(arrayListOf(), this)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter

        }
    }

    private fun updateRecyclerViewData(articleList:List<Item>) {
        itemsAdapter.updateRecyclerViewData(articleList)
    }

    private fun initObserve() {
        showDialog()
        itemListViewModel.loadArticleList(Utils.isInternetConnected(this))
        itemListViewModel.getArticleList().observe(this, Observer {
                if (it != null) {
                    updateRecyclerViewData(it)
                    hideDialog()
                }
        })
        itemListViewModel.getErrorMsg().observe(this, Observer {
                it?.let {
                    hideDialog()
                    showError(resources.getString(R.string.Error_loading_Item_List)) }
        })

    }

}
