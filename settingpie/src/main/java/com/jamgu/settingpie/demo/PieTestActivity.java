package com.jamgu.settingpie.demo;

import static com.jamgu.settingpie.model.ViewType.VIEW_TYPE_CHECKBOX;
import static com.jamgu.settingpie.model.ViewType.VIEW_TYPE_NORMAL;
import static com.jamgu.settingpie.model.ViewType.VIEW_TYPE_TEXT_TITLE;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jamgu.settingpie.R;
import com.jamgu.settingpie.model.CheckBoxProp;
import com.jamgu.settingpie.model.DecorationProp;
import com.jamgu.settingpie.model.LayoutProp;
import com.jamgu.settingpie.model.SetItemBuilder;
import com.jamgu.settingpie.model.SetListBuilder;
import com.jamgu.settingpie.databinding.ActivityTestBinding;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

public class PieTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.jamgu.settingpie.databinding.ActivityTestBinding mBinding = ActivityTestBinding
                .inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        String iconUrl = "http://img.daimg.com/uploads/allimg/210729/3-210H92301020-L.jpg";

        new SetListBuilder(mBinding.recycler)
                .showDecoration(true)
                .arrowOfTheme(true)
//                .decorationOfTheme(1, 0, 0, "#333333")
//                .decorationOfGroup(10, "#999999")
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE)
                                .mainText("资料设置", 14, "#999999")
                                .checkBoxProp(new CheckBoxProp(true, null));
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("用户名", 16, "#000000")
//                                .paddingPair(16,8)
                                .hintIcon(iconUrl);
                    }
                })
//                .addGroupItems(new DecorationProp(2, 32, 0, "#000000"), (new Function0<ArrayList<SetItemBuilder>>() {
                .addGroupItems(new Function0<ArrayList<SetItemBuilder>>() {
                    @Override
                    public ArrayList<SetItemBuilder> invoke() {
                        ArrayList<SetItemBuilder> builders = new ArrayList<>();
                        builders.add(new SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX)
                                .mainText("新消息通知")
                                .checkBoxProp(new CheckBoxProp(true, new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(PieTestActivity.this, "on checked changed = " + isChecked,
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })));
                        builders.add(new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("好友动态")
                                .mainIcon(iconUrl)
                                .hintText("新发表")
                                .hintIcon(iconUrl, 16)
                        );

                        builders.add(new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("附近")
                                .mainIcon(iconUrl)
                        );
                        return builders;
                    }
                })
                .addGroupItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX)
                                .mainText("聊天窗口顶部消息提醒")
                                .checkBoxProp(new CheckBoxProp(false, null));
                    }
                })
                .addGroupItems(new Function0<ArrayList<SetItemBuilder>>() {
                    @Override
                    public ArrayList<SetItemBuilder> invoke() {
                        ArrayList<SetItemBuilder> builders = new ArrayList<>();
                        builders.add(new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("消息通知设置")
                                .hintText("消息预览、提示音等")
                                .checkBoxProp(new CheckBoxProp(true, new OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        Toast.makeText(PieTestActivity.this, "on checked changed",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })));
                        builders.add(new SetItemBuilder().viewType(VIEW_TYPE_CHECKBOX)
                                .mainText("勿扰模式")
                        );
                        return builders;
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE)
                                .mainText("开启后，在指定时间内不再接受消息推送.", 14, "#999999")
                                .paddingY(8);
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("群消息设置")
                                .showArrow(false);
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("临时会话设置")
                                .layoutProp(new LayoutProp(new OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(PieTestActivity.this, "click 临时会话设置", Toast.LENGTH_SHORT).show();
                                    }
                                }));
                    }
                }).build();

    }
}