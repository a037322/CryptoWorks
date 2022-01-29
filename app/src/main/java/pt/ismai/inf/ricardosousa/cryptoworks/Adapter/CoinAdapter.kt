package pt.ismai.inf.ricardosousa.cryptoworks.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coin_layout.view.*
import pt.ismai.inf.ricardosousa.cryptoworks.HomeActivity
import pt.ismai.inf.ricardosousa.cryptoworks.Interface.ILoadMore
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CoinModel
import pt.ismai.inf.ricardosousa.cryptoworks.R


class CoinViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var coinIcon = itemView.coin_icon
    var coinSymbol = itemView.coin_symbol
    var coinName = itemView.coin_name
    var coinPrice = itemView.price_usd
    var oneHourChange = itemView.one_hour
    var twentyFourHourChange = itemView.twenty_four_hour
    var sevenDayChange = itemView.seven_days
}

class CoinAdapter(recyclerView: RecyclerView,internal var activity: HomeActivity,var items:MutableList<CoinModel>):
    RecyclerView.Adapter<CoinViewHolder>()
{
    internal var loadMore:ILoadMore?=null
    var isLoading:Boolean=false
    var visibleThreshold=5
    var lastVisibleItem:Int=0
    var totalItemCount:Int=0

    init {
        val linearLayout = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayout.itemCount
                lastVisibleItem = linearLayout.findLastVisibleItemPosition()
                if (loadMore != null)
                    loadMore!!.onLoadMore()
                isLoading = true
            }
        })
    }

    fun setLoadMore(loadMore:ILoadMore)
    {
        this.loadMore = loadMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.coin_layout,parent, false)
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
        item.coinPrice.text = coinModel.quote.quoteUsd.toString()
        item.oneHourChange.text = "${percent_change_1h}%"
        item.twentyFourHourChange.text = "${percent_change_24h}%"
        item.sevenDayChange.text = "${percent_change_7d}%"
        /*
        Picasso.with(activity.baseContext)
            .load(StringBuilder(Common.imageURL)
            .append(coinModel.symbol!!.toLowerCase())
                .append(".png")
                .toString())
         */
        /*item.oneHourChange.setText(
            if()
            {
                Color.parseColor("#FF0000")
            }
            else
            {
                Color.parseColor("#32CD32")
            }
        )
        item.twentyFourHourChange.setText(
            if(percent_change_24h < 0)
            {
                Color.parseColor("#FF0000")
            }
            else
            {
                Color.parseColor("#32CD32")
            }
        )
        item.sevenDayChange.setText(
            if(percent_change_7d < 0)
            {
                Color.parseColor("#FF0000")
            }
            else
            {
                Color.parseColor("#32CD32")
            }
        )*/
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun setLoaded() {
        isLoading = false
    }

    fun updateData(coinModels: List<CoinModel>){
        val previousSize = items.size
        setLoaded()
        items.addAll(coinModels)
        notifyItemRangeInserted(previousSize, coinModels.size)
    }

}