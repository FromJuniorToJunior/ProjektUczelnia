package com.example.projektuczelnia.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.projektuczelnia.data.Employee
import com.example.projektuczelnia.databinding.EmployeeBinding
import java.util.Base64

class EmployeesAdapter(private val employees: List<Employee>) :
    RecyclerView.Adapter<EmployeesAdapter.EmployeeHolder>() {
    inner class EmployeeHolder(binding: EmployeeBinding) : ViewHolder(binding.root) {
        val name = binding.name
        var surname = binding.surname
        val image = binding.imageView
        val addButton = binding.button
        val fatherName = binding.textView20
        val hireDate = binding.textView21
        val age = binding.textView22
        val seniority = binding.textView23
        val active = binding.textView24
        val retired = binding.textView25
        val likeBananas = binding.textView26
        val label1 = binding.textView2
        val label2 = binding.textView4
        val label3 = binding.textView7
        val label4 = binding.textView10
        val label5 = binding.textView12
        val label6 = binding.textView13
        val label7 = binding.textView14
        val label8 = binding.textView18
        val label9 = binding.textView19
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val employeeBinding = EmployeeBinding.inflate(inflater, parent, false)
        return EmployeeHolder(employeeBinding)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        val surname: String = employees[position].surname
        val name: String = employees[position].name
        val age: String = employees[position].advanced.age.toString()
        val seniority: String = employees[position].advanced.seniority.toString()
        val fastherName: String = employees[position].advanced.fatherName
        val retired: String = employees[position].advanced.retired.toString()
        val likeBananas: String =
            employees[position].advanced.likeBananas.toString()
        val hireDate: String = employees[position].advanced.hireDate
        val active: String = employees[position].advanced.active.toString()
        val fullName: String = name + " " + surname
        holder.name.text = name
        holder.surname.text = surname
        holder.active.text = active
        holder.age.text = age
        holder.seniority.text = seniority
        holder.fatherName.text = fastherName
        holder.retired.text = retired
        holder.likeBananas.text = likeBananas
        holder.hireDate.text = hireDate
        holder.addButton.text = fullName
        val imgbyte = Base64.getMimeDecoder().decode(
            employees[position].advanced.img
        )
        val bitMap: Bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.size)
        holder.image.setScaleType(ImageView.ScaleType.FIT_XY)
        holder.image.setImageBitmap(bitMap)
        holder.addButton.setOnClickListener {
            if (holder.image.isVisible) {
                holder.image.visibility = View.GONE
                holder.name.visibility = View.GONE
                holder.surname.visibility = View.GONE
                holder.age.visibility = View.GONE
                holder.active.visibility = View.GONE
                holder.hireDate.visibility = View.GONE
                holder.likeBananas.visibility = View.GONE
                holder.retired.visibility = View.GONE
                holder.fatherName.visibility = View.GONE
                holder.seniority.visibility = View.GONE
                holder.label1.visibility = View.GONE
                holder.label2.visibility = View.GONE
                holder.label3.visibility = View.GONE
                holder.label4.visibility = View.GONE
                holder.label5.visibility = View.GONE
                holder.label6.visibility = View.GONE
                holder.label7.visibility = View.GONE
                holder.label8.visibility = View.GONE
                holder.label9.visibility = View.GONE

            } else {
                holder.image.visibility = View.VISIBLE
                holder.name.visibility = View.VISIBLE
                holder.surname.visibility = View.VISIBLE
                holder.age.visibility = View.VISIBLE
                holder.active.visibility = View.VISIBLE
                holder.hireDate.visibility = View.VISIBLE
                holder.likeBananas.visibility = View.VISIBLE
                holder.retired.visibility = View.VISIBLE
                holder.fatherName.visibility = View.VISIBLE
                holder.seniority.visibility = View.VISIBLE
                holder.label1.visibility = View.VISIBLE
                holder.label2.visibility = View.VISIBLE
                holder.label3.visibility = View.VISIBLE
                holder.label4.visibility = View.VISIBLE
                holder.label5.visibility = View.VISIBLE
                holder.label6.visibility = View.VISIBLE
                holder.label7.visibility = View.VISIBLE
                holder.label8.visibility = View.VISIBLE
                holder.label9.visibility = View.VISIBLE

            }
        }


    }
}