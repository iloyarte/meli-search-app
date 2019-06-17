package apps.iloyarte.melichallenge.api

data class SearchResponse(
    val site_id: String,
    val query: String,
//    val related_results: List<Any>,
    val results: List<Result>,
//    val secondary_results: List<Any>,

    // Sort, paging, filters. May be added.
    val sort: Sort,
    val available_sorts: List<AvailableSort>,
    val paging: Paging,

    val available_filters: List<AvailableFilter>,
    val filters: List<Any>
)


data class Result(
    val id: String,

    // will use
    val title: String,
    val address: Address,
    val accepts_mercadopago: Boolean,
    val permalink: String,
    val thumbnail: String,
    val sold_quantity: Int,
    val currency_id: String,
    val original_price: Float?,
    val price: Int,

    // Some extra display info
    val attributes: List<Attribute>,

    val available_quantity: Int,
    val buying_mode: String,
    val catalog_product_id: Any,
    val category_id: String,
    val condition: String,
    val installments: Installments,
    val listing_type_id: String,
    val location: Location,
    val official_store_id: Int,
    val seller: Seller,
    val seller_address: SellerAddress,
    val seller_contact: SellerContact,
    val shipping: Shipping,
    val site_id: String,
    val stop_time: String,
    val tags: List<String>
) {
    data class Address(
        val city_id: String,
        val city_name: String,
        val state_id: String,
        val state_name: String
    )

    data class SellerContact(
        val area_code: String,
        val area_code2: String,
        val contact: String,
        val email: String,
        val other_info: String,
        val phone: String,
        val phone2: String,
        val webpage: String
    )

    data class Location(
        val address_line: String,
        val city: City,
        val country: Country,
        val latitude: Double,
        val longitude: Double,
        val neighborhood: Neighborhood,
        val state: State,
        val subneighborhood: Any,
        val zip_code: String
    ) {
        data class Country(
            val id: String,
            val name: String
        )

        data class City(
            val id: String,
            val name: String
        )

        data class State(
            val id: String,
            val name: String
        )

        data class Neighborhood(
            val id: String,
            val name: String
        )
    }

    data class Shipping(
        val free_shipping: Boolean,
        val logistic_type: String,
        val mode: String,
        val store_pick_up: Boolean,
        val tags: List<Any>
    )

    data class Seller(
        val car_dealer: Boolean,
        val id: Int,
        val power_seller_status: String,
        val real_estate_agency: Boolean,
        val tags: List<Any>
    )

    data class Attribute(
        val attribute_group_id: String,
        val attribute_group_name: String,
        val id: String,
        val name: String,
        val source: Int,
        val value_id: String,
        val value_name: String,
        val value_struct: Any
    )

    data class SellerAddress(
        val address_line: String,
        val city: City,
        val comment: String,
        val country: Country,
        val id: String,
        val latitude: String,
        val longitude: String,
        val state: State,
        val zip_code: String
    ) {
        data class Country(
            val id: String,
            val name: String
        )

        data class City(
            val id: String,
            val name: String
        )

        data class State(
            val id: String,
            val name: String
        )
    }

    data class Installments(
        val amount: Double,
        val currency_id: String,
        val quantity: Int,
        val rate: Int
    )
}

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)

data class AvailableFilter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
) {
    data class Value(
        val id: String,
        val name: String,
        val results: Int
    )
}

data class AvailableSort(
    val id: String,
    val name: String
)


data class Sort(
    val id: String,
    val name: String
)
