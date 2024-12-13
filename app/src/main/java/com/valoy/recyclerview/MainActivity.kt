package com.valoy.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.valoy.recyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindRecyclerview()

        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    loadItemsIntoRecyclerview(uiState.items)
                }
            }
        }
    }

    private fun loadItemsIntoRecyclerview(dataset: List<Item>) {
        val customAdapter = CustomAdapter(dataset) {
            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT)
                .show()
        }

        binding.recyclerview.adapter = customAdapter
    }

    private fun bindRecyclerview() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recyclerview.addItemDecoration(ItemSpacingDecoration(24))
        binding.recyclerview.addItemDecoration(dividerItemDecoration)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }
}