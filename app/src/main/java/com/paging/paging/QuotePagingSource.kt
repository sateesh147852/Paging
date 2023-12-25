package com.paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paging.model.Results
import com.paging.network.QuoteApiService
import javax.inject.Inject

class QuotePagingSource @Inject constructor(val apiService: QuoteApiService) :
    PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val position = params.key ?: 1
        val response = apiService.getQuotes(position)
        return try {
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}