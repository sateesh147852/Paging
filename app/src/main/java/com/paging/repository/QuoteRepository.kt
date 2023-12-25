package com.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.paging.paging.QuotePagingSource
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quotePagingSource: QuotePagingSource) {

    fun getQuotes() =
        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 180),
            pagingSourceFactory = { quotePagingSource }
        )
}