package com.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.paging.databinding.ActivityMainBinding
import com.paging.paging.QuotePagingAdapter
import com.paging.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var quotePagingAdapter: QuotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpAdapter() {
        binding.rvQuotes.apply {
            adapter = quotePagingAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun setUpViewModel() {
        val quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        lifecycleScope.launch {
            quoteViewModel.quoteList.collect{
                quotePagingAdapter.submitData(it)
            }
        }
    }
}