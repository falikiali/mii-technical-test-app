package com.mii.techincaltest.app.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.mii.techincaltest.app.domain.model.Promo

data class PromosResponse(
    val data: List<DataPromosResponse>
)

data class DataPromosResponse(
    val id: Int,
    val attributes: AttributesDataPromosResponse
) {
    fun toDomain(): Promo {
        return Promo(
            id,
            attributes.title,
            attributes.desc,
            attributes.name,
            attributes.location
        )
    }
}

data class AttributesDataPromosResponse(
    val title: String,
    val desc: String,
    @SerializedName("nama") val name: String,
    @SerializedName("lokasi") val location: String
)