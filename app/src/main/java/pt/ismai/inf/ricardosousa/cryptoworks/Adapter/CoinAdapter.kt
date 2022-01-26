package pt.ismai.inf.ricardosousa.cryptoworks.Adapter

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coin_layout.view.*


class CoinViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var coinIcon = itemView.coin_icon
    var coinSymbol = itemView.coin
}