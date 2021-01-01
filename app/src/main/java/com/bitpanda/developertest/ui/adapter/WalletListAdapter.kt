package com.bitpanda.developertest.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.bitpanda.developertest.R
import com.bitpanda.developertest.ext.getCurrencyFormatValue
import com.bitpanda.developertest.model.Cryptocoin
import com.bitpanda.developertest.model.Fiat
import com.bitpanda.developertest.model.Metal
import com.bitpanda.developertest.repository.WalletData
import java.text.NumberFormat
import java.util.*


class WalletListAdapter(val onItemSelected: (WalletData) -> Unit) :
    ListAdapter<WalletData, WalletListAdapter.WalletListViewHolder>(WalletListDiffUtil()) {

    class WalletListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.wallet_icon)
        val name: TextView = itemView.findViewById(R.id.name)
        val value: TextView = itemView.findViewById(R.id.value)
        val symbol: TextView = itemView.findViewById(R.id.symbol)
        val balance: TextView = itemView.findViewById(R.id.balance)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_walllet, parent, false)
        return WalletListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletListViewHolder, position: Int) {
        when (currentList[position].walletItem) {
            is Fiat -> {
                holder.symbol.text = ((currentList[position].walletItem) as Fiat).symbol
                holder.value.text = ""
                SvgLoader.pluck()
                    .with(holder.itemView.context as Activity?)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(((currentList[position].walletItem) as Fiat).logo, holder.icon)
            }
            is Cryptocoin -> {
                holder.symbol.text = ((currentList[position].walletItem) as Cryptocoin).symbol
                holder.value.text =
                    ((currentList[position].walletItem) as Cryptocoin).price.getCurrencyFormatValue()

                SvgLoader.pluck()
                    .with(holder.itemView.context as Activity?)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(((currentList[position].walletItem) as Cryptocoin).logo, holder.icon)
            }
            is Metal -> {
                holder.symbol.text = ((currentList[position].walletItem) as Metal).symbol
                holder.value.text =
                    ((currentList[position].walletItem) as Metal).price.getCurrencyFormatValue()
                SvgLoader.pluck()
                    .with(holder.itemView.context as Activity?)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(((currentList[position].walletItem) as Metal).logo, holder.icon)
            }
        }
        holder.name.text = currentList[position].name
        holder.balance.text = currentList[position].balance.getCurrencyFormatValue()
        holder.itemView.tag = currentList[position]
        holder.itemView.setOnClickListener {
            onItemSelected(it.tag as WalletData)
        }
    }

    class WalletListDiffUtil : DiffUtil.ItemCallback<WalletData>() {
        override fun areItemsTheSame(oldItem: WalletData, newItem: WalletData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: WalletData, newItem: WalletData): Boolean {
            return oldItem.equals(newItem)
        }

    }
}