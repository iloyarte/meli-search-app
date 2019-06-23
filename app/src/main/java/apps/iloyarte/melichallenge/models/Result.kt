package apps.iloyarte.melichallenge.models

import java.io.Serializable


data class Result(
    val id: String,

    // will use
    val title: String,
    val address: Address,
    val permalink: String?,
    val thumbnail: String?,
    val sold_quantity: Int?,
    val currency_id: String?,
    val original_price: Float?,
    val price: Float,

    // Some extra display info
    val attributes: List<Attribute>,
    val location: Location,
    val condition: String?,
    val buying_mode: String?,

    val accepts_mercadopago: Boolean,
    val catalog_product_id: Any,
    val category_id: String?,
    val listing_type_id: String?,
    val official_store_id: Int?,
    val seller: Seller,
    val shipping: Shipping
) : Serializable

data class Address(
    val city_id: String?,
    val city_name: String?,
    val state_id: String?,
    val state_name: String?
) : Serializable


data class Location(
    val address_line: String?,
    val city: City,
    val country: Country,
    val latitude: Double,
    val longitude: Double,
    val neighborhood: Neighborhood,
    val state: State,
    val subneighborhood: Any,
    val zip_code: String?
) {
    data class Country(
        val id: String?,
        val name: String?
    )

    data class City(
        val id: String?,
        val name: String?
    )

    data class State(
        val id: String?,
        val name: String?
    )

    data class Neighborhood(
        val id: String?,
        val name: String?
    )
}

data class Shipping(
    val mode: String?,
    val logistic_type: String?,
    val free_shipping: Boolean,
    val store_pick_up: Boolean
)

data class Seller(
    val car_dealer: Boolean,
    val id: Int,
    val power_seller_status: String?,
    val real_estate_agency: Boolean
)

data class Attribute(
    val attribute_group_id: String?,
    val attribute_group_name: String?,
    val id: String?,
    val name: String?,
    val source: Double,
    val value_id: String?,
    val value_name: String?,
    val value_struct: Any
)
