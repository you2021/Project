package com.example.project

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var saveViewModel: SaveViewModel
    lateinit var getModel: GetListModel
    lateinit var getTwoModel: GetListTwoModel
    lateinit var deleteModel: DeleteViewModel

    var adapter: ListAdapter? = null
    var twoAdapter: ListTwoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        saveViewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
        getModel = ViewModelProvider(this).get(GetListModel::class.java)
        getTwoModel = ViewModelProvider(this).get(GetListTwoModel::class.java)
        deleteModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        setContentView(binding.root)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)  // 키보드 올라올시 화면 밀려올라 가는것을 방지

        now()
        setObserver()
        clickDate()  // 검색시 등록 날짜
        add() // 추가 버튼 (저장할 수 있게 행이 추가 된다)
        list() // 리스트
        saveBtn() // 저장버튼
    }

    fun setObserver(){
        saveViewModel.result.observe(this, {
            if(it.status == "success") {
                list() // 리스트
                binding.ed.visibility = View.GONE
                binding.edTwo.visibility = View.GONE
                binding.lineVisible.visibility = View.GONE
                binding.edTxtComName.setText("")
                binding.edTxtSerial.setText("")
                binding.edTxtState.setText("")
                binding.edTextType.setText("")
                binding.edTxtUser.setText("")
                Log.d("TAG", "success")
            } else Log.d("TAG", "${it.code}")
        })

        getModel.result.observe(this, {
            if (it == null||it.size == 0) Log.d("TAG", "${it}")
            adapter = ListAdapter(it)

            binding.data.adapter = adapter
        })

        getTwoModel.result.observe(this, {
            if (it == null||it.size == 0) Log.d("TAG", "${it}")
            twoAdapter = ListTwoAdapter(it)

            binding.dataTwo.adapter = twoAdapter
        })

        deleteModel.result.observe(this, {
            if (it.status == "success"){
                list() // 리스트
            }
        })


    }

    fun clickDate(){
        val cal = Calendar.getInstance()

        binding.clickDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, y, m, d ->
                binding.txtDate.text = "$y-${m+1}-$d"
            },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    fun add(){
        binding.add.setOnClickListener {
            binding.ed.visibility = View.VISIBLE
            binding.edTwo.visibility = View.VISIBLE
            binding.lineVisible.visibility = View.VISIBLE
        }
    }

    fun list(){
        getModel.get()
        getTwoModel.get()
    }

    fun saveBtn(){
        binding.save.setOnClickListener {
            saveViewModel.save(binding.edTxtComName.text.toString(),binding.edTxtSerial.text.toString(),binding.edTxtState.text.toString(),
                binding.edTextType.text.toString(),binding.edTxtUser.text.toString(),binding.edTxtDtReg.text.toString(),binding.edTxtDtCor.text.toString(),)
        }

    }

    fun delete(){
        binding.delete.setOnClickListener {

        }
    }

    fun now(){
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        binding.txtDate.text = getTime
    }

}