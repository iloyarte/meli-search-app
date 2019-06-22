package apps.iloyarte.melichallenge.models

import java.io.Serializable

data class Item(
    val id: String,
    val site_id: String,
    val title: String,

    // Seller profile, data model?
//    val seller: String,
    val price: Float,
    val currency_id: String,
//    val available_quantity: String,
//    val sold_quantity: String,
//    val buying_mode: String,
//    val listing_type_id: String,
//    val stop_time: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val accepts_mercadopago: Boolean,
//    val installments: String,
    val address: Address,
//    val shipping: String,
//    val seller_address: String,
//    val attributes: String,
    val original_price: Float?,
    val tags: List<String>
): Serializable

data class Address(
    val state_id: String,
    val state_name: String,
    val city_id: String,
    val city_name: String
): Serializable

/*
* {
      "id": "MLU459154242",
      "site_id": "MLU",
      "title": "Google Chromecast 3 Hdmi 1080p Nuevo Modelo Oferta Loi",
      "seller": {},
      "price": 53,
      "currency_id": "USD",
      "available_quantity": 250,
      "sold_quantity": 500,
      "buying_mode": "buy_it_now",
      "listing_type_id": "gold_special",
      "stop_time": "2038-12-08T22:04:19.000Z",
      "condition": "new",
      "permalink": "https://articulo.mercadolibre.com.uy/MLU-459154242-google-chromecast-3-hdmi-1080p-nuevo-modelo-oferta-loi-_JM",
      "thumbnail": "http://mlu-s1-p.mlstatic.com/699949-MLU28995929204_122018-I.jpg",
      "accepts_mercadopago": true,
      "installments": {},
      "address": {
            "state_id": "UY-MO",
            "state_name": "Montevideo",
            "city_id": "TUxVQ0NFTjVjMTM",
            "city_name": "Centro"
       },
      "shipping": {},
      "seller_address": {},
      "attributes": [],
      "original_price": 64.99,
      "category_id": "MLU176997",
      "official_store_id": 638,
      "catalog_product_id": null,
      "tags": []
    }
*
* */