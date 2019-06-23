package apps.iloyarte.melichallenge.api.dto

import apps.iloyarte.melichallenge.models.Result

data class SearchResponse(
    val site_id: String,
    val query: String,
    val results: List<Result>,

    // Sort, paging, filters. May be added.
    val sort: Sort,
    val available_sorts: List<AvailableSort>,
    val paging: Paging,
    val available_filters: List<AvailableFilter>,
    val filters: List<Any>
)

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)

data class AvailableFilter(
    val id: String?,
    val name: String?,
    val type: String?,
    val values: List<Value>
) {
    data class Value(
        val id: String?,
        val name: String?,
        val results: Int
    )
}

data class AvailableSort(
    val id: String?,
    val name: String?
)


data class Sort(
    val id: String?,
    val name: String?
)
