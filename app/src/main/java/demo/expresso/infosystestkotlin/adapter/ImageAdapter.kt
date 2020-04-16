package demo.expresso.infosystestkotlin.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import demo.expresso.infosystestkotlin.R

import java.util.HashMap
import demo.expresso.infosystestkotlin.model.Row

class ImageAdapter(private val rowList: List<Row>) : RecyclerView.Adapter<ImageAdapter.MyHolder>() {

    private val bitmapHashMap: HashMap<String, Bitmap>

    init {
        bitmapHashMap = HashMap()
    }

    class MyHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var imageTitle: TextView
        internal var description: TextView
        internal var imageView: ImageView
        internal var divider: View

        init {
            imageTitle = v.findViewById(R.id.title)
            imageView = v.findViewById(R.id.image)
            description = v.findViewById(R.id.description)
            divider = v.findViewById(R.id.divider)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_image, parent, false)
        return MyHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.imageTitle.setText(rowList[position].title)
        holder.description.text = "Description :\n" + rowList[position].description
        Picasso.get().load(rowList[position].imageHref).placeholder(R.drawable.ic_empty_image)
            .into(holder.imageView)
        if (position == rowList.size - 1)
            holder.divider.visibility = View.GONE
        else
            holder.divider.visibility = View.VISIBLE

    }

    override fun getItemCount(): Int {
        return rowList.size;
    }



}
