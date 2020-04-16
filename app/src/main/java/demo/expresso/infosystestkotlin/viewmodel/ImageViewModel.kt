package demo.expresso.infosystestkotlin.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import demo.expresso.infosystestkotlin.model.ImageData
import demo.expresso.infosystestkotlin.network.ApiClient
import demo.expresso.infosystestkotlin.network.ApiService
import demo.expresso.infosystestkotlin.network.ApiUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageViewModel(application: Application) : AndroidViewModel(application) {

    var errorMessage = MutableLiveData<String>()
    var progressFlag = MutableLiveData<Boolean>()
    var imageDataMutableLiveData = MutableLiveData<ImageData>()


    fun getImageData(context: Context) {
        val apiService = ApiClient.getClient(context)!!.create(ApiService::class.java)
        val call = apiService.getImageData(ApiUrl.BASE_URL + ApiUrl.Images)
        call.enqueue(object : Callback<ImageData> {
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.d("Faild", t.localizedMessage!!)
                errorMessage.postValue("Something wrong-\n" + t.localizedMessage + "\n\nPlease check Internet connection!!")
                progressFlag.postValue(false)
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                System.out.print("Response:\n" + response.body())
                imageDataMutableLiveData.postValue(response.body())
            }

        })
    }


}

