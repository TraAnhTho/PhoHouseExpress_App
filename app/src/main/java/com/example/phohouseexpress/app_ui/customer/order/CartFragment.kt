package com.example.phohouseexpress.app_ui.customer.order

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.phohouseexpress.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phohouseexpress.adapter.CartAdapter

class CartFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var btnCheckout: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rvCart)
        txtTotal = view.findViewById(R.id.txtTotal)
        btnCheckout = view.findViewById(R.id.btnCheckout)

        val adapter = CartAdapter(CartManager.getCartItems())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        txtTotal.text = "Tổng tiền: ${CartManager.getTotalPrice()}đ"

        btnCheckout.setOnClickListener {
            Toast.makeText(requireContext(), "Thanh toán thành công!", Toast.LENGTH_SHORT).show()
            CartManager.clearCart()
        }
    }
}
