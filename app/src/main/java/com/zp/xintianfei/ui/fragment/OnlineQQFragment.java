package com.zp.xintianfei.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.ImageUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.io.IOException;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class OnlineQQFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_online_qq_img)
    private ImageView imgQrcode;

    @BindView(id = R.id.fg_online_qq_tv_qq)
    private TextView tvQQ;
    @BindView(id = R.id.fg_online_qq_btn_copy, click = true)
    private Button btnCopy;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_online_qq, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.online_text_1);

        imgQrcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                try {
                    ImageUtils.saveImage(getActivity(), AppConfig.getSaveImagePath() + "qq_" + System.currentTimeMillis() + ".png", ImageUtils.getViewBitmap(imgQrcode));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        ApiCommon.getNetBitmap(AppConfig.getInstance().getmPre().getString("online_service_qq_url", ""), imgQrcode, false);

        tvQQ.setText(AppConfig.getInstance().getmPre().getString("online_service_qq", ""));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(10);
                break;
            case R.id.fg_online_qq_btn_copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", tvQQ.getText());
                cm.setPrimaryClip(mClipData);
                UIHelper.ToastMessage("复制成功");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
