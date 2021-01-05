package com.akash.newsfuss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:newsItemClicked):RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> =ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        /*NOTES
      -- called when a viewHolder is created, called every time for all the lists in the recycle view
      -- adapter needs data, and it gets it from the constructor
      -- it is the function returning a NewsViewHolder
      -- so we just make an instance of the NewsViewHolder and return it.
      -- NewsViewHolder instance needs an itemView from the XML
      -- since the view is in XML form and we need it in View form, we use an LayoutInflator
      -- LayoutInflator basically converts the layout news_item into View
      -- To handle the clicks on the views we handle it in this very function
      */
        val view= LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewHolder= NewsViewHolder(view)
         view.setOnClickListener{
             //we can set up what happens on clicking here but the task of handling the clicks shouldn't be
             //of the adapter but the activity itself
             //for the activity to know that the button has been clicked, we need a callback and so we create an interface
             listener.onItemClicked(items[viewHolder.adapterPosition])
         }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

    /*NOTES
    --binds the data to the viewHolder
    --it gets a position and that tells this functions which itme is to be binded
    --so we retrieve the item on that position
    --remember that items is an arraylist of strings
    --and then we go back to the MainActiviy, and link this Adapter to the RecyclerView
    */
        val currentItem= items[position]
        holder.titleView.text= currentItem.title
        holder.authorView.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)



    }

    override fun getItemCount(): Int {
        // TODO("Not yet implemented")
        //gives the count of the number of items currently in the recycler view
        return items.size

    }
    fun updateNews(updatedNews:ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
        //this function basically calls all the internal Adapter Functions again

    }
}
class NewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
{
    //itemView is the complete news_item layout and from it we retrieve the title view
    val imageView: ImageView= itemView.findViewById(R.id.image)
    val titleView: TextView= itemView.findViewById(R.id.title)
    val authorView: TextView= itemView.findViewById(R.id.author)

//just the title from the news_item layout that we created.
    //basically news_item layout is the layout of one item of the recycler view
    // in the activity_main
    //now we can pass this ViewHolder to the ViewAdapter

}
interface newsItemClicked{
    fun onItemClicked(item:News)
    {

    }

}