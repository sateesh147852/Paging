package com.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.paging.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val quoteRepository: QuoteRepository) : ViewModel() {

    val quoteList = quoteRepository.getQuotes().flow.cachedIn(viewModelScope)
}