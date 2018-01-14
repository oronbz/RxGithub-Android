package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import bz.oron.rxgithub.R
import bz.oron.rxgithub.ui.extensions.inflate
import bz.oron.rxgithub.ui.transformations.RoundedTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * Created by oron on 1/11/18.
 */
class SearchAdapter(var viewModels: ArrayList<SearchItemViewModel>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
    val itemViewModel = viewModels[position]
    holder.bindViewModel(itemViewModel)
  }

  override fun getItemCount() = viewModels.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
    val inflatedView = parent.inflate(R.layout.item_search, false)
    return ViewHolder(inflatedView)
  }

  class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private var viewModel: SearchItemViewModel? = null

    init {
      view.setOnClickListener {
        Log.d("SearchRecyclerView", "Clicked on " + layoutPosition)
      }
    }

    fun bindViewModel(viewModel: SearchItemViewModel) {
      this.viewModel = viewModel
      Picasso.with(view.context)
          .load(viewModel.imageUrl)
          .transform(RoundedTransformation())
          .fit()
          .centerCrop()
          .placeholder(android.R.drawable.ic_menu_camera)
          .into(view.searchItemIv)
      
      view.searchItemTv.text = viewModel.username
    }
  }

}