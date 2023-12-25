package com.paging.model

data class Quotelist(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val lastItemIndex: Int,
    val results: List<Results>
)
