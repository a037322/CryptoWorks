package pt.ismai.inf.ricardosousa.cryptoworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import pt.ismai.inf.ricardosousa.cryptoworks.Adapter.CoinAdapter
import pt.ismai.inf.ricardosousa.cryptoworks.Common.Common
import pt.ismai.inf.ricardosousa.cryptoworks.Interface.ILoadMore
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CoinModel
import pt.ismai.inf.ricardosousa.cryptoworks.Model.CryptoWorksResponse

class HomeActivity : AppCompatActivity(),ILoadMore {

    internal var items:MutableList<CoinModel> = ArrayList()
    internal lateinit var adapter: CoinAdapter
    val httpClient: HttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer {

            }
        }
    }

    override fun onLoadMore() {
        if (items.size <= Common.MAX_COIN_LOAD){
            loadCoinsCallback()
        }
        else {
            Toast.makeText(this@HomeActivity, "O Máximo de Dados é"+Common.MAX_COIN_LOAD,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        /*val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

         bottomNavigationView.setOnItemSelectedListener {
             when(it.itemId){
                 R.id.buy_icon -> {

                 }
                 R.id.home_icon -> {

                 }
                 R.id.profile_icon -> {

                 }
             }
             true*/
        swipe_to_refresh.post(this::loadCoinsCallback)

        swipe_to_refresh.setOnRefreshListener(this::loadCoinsCallback)

        val linearLayoutManager = LinearLayoutManager(this)
        coin_recycler_view.layoutManager = linearLayoutManager
        adapter = CoinAdapter(coin_recycler_view, linearLayoutManager, items)
        coin_recycler_view.adapter = adapter
        setUpAdapter()
    }


    private suspend fun loadCoins(start: Int, limit:Int)= coroutineScope {
        async {
            httpClient.request<CryptoWorksResponse> {
                url("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
                headers{
                    append("X-CMC_PRO_API_KEY", "fbc98735-5f87-4bb5-b1eb-40a90b4f0d3b")
                }
                parameter("start", start)
                parameter("limit", limit)
                method = HttpMethod.Get
            }
        }.await()
    }

    private fun loadCoinsCallback() {
        val response = runBlocking {
            loadCoins(start = items.size + 1, limit = 10)
        }
        swipe_to_refresh.isRefreshing=true
        runOnUiThread {
            adapter.updateData(response.data)

            swipe_to_refresh.isRefreshing = false
        }
        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter.setLoadMore(this)
    }
}