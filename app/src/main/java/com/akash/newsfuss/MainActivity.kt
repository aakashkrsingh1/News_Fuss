package com.akash.newsfuss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), newsItemClicked {
    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager= LinearLayoutManager(this)
        //we link the Adapter to this RecyclerView
        //for data for creating an instance of the adapter class we create a function fetchData()
        fetchdata()
        mAdapter= NewsListAdapter(this)

        //adapter gets the data and then we link it to our recycler view
        recyclerView.adapter=mAdapter


    }
    fun fetchdata(){
        val url:String ="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                                  val newsJsonArray=it.getJSONArray("articles")
                    val newsArray= ArrayList<News>()
                    for(i in 0 until newsJsonArray.length())
                    {  //Toast.makeText(this,"There is response ",Toast.LENGTH_LONG).show()
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news:News= News(newsJsonObject.getString("title")
                            ,newsJsonObject.getString("author"),
                            newsJsonObject.getString("url"),
                            newsJsonObject.getString("urlToImage"))
                        newsArray.add(news)
                    }
                    mAdapter.updateNews(newsArray)
                },
                Response.ErrorListener {
                }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
    override fun  onItemClicked(item:News){
        //Toast.makeText(this,"$item clicked",Toast.LENGTH_SHORT).show()
        //should open a chrome tab on getting clicked

    }
}