package com.example.phohouseexpress.app_ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phohouseexpress.R
import com.example.phohouseexpress.adapter.CategoryAdapter
import com.example.phohouseexpress.adapter.FoodAdapter
import com.example.phohouseexpress.utils.RetrofitClient
import com.example.phohouseexpress.utils.ApiService
import kotlinx.coroutines.launch

class MainHomeFragment : Fragment() {
    private lateinit var rvCategory: RecyclerView
    private lateinit var rvBestOffers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCategory = view.findViewById(R.id.rvCategory)
        rvBestOffers = view.findViewById(R.id.rvBestOffers)

        val categories = listOf("Phở", "Pizza", "Burger")
        rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = CategoryAdapter(categories)

        // Load API data
        lifecycleScope.launch {
            try {
                val api = RetrofitClient.instance.create(ApiService::class.java)
                val foods = api.getFoods()

                Log.d("API", "Số món ăn nhận được: ${foods.size}")

                view.post {
                    rvBestOffers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    rvBestOffers.adapter = FoodAdapter(foods)
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Không lấy được dữ liệu món ăn từ server", e)
            }
        }
    }
}
