package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcinfo.album.ui.ChooseDialog;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class BanddingWeixinFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_recharge_btn_1, click = true)
    private Button btnExchange1;
    @BindView(id = R.id.fg_recharge_btn_2, click = true)
    private Button btnExchange2;

    @BindView(id = R.id.fg_tx_nickname)
    private TextView tvNickname;
    @BindView(id = R.id.fg_tx_id)
    private TextView tvID;
    @BindView(id = R.id.fg_tx_sum)
    private TextView tvSum;
    @BindView(id = R.id.fg_tx_fanshui_sum)
    private TextView tvSumFanshui;
    @BindView(id = R.id.fg_tx_yongjin_sum)
    private TextView tvSumYongjin;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    @BindView(id = R.id.fg_bandding_weixin_img)
    private ImageView imgQrcode;

    @BindView(id = R.id.fg_bandding_weixin_tv_file)
    private TextView tvFile;

    private Handler handler;

    @BindView(id = R.id.fg_bandding_weixin_edt_nickname)
    private EditText edtNickname;
    @BindView(id = R.id.fg_bandding_weixin_btn_bandding, click = true)
    private Button btnBadding;
    @BindView(id = R.id.fg_bandding_weixin_btn_scan, click = true)
    private Button btnScan;
//    @BindView(id = R.id.fg_bandding_weixin_btn_upload, click = true)
//    private Button btnUpload;

    private int bankId = 0;

    private String uri;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_bandding_weixin, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.fragment_withdraw_text_14);

        tvNickname.setText(AppContext.user.getNickname());
        tvID.setText(AppContext.user.getUid() + "");
        tvSum.setText(AppContext.user.getMoney().toString());
        tvSumFanshui.setText(AppContext.user.getFanshui().toString());
        tvSumYongjin.setText(AppContext.user.getYongjin().toString());

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                if (message.what == 1) {
                }

                return false;
            }
        });

        getMemberWeiXin();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_recharge_btn_1:
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_recharge_btn_2:
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(1);
                break;
            case R.id.fg_bandding_weixin_btn_bandding:
                badding();
                break;
            case R.id.fg_bandding_weixin_btn_scan:
                // 浏览

                if(!AppContext.appContext.isGrantExternalRW(getActivity())){
                    break;
                }

                ((MainActivity) getActivity()).imgUploadType = 0;
                ChooseDialog.startActivity(getActivity(), 1, false);
                break;
//            case R.id.fg_bandding_weixin_btn_upload:
//                // 上传
//                break;
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void badding() {
        String nickName = edtNickname.getText().toString().trim();
        String filename = tvFile.getText().toString().trim();


        if (StringUtils.isEmpty(nickName)) {
            UIHelper.ToastMessage("请输入微信昵称");
            return;
        }
//        if (StringUtils.isEmpty(filename)) {
//            UIHelper.ToastMessage("请上传收款二维码");
//            return;
//        }

        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    UIHelper.ToastMessage("绑定成功");
                    ((MainActivity) getActivity()).setPosition(1);
                } else {
                    UIHelper.ToastMessage(result.getMsg());
                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(getActivity());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiUser.bindWeiXin(nickName, uri, callBack);
    }

    /**
     * 图片上传
     */
    public void imgUpLoad(final String uri) {
        this.uri = uri;
        String fileName = StringUtils.pathToFileName(uri);
        tvFile.setText(fileName);
    }

    /**
     * 获取绑定的微信
     */
    private void getMemberWeiXin() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);

                        JSONArray jsonArray = jsonUtils.getJSONArray("info");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JsonUtils jsonUtils1 = new JsonUtils(jsonArray.getString(i));
                            String wx_username = jsonUtils1.getString("wx_username");
                            String imgUrl = jsonUtils1.getString("wx_url");

                            if(!StringUtils.isEmpty(wx_username)){
                                btnBadding.setText("更新");
                                edtNickname.setText(wx_username);
                                imgQrcode.setVisibility(View.VISIBLE);
                                ApiCommon.getNetBitmap(imgUrl, imgQrcode, false);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("解析错误");
                    }
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }
        };
        ApiUser.getMemberWeiXin(callBack);
    }
}
