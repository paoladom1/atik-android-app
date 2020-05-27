package com.pdm.atikapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import  kotlinx.android.synthetic.main.fragment_special_orders.*


class SpecialOrdersFragment : Fragment() {

      val REQUEST_GALLERY = 1001


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        this.activity?.setTitle(R.string.view_special_orders_name)
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_special_orders, container, false)
        val ImgBtn = view.findViewById<Button>(R.id.buttonImagen)
       ImgBtn.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(context!!,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissionRecords = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissionRecords, REQUEST_GALLERY)
                } else {
                    showGallery()
                }
            } else {
                showGallery()
            }
        }
return view;
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_GALLERY ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    showGallery()
                else
                    Toast.makeText(context,"No puedes acceder a tus imagenes",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showGallery(){
      val intentGallery =  Intent(Intent.ACTION_PICK)
      intentGallery.type = "image/*"
      startActivityForResult(intentGallery,REQUEST_GALLERY)
  }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode ==  Activity.RESULT_OK && requestCode == REQUEST_GALLERY){
            imgPhoto.setImageURI(data?.data)
        }
    }

}





