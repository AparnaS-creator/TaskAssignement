package com.assignment.wiproassignment.model.newlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewsListResponse {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rows")
    @Expose
    var rows: ArrayList<Row>? = null

    inner class Row:Serializable{
        @SerializedName("title")
        var title: String? = null
        @SerializedName("description")
        var description: String? = null
        @SerializedName("imageHref")
        var imageHref: String? = null
    }

}
