package demo.expresso.infosystestkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageData {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rows")
    @Expose
    var rows: List<Row>? = null

}