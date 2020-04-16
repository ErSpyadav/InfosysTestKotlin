package demo.expresso.infosystestkotlin.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import demo.expresso.infosystestkotlin.R
import demo.expresso.infosystestkotlin.adapter.ImageAdapter
import demo.expresso.infosystestkotlin.model.ImageData
import demo.expresso.infosystestkotlin.viewmodel.ImageViewModel


class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mViewModel: ImageViewModel? = null
    private lateinit var progressBar: ProgressBar
    lateinit var errorMsgTv: TextView
    lateinit var imageAdapter: ImageAdapter
    var isRefresh = false
    private lateinit var pullToRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        errorMsgTv = findViewById(R.id.errorMsg) as TextView
        errorMsgTv.visibility = View.GONE
        progressBar = findViewById(R.id.progressBar_cyclic)
        recyclerView = findViewById(R.id.recycleView)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))

        refreshData()
        pullToRefresh = findViewById(R.id.pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            isRefresh = true
            refreshData() // load list on refresh
            progressBar.visibility = View.VISIBLE
            pullToRefresh.setRefreshing(false)

        }

        mViewModel!!.progressFlag.observe(this, Observer<Boolean> { aBoolean ->
            if (!aBoolean)
                progressBar.visibility = View.GONE
        })

        mViewModel!!.errorMessage.observe(this, Observer<String> { s ->
            errorMsgTv.visibility = View.VISIBLE
            errorMsgTv.text = s
        })

        mViewModel!!.imageDataMutableLiveData.observe(this, Observer<ImageData> { imageData ->
            this@MainActivity.supportActionBar!!.setTitle(imageData.title)
            imageData.let {
                imageAdapter = ImageAdapter(it.rows!!)
                recyclerView!!.setAdapter(imageAdapter)
                progressBar.visibility = View.GONE
            }

        })


    }

    fun refreshData() {
        progressBar.visibility = View.VISIBLE
        mViewModel!!.getImageData(applicationContext)
    }
}
