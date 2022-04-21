package com.bolttech.bolthome.data

import com.google.gson.annotations.SerializedName


data class Device (

    @SerializedName("items" ) var items : ArrayList<Items> = arrayListOf()

)
data class Items (

    @SerializedName("principalId"              ) var principalId              : String?             = null,
    @SerializedName("fullName"                 ) var fullName                 : String?             = null,
    @SerializedName("email"                    ) var email                    : String?             = null,
    @SerializedName("countryCode"              ) var countryCode              : String?             = null,
    @SerializedName("partnerId"                ) var partnerId                : String?             = null,
    @SerializedName("devices"                  ) var devices                  : ArrayList<Devices>  = arrayListOf(),
    @SerializedName("customer"                 ) var customer                 : Customer?           = Customer(),
    @SerializedName("activePartnerSubsciption" ) var activePartnerSubsciption : Boolean?            = null,
    @SerializedName("findDevicesContent"       ) var findDevicesContent       : FindDevicesContent? = FindDevicesContent()

)

data class FindDevicesContent (

    @SerializedName("findDevices"       ) var findDevices       : FindDevices?       = FindDevices(),
    @SerializedName("addDeviceManually" ) var addDeviceManually : AddDeviceManually? = AddDeviceManually()

)
data class AddDeviceManually (

    @SerializedName("isToShowPopup"    ) var isToShowPopup    : Boolean? = null,
    @SerializedName("popupHeader"      ) var popupHeader      : String?  = null,
    @SerializedName("popupDescription" ) var popupDescription : String?  = null,
    @SerializedName("popupCTA"         ) var popupCTA         : String?  = null

)

data class FindDevices (

    @SerializedName("isToShowPopup"    ) var isToShowPopup    : Boolean? = null,
    @SerializedName("popupHeader"      ) var popupHeader      : String?  = null,
    @SerializedName("popupDescription" ) var popupDescription : String?  = null,
    @SerializedName("popupCTA"         ) var popupCTA         : String?  = null

)
data class Customer (

    @SerializedName("customerId"   ) var customerId   : String?                = null,
    @SerializedName("fullName"     ) var fullName     : String?                = null,
    @SerializedName("mobileNumber" ) var mobileNumber : String?                = null,
    @SerializedName("email"        ) var email        : String?                = null,
    @SerializedName("addressList"  ) var addressList  : ArrayList<AddressList> = arrayListOf()

)

data class AddressList (

    @SerializedName("address1"    ) var address1    : String? = null,
    @SerializedName("address2"    ) var address2    : String? = null,
    @SerializedName("city"        ) var city        : String? = null,
    @SerializedName("state"       ) var state       : String? = null,
    @SerializedName("countryCode" ) var countryCode : String? = null,
    @SerializedName("postalCode"  ) var postalCode  : String? = null,
    @SerializedName("addressType" ) var addressType : String? = null

)
data class Devices (

    @SerializedName("id"                     ) var id                     : String?           = null,
    @SerializedName("deviceType"             ) var deviceType             : String?           = null,
    @SerializedName("make"                   ) var make                   : String?           = null,
    @SerializedName("model"                  ) var model                  : String?           = null,
    @SerializedName("name"                   ) var name                   : String?           = null,
    @SerializedName("purchaseDate"           ) var purchaseDate           : String?           = null,
    @SerializedName("warrantyPeriod"         ) var warrantyPeriod         : Int?              = null,
    @SerializedName("extendedWarrantyPeriod" ) var extendedWarrantyPeriod : String?           = null,
    @SerializedName("purchasePrice"          ) var purchasePrice          : Double?           = null,
    @SerializedName("currency"               ) var currency               : String?           = null,
    @SerializedName("receiptId"              ) var receiptId              : String?           = null,
    @SerializedName("manuallyAddedDevice"    ) var manuallyAddedDevice    : Boolean?          = null,
    @SerializedName("totalDeviceWarranty"    ) var totalDeviceWarranty    : Int?              = null,
    @SerializedName("warrantyExpiryDate"     ) var warrantyExpiryDate     : String?           = null,
   // @SerializedName("offers"                 ) var offers                 : ArrayList<String> = arrayListOf(),
    @SerializedName("deviceStatus"           ) var deviceStatus           : String?           = null,
    @SerializedName("orders"                 ) var orders                 : ArrayList<Orders> = arrayListOf()

)
data class Orders (

    @SerializedName("orderId"     ) var orderId     : String? = null,
    @SerializedName("createdAt"   ) var createdAt   : String? = null,
    @SerializedName("orderStatus" ) var orderStatus : String? = null

)