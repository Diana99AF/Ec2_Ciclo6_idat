package com.diana.ec2_aliaga_farro.ui.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.diana.ec2_aliaga_farro.R
import com.diana.ec2_aliaga_farro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Configurar el botón para agregar foto
        val addButton = view.findViewById<Button>(R.id.btn_add_photo)
        addButton.setOnClickListener {
            if (permissionValidated()) {
                openCamera()
            }
        }

        // Configurar el ActivityResultLauncher para la cámara
        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photo: Bitmap = result.data?.extras?.get("data") as Bitmap
                selectedImageBitmap = photo
                val imageView = view.findViewById<ImageView>(R.id.img_photo)
                imageView.setBackgroundColor(Color.TRANSPARENT)
                imageView.setImageBitmap(selectedImageBitmap)
            }
        }

        return view
    }
    private fun permissionValidated(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA)
        }
        if (permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(requireActivity(), permissionList.toTypedArray(),1000)
            return false
        }

        return true
    }
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    private var selectedImageBitmap: Bitmap? = null

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }


}