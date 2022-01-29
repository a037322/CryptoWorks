package pt.ismai.inf.ricardosousa.cryptoworks.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coin_layout.view.*
import pt.ismai.inf.ricardosousa.cryptoworks.HomeActivity
import pt.ismai.inf.ricardosousa.cryptoworks.Interface.ILoadMore
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CoinModel
import pt.ismai.inf.ricardosousa.cryptoworks.R


class CoinAdapter(
    var items: MutableList<CoinModel>
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coinIcon: ImageView
        val coinSymbol: TextView
        val coinName: TextView
        val coinPrice: TextView
        val oneHourChange: TextView
        val twentyFourHourChange: TextView
        val sevenDayChange: TextView

        init {
            // Define click listener for the ViewHolder's View.
            coinIcon = itemView.findViewById(R.id.coin_icon)
            coinSymbol = itemView.findViewById(R.id.coin_symbol)
            coinName = itemView.findViewById(R.id.coin_name)
            coinPrice = itemView.findViewById(R.id.price_usd)
            oneHourChange = itemView.findViewById(R.id.one_hour)
            twentyFourHourChange = itemView.findViewById(R.id.twenty_four_hour)
            sevenDayChange = itemView.findViewById(R.id.seven_days)
        }
    }

    internal var loadMore: ILoadMore? = null
    var isLoading: Boolean = false
    var visibleThreshold = 5
    var lastVisibleItem: Int = 0
    var totalItemCount: Int = 0

    init {
        /*val linearLayout = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayout.itemCount
                lastVisibleItem = linearLayout.findLastVisibleItemPosition()
                if (loadMore != null)
                    loadMore!!.onLoadMore()
                isLoading = true
            }
        })*/
    }

    fun setLoadMore(loadMore: ILoadMore) {
        this.loadMore = loadMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coin_layout, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinModel = items.get(position)

        val item = holder as CoinViewHolder

        val percent_change_1h = coinModel.quote.quoteUsd.percent_change_1h
        val percent_change_24h = coinModel.quote.quoteUsd.percent_change_24h
        val percent_change_7d = coinModel.quote.quoteUsd.percent_change_7d

        item.coinName.text = coinModel.name
        item.coinSymbol.text = coinModel.symbol
        item.coinPrice.text = String.format("%.2f", coinModel.quote.quoteUsd.price)
        item.oneHourChange.text = String.format("%.2f%%", percent_change_1h)
        item.twentyFourHourChange.text = String.format("%.2f%%", percent_change_24h)
        item.sevenDayChange.text = String.format("%.2f%%", percent_change_7d)
        /*
        Picasso.with(activity.baseContext)
            .load(StringBuilder(Common.imageURL)
            .append(coinModel.symbol!!.toLowerCase())
                .append(".png")
                .toString())
         */

        item.oneHourChange.setTextColor(if(percent_change_1h < 0) Color.parseColor("#FF0000") else Color.parseColor("#32CD32"))

        item.twentyFourHourChange.setTextColor(if(percent_change_24h < 0) Color.parseColor("#FF0000") else Color.parseColor("#32CD32"))

        item.sevenDayChange.setTextColor(if(percent_change_7d < 0) Color.parseColor("#FF0000") else Color.parseColor("#32CD32"))
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun setLoaded() {
        isLoading = false
    }

    fun updateData(coinModels: List<CoinModel>) {
        val previousSize = items.size
        setLoaded()
        items.addAll(coinModels)
        notifyItemRangeInserted(previousSize, coinModels.size)
    }

}