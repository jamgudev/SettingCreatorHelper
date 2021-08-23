# SettingPie

SettingPie是一个拓展Android库，使Android开发设置页变得非常容易，它通过代码调用的方式，不仅提供了常见的设置布局来应对基本的设置列表需求，还支持自定义设置布局以处理更复杂的布局。

## Quick Setup

编辑你项目的build.gradle文件，然后添加如下依赖。

```groovy
repositories {
	mavenCentral()
}

dependencies {
	implementation 'io.github.jamgudev:settingpie:1.0.0'
}
```

添加完毕后，你就可以使用了。

## Basic Usage

SettingPie的使用非常流畅方便。

例如下面一个设置页

![1_quick_look][1_quick_look]

可以通过如下代码生成：

```kotlin
val iconUrl = "https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100"

SetListBuilder(vRecycler).showDecoration(true).paddingPair(16, 16).addItem {
    SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE).mainText("社区资料设置", 14, "#999999")
            .paddingY(6)
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("ni").hintIcon(iconUrl, 25, 25, 16)
            .layoutProp(LayoutProp {
                Toast.makeText(...).show()
            })
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("头像框显示")
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE).mainText("角色卡通用设置", 14, "#999999").paddingY(6)
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_NORMAL).mainText("背景图设置").hintText("曙光之城")
            .hintIcon(iconUrl, 4)
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE).mainText("端游角色卡设置", 14, "#999999").paddingY(6)
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX).mainText("展示角色装扮外观")
            .checkBoxProp(CheckBoxProp(true, 50, null))
}.addItem {
    SetItemBuilder().viewType(VIEW_TYPE_CUSTOM).viewBinder(SettingViewBinder(R.layout.test_layout) { holder, pos ->
        holder.itemView.findViewById<TextView>(R.id.vBgNames).text = "角色动作"
        holder.itemView.findViewById<TextView>(R.id.vBgActTipss).text = "新活动"
        holder.itemView.findViewById<TextView>(R.id.vBgDescs).text = "行走"
    })
}.build()
```
每一项设置项都通过代码设置，并最终生成一个设置列表，SettingPie会根据列表中设置项的顺序，生成设置列表显示到对应的RecyclerView中。

## 详细配置

### SetListBuilder配置

可以通过SetListBuilder，设置用于整个列表的主题配置，例如边距，列表分割线，以及列表右端箭头配置等，**在SetListBuilder中设置的属性配置，将被适用于所有的设置子项，前提是该设置子项自身没有对指定属性进行配置**

**内边距**

如果，你要设置整个设置页所有子项的内边距属性，你可以这样设置

```kotlin
// 设置左、右内边距为16dp，上、下内边距为12dp
SetListBuilder(recyclerView)
        .paddingPair(16, 12)	
```

> SettingPie是基于RecyclerView的，因此需要在SetListBuilder的构造函数中传入RecyclerView进行绑定

![2_padding_show][2_padding_show]

如上代码设置，paddingLeft和paddingRight为16dp，paddingTop和PaddingBottom为12dp，SettingPie还提供了其他padding的设置Api，更多Api请查看源码。

&emsp;

**分割线**

如果你还想让各个子项之间用间隔线隔开，这样设置

```kotlin
SetListBuilder(recyclerView)
        .paddingPair(16, 12)	
        .decorationOfTheme(1, 0, 0, "#999999")
```

上面代码将设置主题分割线高度为1dp，分割线的marginLeft为0、marginRight为0，以及分隔线的颜色为灰色。

这也是SettingPie的默认分割线样式，如果已经能满足需求，则无需再设置decorationOfTheme()。

但SettingPie默认是不显示分割线的，**如果用户想要采用默认分割线样式，则需要显示调用showDecoration(true)**，标注需要为子项描绘分割线。而自定义分割线样式时，showDecoration()会被隐式调用，用户不需要调用showDecoration()。

如下代码，配置显示默认分割线

```kotlin
SetListBuilder(binding.recycler)
        .paddingPair(16, 12)
        .showDecoration(true)
```

&emsp;

**组分割线和组内分割线**

SettingPie的分割线中，除了正常的分割线以外，还区分组分割线和组内分割线

![4_decoration_type_edit][4_decoration_type_edit]

设置页通常会将相关联的设置排列在一块，与其它设置项留有一定的间隙，这样排版比较整齐。

SettingPie中引用组的概念，将同类的设置放在一块成组，SettingPie在排列一组设置项时，会自动在组的上下方添加一道较宽的分割线，我叫它组分割线。

以此类推，组内设置项之间的分割线为组内分割线，如上图所示。

同样，组分割线和组内分割线有默认样式（效果上图右边部分），也可通过调用如下代码，自定义其样式：

```kotlin
SetListBuilder(mBinding.recycler)
         // 自定义正常分割线样式
        .decorationOfTheme(1, 0, 0, "#333333")
         // 自定义组分割线样式 
        .decorationOfGroup(10, "#999999")
         // 在添加组设置项时，传入DecorationProp，
         // 设置该组内分割线样式
        .addGroupItems(new DecorationProp(2, 32, 0, "#000000"), ...)
```



**角标**

![5_arrow_show][5_arrow_show]

如果你不想显示显示右部的角标按钮，可以隐藏，调用：

```kotlin
SetListBuilder(mBinding.recycler)
        .arrowOfTheme(false)
```

如果你想改变角标的默认样式并显示出来，调用:

```kotlin
SetListBuilder(mBinding.recycler)
        .showDecoration(true)
        .arrowOfTheme(true, R.drawable.youArrowIcon)
```

&emsp;

### SetItemBuilder配置

用于配置某个设置子项，目前支持配置的属性如下：

- ViewType: 设置布局类型配置。
- MainTextProp: 设置项名字配置。
- HintTextProp: 设置项描述配置。
- MainIconProp: 设置项图标配置。
- HintIconProp: 设置项描述图标配置。
- CheckBoxProp: 复选框配置。
- ArrowProp: 角标配置。
- PaddingProp: 边距配置。
- ViewBinder: 用于支持自定义布局的配置。
- LayoutProp: 布局配置，通常用于设置项的点击效果和点击事件。

![6_type_normal_show][6_type_normal_show]

**ViewType**

当前SettingPie支持ViewType有：**VIEW_TYPE_NORMAL**，**VIEW_TYPE_CHECKBOX**，**VIEW_TYPE_TEXT_TITLE**，**VIEW_TYPE_CUSTOM（自定义布局）**

1. VIEW_TYPE_NORMAL

![7_type_normal][7_type_normal]

2. VIEW_TYPE_CHECKBOX

![8_type_checkbox][8_type_checkbox]

3. VIEW_TYPE_TEXT_TITLE

![9_type_text_title][9_type_text_title]

可以根据viewType，在SetItemBuilder配置想要的属性。值得注意的是，只有对该ViewType支持的属性进行的配置才是有效的。如下代码设置的checkboxProp是无效的，因为该viewType不支持设置checkbox。

```kotlin
new SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE)
        .mainText("资料设置", 14, "#999999")
        .checkBoxProp(new CheckBoxProp(true, null))
```

4. VIEW_TYPE_CUSTOM

如果以上常用的布局类型不能满足你的需求场景，SettingPie支持自定义设置布局

```kotlin
SetItemBuilder().viewType(VIEW_TYPE_CUSTOM)
        .viewBinder(SettingViewBinder(R.layout.setting_normal_item_layout_2) { holder, _ ->
            val name = holder.itemView.findViewById<TextView>(R.id.setting_name)
            name.text = "推荐好友"
            holder.itemView.findViewById<TextView>(R.id.setting_hint).text = "lian_yi"
        })
```

如果你想自定义布局，需要在viewType设置VIEW_TYPE_CUSTOM的同时，传入viewBinder

```kotlin
class SettingViewBinder(val layoutId: Int?, val binder: Binder?)

typealias Binder = (RecyclerView.ViewHolder, Int) -> Unit
```

第一个参数传入自定义布局layoutId，第二个传入binder，这个binder相当于RecyclerView的onBindViewHolder()方法，用户传入后，SettingPie在绑定数据时会调用该binder完成对自定义布局的初始化。

**LayoutProp**

通用设置页的核心之一，就是定义每个设置项的点击事件以及相应的点击效果。SettingPie同样暴露了这样的接口，将其配置封装在了LayoutProp中。

```kotlin
/**
 * 点击事件配置：clickDrawableRes 点击时的drawable， onClick 点击触发的回调
 *
 *  优先使用bgRes，如果bgRes和bgColor都没有，用默认的
 */
class LayoutProp private constructor(
    val bgColor: String?,
    val bgRes: Int?,
    val onClick: (View.OnClickListener)?
) {

    @JvmOverloads
    constructor(bgRes: Int? = null, onClick: (View.OnClickListener)?) : this(null, bgRes = bgRes, onClick = onClick)

    /**
     * 传的是一个颜色固定值，可能不需要点击事件，onClick可以不传
     */
    @JvmOverloads
    constructor(bgColor: String?, onClick: (View.OnClickListener)? = null) : this(bgColor, null, onClick = onClick)
}
```

## LICENSE

```
Copyright (C) jamgu, SettingPie Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[1_quick_look]: ./pics/1_quick_look.png
[2_padding_show]:./pics/2_padding_show.png
[4_decoration_type_edit]:./pics/4_decoration_type_edit.png
[5_arrow_show]:./pics/5_arrow_show.png
[6_type_normal_show]: ./pics/6_type_normal_show.png
[7_type_normal]: ./pics/7_type_normal.png
[8_type_checkbox]: ./pics/8_type_checkbox.png
[9_type_text_title]: ./pics/9_type_text_title.png





