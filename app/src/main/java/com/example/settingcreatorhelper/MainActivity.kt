package com.example.settingcreatorhelper

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settingcreatorhelper.base.SettingAdapter
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.databinding.ActivityMainBinding
import com.example.settingcreatorhelper.model.CheckBoxProp
import com.example.settingcreatorhelper.model.LayoutProp
import com.example.settingcreatorhelper.model.DecorationProp
import com.example.settingcreatorhelper.model.SetItemBuilder
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CHECKBOX
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CUSTOM
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_NORMAL
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_TEXT_TITLE
import com.example.settingcreatorhelper.model.SettingListBuilder

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

        val data = SettingListBuilder(binding.recycler).paddingPair(16, 12)
                .showDecoration(true)
                .addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("角色动作", textColor = "#999999").hintText("攻击")
                            .layoutProp(LayoutProp {
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            })
                            .hintIcon(
                                "https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100",
                                width = 40,
                                height = 40,
                                radius = 16
                            )
                            .mainIcon("https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100")
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                            .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                            .mainIcon(R.drawable.ic_launcher_background, 40, 30)
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE).mainText("端游角色卡设置", 14, "#999999")
                            .paddingX(16)
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX).mainText("展示外观")
                            .checkBoxProp(CheckBoxProp(true, 50) { _, isChecked ->
                                Toast.makeText(this@MainActivity, "check box clicked = $isChecked", Toast.LENGTH_SHORT)
                                        .show()
                            })
                            .layoutProp(LayoutProp {
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            })
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_CUSTOM)
                            .viewBinder(SettingViewBinder(R.layout.setting_normal_item_layout_2) { holder, _ ->
                                val name = holder.itemView.findViewById<TextView>(R.id.setting_name)
                                name.text = "推荐好友"
                                holder.itemView.findViewById<TextView>(R.id.setting_hint).text = "lian_yi"
                            }).layoutProp(LayoutProp {
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            })
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_CUSTOM)
                            .viewBinder(SettingViewBinder(R.layout.setting_normal_item_layout_2) { holder, _ ->
                                val name = holder.itemView.findViewById<TextView>(R.id.setting_name)
                                name.text = "推荐好友"
                                holder.itemView.findViewById<TextView>(R.id.setting_hint).text = "lian_yi"
                            }).layoutProp(LayoutProp{
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            })
                }.addGroupItems(DecorationProp(1, 32, 0)) {
                    ArrayList<SetItemBuilder>().apply {
                        add(
                            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                                    .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                                    .mainIcon(R.drawable.default_img_placeholder, 40, 30)
                        )
                        add(
                            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                                    .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                                    .mainIcon(R.drawable.default_img_placeholder, 40, 30)
                        )
                        add(
                            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                                    .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                                    .mainIcon(R.drawable.default_img_placeholder, 40, 30)
                        )
                        add(
                            SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX).mainText("展示外观")
                                    .checkBoxProp(CheckBoxProp(true, 50) { _, isChecked ->
                                        Toast.makeText(this@MainActivity, "check box clicked = $isChecked", Toast.LENGTH_SHORT)
                                                .show()
                                    })
                                    .layoutProp(LayoutProp {
                                        Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                                    })
                        )
                        add(
                            SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                                    .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                                    .mainIcon(R.drawable.default_img_placeholder, 40, 30)
                        )
                    }
                }.addItem {
                    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("光效").hintText("九幽冥火")
                            .hintIcon("http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg")
                            .mainIcon(R.drawable.default_img_placeholder, 40, 30)
                }.addGroupItem {
                    SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX).mainText("展示外观")
                            .checkBoxProp(CheckBoxProp(true, 50) { _, isChecked ->
                                Toast.makeText(this@MainActivity, "check box clicked = $isChecked", Toast.LENGTH_SHORT)
                                        .show()
                            })
                            .layoutProp(LayoutProp {
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            })
                }.build()

        adapter.setData(data)

        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}