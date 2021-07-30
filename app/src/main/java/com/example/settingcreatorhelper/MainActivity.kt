package com.example.settingcreatorhelper

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settingcreatorhelper.base.SettingAdapter
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.databinding.ActivityMainBinding
import com.example.settingcreatorhelper.model.CheckBoxProp
import com.example.settingcreatorhelper.model.ClickProp
import com.example.settingcreatorhelper.model.IconProp
import com.example.settingcreatorhelper.model.SetItemBuilder
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CHECKBOX
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CUSTOM
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_NORMAL
import com.example.settingcreatorhelper.model.SettingListBuilder
import com.example.settingcreatorhelper.model.TextProp

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val adapter = SettingAdapter()
        binding.recycler.adapter = adapter

        val data = SettingListBuilder().paddingPair(16, 12).addItem {
            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("角色动作", textColor = "#999999").hintText("攻击")
                    .onLayoutClickProp(ClickProp {
                        Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                    })
                    .hintIconProp(IconProp(iconUrl = "https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100", width = 40, height = 40, radius = 16))
                    .mainIconProp(IconProp(iconUrl = "https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100"))
        }.addItem {
            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                    .hintIconProp(IconProp(iconUrl = "http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg"))
        }.addItem {
            SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX).mainText("展示外观")
                    .checkBoxProp(CheckBoxProp(true, 50) { _, isChecked ->
                        Toast.makeText(this@MainActivity, "check box clicked = $isChecked", Toast.LENGTH_SHORT).show()
                    })
                    .onLayoutClickProp(ClickProp {
                        Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                    })
        }.addItem {
            SetItemBuilder().viewType(VIEW_TYPE_CUSTOM)
                    .viewBinder(SettingViewBinder(R.layout.setting_normal_item_layout_2) { holder, _ ->
                        val name = holder.itemView.findViewById<TextView>(R.id.setting_name)
                        name.text = "推荐好友"
                        holder.itemView.findViewById<TextView>(R.id.setting_hint).text = "lian_yi"
                    }).onLayoutClickProp(ClickProp {
                        Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                    })
        }.build()

        adapter.setData(data)

        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}