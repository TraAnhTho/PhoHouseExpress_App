package com.example.phohouseexpress.app_ui.admin.menu

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.phohouseexpress.R
import com.example.phohouseexpress.viewmodel.AdminViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class AddFoodFragment : Fragment() {

    private lateinit var edtName: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtPromotion: EditText
    private lateinit var imagePreview: ImageView
    private lateinit var btnSelectImage: Button
    private lateinit var btnSubmit: Button

    private var imageUri: Uri? = null

    private val viewModel: AdminViewModel by viewModels()

    companion object {
        private const val IMAGE_PICK_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtName = view.findViewById(R.id.edtFoodName)
        edtPrice = view.findViewById(R.id.edtPrice)
        edtPromotion = view.findViewById(R.id.edtPromotion)
        imagePreview = view.findViewById(R.id.imagePreview)
        btnSelectImage = view.findViewById(R.id.btnSelectImage)
        btnSubmit = view.findViewById(R.id.btnSubmitFood)

        btnSelectImage.setOnClickListener {
            pickImageFromGallery()
        }

        btnSubmit.setOnClickListener {
            val name = edtName.text.toString()
            val price = edtPrice.text.toString().toDoubleOrNull()
            val promo = edtPromotion.text.toString().ifBlank { null }

            if (name.isBlank() || price == null || imageUri == null) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imageFile = copyUriToFile(imageUri!!)
            if (imageFile == null) {
                Toast.makeText(requireContext(), "Không thể lấy ảnh!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                viewModel.addFood(name, price, promo, imageFile)
                Toast.makeText(requireContext(), "Thêm món thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imagePreview.setImageURI(imageUri)
        }
    }

    private fun copyUriToFile(uri: Uri): File? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri) ?: return null
            val tempFile = File.createTempFile("upload_", ".jpg", requireContext().cacheDir)
            FileOutputStream(tempFile).use { output ->
                inputStream.copyTo(output)
            }
            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
