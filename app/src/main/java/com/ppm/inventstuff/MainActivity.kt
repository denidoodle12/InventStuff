package com.ppm.inventstuff

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ppm.inventstuff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val deniedPermissions = permissions.filterValues { !it }

        if (deniedPermissions.isEmpty()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            // Lakukan tindakan yang membutuhkan izin di sini
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

    }

//    private fun hasPermission(permission: String): Boolean {
//        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun checkAndRequestPermissions() {
//        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            arrayOf(
//                android.Manifest.permission.READ_MEDIA_IMAGES,
//                android.Manifest.permission.READ_MEDIA_VIDEO
//            )
//        } else {
//            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//
//        val permissionsToRequest = permissions.filterNot { hasPermission(it) }
//
//        if (permissionsToRequest.isNotEmpty()) {
//            permissionLauncher.launch(permissionsToRequest.toTypedArray())
//        } else {
//            Toast.makeText(this, "All required permissions are already granted", Toast.LENGTH_SHORT).show()
//        }
//    }
}
