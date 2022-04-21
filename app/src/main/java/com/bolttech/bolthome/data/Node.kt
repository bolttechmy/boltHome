package com.bolttech.bolthome.data

import com.google.gson.annotations.SerializedName

data class Node(
    @SerializedName("best_type")
    var best_type: String?,
    @SerializedName("best_category")
    var best_category: String?,
    @SerializedName("best_make")
    var best_make: String?,
    @SerializedName("best_os")
    var best_os: String?,
    @SerializedName("best_osver")
    var best_osver: String?,
    @SerializedName("recog_rank")
    var recog_rank: String?,
    @SerializedName("is_family")
    var is_family: String?,
    @SerializedName("best_name")
    var best_name: String?,
    @SerializedName("best_storage")
    var best_storage: String?,
    @SerializedName("mac_address")
    var mac_address: String?,
    @SerializedName("vendor")
    var vendor: String?
)
