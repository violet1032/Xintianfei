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
public class OnlineWeixinFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;
    @BindView(id = R.id.fg_online_weixin_tv)
    private TextView tvAccount;
    @BindView(id = R.id.fg_online_weixin_btn_copy, click = true)
    private Button btnCopy;

    @BindView(id = R.id.fg_online_weixin_img)
    private ImageView imgQrcode;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_online_weixin, null);
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
                    ImageUtils.saveImage(getActivity(), AppConfig.getSaveImagePath() + "weixin_" + System.currentTimeMillis() + ".png", ImageUtils.getViewBitmap(imgQrcode));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        ApiCommon.getNetBitmap(AppConfig.getInstance().getmPre().getString("online_service_weixin_url", ""), imgQrcode, false);

        tvAccount.setText(AppConfig.getInstance().getmPre().getString("online_service_weixin", ""));
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(10);
                break;
            case R.id.fg_online_weixin_btn_copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", tvAccount.getText());
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
