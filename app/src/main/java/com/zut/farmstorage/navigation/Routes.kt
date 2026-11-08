package com.zut.farmstorage.navigation

object Routes {
    const val DASHBOARD = "dashboard"
    const val ADD_PRODUCE = "add_produce"
    const val RECORDS = "records"
    const val DETAILS = "details/{produceId}"
    const val MONITORING = "monitoring"

    fun details(id: Long) = "details/$id"
}
