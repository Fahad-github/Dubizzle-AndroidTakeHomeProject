package com.dubizzle.core.model

import com.squareup.moshi.Json
import java.io.Serializable

data class ItemModel(
    var created_at: String,
    var image_ids: List<String>,
    var image_urls: List<String>,
    var image_urls_thumbnails: List<String>,
    var name: String,
    var price: String,
    var uid: String
) : Serializable