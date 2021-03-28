package com.atef.clubhouse.ui.country

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atef.clubhouse.databinding.ItemCountryBinding
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.utils.ImageLoader
import com.atef.clubhouse.utils.adapterProperty
import com.atef.clubhouse.utils.textAsBitmap
import javax.inject.Inject

class CountriesAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (Country) -> Unit,
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    var items: List<Country> by adapterProperty(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader,
            onClick
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(
        private val binding: ItemCountryBinding,
        private val imageLoader: ImageLoader,
        val onClick: (Country) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            with(binding) {
                itemView.setOnClickListener { onClick(country) }
                val drawable =
                    BitmapDrawable(binding.root.context.resources, country.emojii.textAsBitmap(binding.root.context))
                imageLoader.loadImage(flag, drawable)
                name.text = country.name
                code.text = country.dialCode
            }
        }
    }

}