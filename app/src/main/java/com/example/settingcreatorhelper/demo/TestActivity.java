package com.example.settingcreatorhelper.demo;

import static com.example.settingcreatorhelper.model.SetConstants.VIEW_TYPE_NORMAL;
import static com.example.settingcreatorhelper.model.SetConstants.VIEW_TYPE_TEXT_TITLE;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.settingcreatorhelper.databinding.ActivityTestBinding;
import com.example.settingcreatorhelper.model.LayoutProp;
import com.example.settingcreatorhelper.model.SetItemBuilder;
import com.example.settingcreatorhelper.model.SetListBuilder;
import kotlin.jvm.functions.Function0;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        // TODO 设置不对称padding时会有问题
        new SetListBuilder(mBinding.recycler)
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE)
                                .mainText("社区资料设置", 14);
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("SF`QuiBIAN灬Kuang", 16, "#000000")
                                .hintIcon("https://q.qlogo.cn/qqapp/1104466820/2480FDF9E6072E6536CE5FF7B946674F/100",
                                        40, 40, 16);
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_TEXT_TITLE)
                                .mainText("角色卡通用设置", 14)
                                .paddingPair(16, 8);
                    }
                })
                .addItem(new Function0<SetItemBuilder>() {
                    @Override
                    public SetItemBuilder invoke() {
                        return new SetItemBuilder().viewType(VIEW_TYPE_NORMAL)
                                .mainText("背景图设置", 16, "#000000")
                                .layoutProp(new LayoutProp(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(TestActivity.this, "click 背景图设置", Toast.LENGTH_SHORT).show();
                                    }
                                }));
                    }
                }).build();
    }
}