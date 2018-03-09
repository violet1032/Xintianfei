package com.zp.xintianfei.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.GambleSimpleTodayHistory;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.utils.UIHelper;

import java.util.List;
import java.util.Map;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/3 12:46
 * <p/>
 * 版本:
 */
public class GambleSimpleTodayAdapter extends BaseAdapter {
    private ListView lv;
    private List<GambleSimpleTodayHistory> list;
    private LayoutInflater inflater;

    public GambleSimpleTodayAdapter(ListView lv, List<GambleSimpleTodayHistory> list) {
        this.lv = lv;
        this.list = list;
        inflater = LayoutInflater.from(AppContext.appContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.listitem_jl_gamble, viewGroup, false);
            holder = new ViewHolder();

            holder.stage = view.findViewById(R.id.listitem_jl_lottery_tv_stage);
            holder.game = view.findViewById(R.id.listitem_jl_lottery_tv_game);
            holder.gamble = view.findViewById(R.id.listitem_jl_lottery_tv_gamble);
            holder.sum = view.findViewById(R.id.listitem_jl_lottery_tv_sum);
            holder.result = view.findViewById(R.id.listitem_jl_lottery_tv_result);
            holder.opretion = view.findViewById(R.id.listitem_jl_lottery_tv_opretion);
            holder.btnOpretion = view.findViewById(R.id.listitem_jl_lottery_btn_opretion);
            holder.layOpretion = view.findViewById(R.id.listitem_jl_lottery_lay_opretion);

            holder.img1 = view.findViewById(R.id.listitem_jl_lottery_img_1);
            holder.img2 = view.findViewById(R.id.listitem_jl_lottery_img_2);
            holder.img3 = view.findViewById(R.id.listitem_jl_lottery_img_3);
            holder.img4 = view.findViewById(R.id.listitem_jl_lottery_img_4);
            holder.img5 = view.findViewById(R.id.listitem_jl_lottery_img_5);
            holder.img6 = view.findViewById(R.id.listitem_jl_lottery_img_6);
            holder.img7 = view.findViewById(R.id.listitem_jl_lottery_img_7);
            holder.img8 = view.findViewById(R.id.listitem_jl_lottery_img_8);
            holder.img9 = view.findViewById(R.id.listitem_jl_lottery_img_9);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int black = view.getContext().getResources().getColor(R.color.black_3);
        int white = view.getContext().getResources().getColor(R.color.white);
        int purple_1 = view.getContext().getResources().getColor(R.color.purple_1);
        int gray_3 = view.getContext().getResources().getColor(R.color.gray_3);

        if (position == 0) {
            holder.img1.setVisibility(View.VISIBLE);
            holder.img2.setVisibility(View.VISIBLE);
            holder.img3.setVisibility(View.VISIBLE);
            holder.img4.setVisibility(View.VISIBLE);
            holder.img5.setVisibility(View.VISIBLE);
            holder.img6.setVisibility(View.VISIBLE);
            holder.img7.setVisibility(View.VISIBLE);
            holder.img8.setVisibility(View.VISIBLE);
            holder.img9.setVisibility(View.VISIBLE);
            holder.layOpretion.setVisibility(View.GONE);
            holder.opretion.setVisibility(View.VISIBLE);
            holder.stage.setText("  期号  ");
            holder.game.setText("    玩法    ");
            holder.gamble.setText("下注");
            holder.sum.setText("点数");
            holder.result.setText("结果");
            holder.opretion.setText("操作");

            holder.stage.setTextColor(white);
            holder.game.setTextColor(white);
            holder.gamble.setTextColor(white);
            holder.sum.setTextColor(white);
            holder.result.setTextColor(white);
            holder.opretion.setTextColor(white);

            holder.layOpretion.setBackgroundColor(purple_1);
            holder.stage.setBackgroundColor(purple_1);
            holder.game.setBackgroundColor(purple_1);
            holder.gamble.setBackgroundColor(purple_1);
            holder.sum.setBackgroundColor(purple_1);
            holder.result.setBackgroundColor(purple_1);
            holder.opretion.setBackgroundColor(purple_1);
        } else {
            final GambleSimpleTodayHistory item = list.get(position);

//            holder.layOpretion.setVisibility(View.VISIBLE);
//            holder.opretion.setVisibility(View.GONE);

            holder.img1.setVisibility(View.GONE);
            holder.img2.setVisibility(View.GONE);
            holder.img3.setVisibility(View.GONE);
            holder.img4.setVisibility(View.GONE);
            holder.img5.setVisibility(View.GONE);
            holder.img6.setVisibility(View.GONE);
            holder.img7.setVisibility(View.GONE);
            holder.img8.setVisibility(View.GONE);
            holder.img9.setVisibility(View.GONE);
            holder.stage.setText(item.getStage());
            holder.game.setText(item.getGameType());
            holder.gamble.setText(item.getNumber());
            holder.sum.setText(item.getMoney().toString());
//            holder.result.setText(item.getStrResult());
//            holder.opretion.setText("操作");

            holder.stage.setTextColor(black);
            holder.game.setTextColor(black);
            holder.gamble.setTextColor(black);
            holder.sum.setTextColor(black);
            holder.result.setTextColor(black);
            holder.opretion.setTextColor(black);

            holder.stage.setBackgroundColor(gray_3);
            holder.layOpretion.setBackgroundColor(gray_3);
            holder.game.setBackgroundColor(gray_3);
            holder.gamble.setBackgroundColor(gray_3);
            holder.sum.setBackgroundColor(gray_3);
            holder.result.setBackgroundColor(gray_3);
            holder.opretion.setBackgroundColor(gray_3);

            holder.btnOpretion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    withdraw(item.getId());
                }
            });


            if (item.getStatus() == 0)
                holder.result.setText("未结算");
            else if (item.getStatus() == -1)
                holder.result.setText("0");
            else if (item.getStatus() == 1)
                holder.result.setText(item.getWin().toString());

            if (item.getStatus() == 0) {
                holder.layOpretion.setVisibility(View.VISIBLE);
                holder.opretion.setVisibility(View.GONE);
            } else if (item.getStatus() == -1) {
                holder.layOpretion.setVisibility(View.GONE);
                holder.opretion.setVisibility(View.VISIBLE);
                holder.opretion.setText("已撤单");
            } else if (item.getStatus() == 1) {
                holder.layOpretion.setVisibility(View.GONE);
                holder.opretion.setVisibility(View.VISIBLE);

                if (item.getCode() == 0)
                    holder.opretion.setText("未中奖");
                else
                    holder.opretion.setText("中奖");
            }

        }
        return view;
    }

    private class ViewHolder {
        private TextView stage;
        private TextView game;
        private TextView gamble;
        private TextView sum;
        private TextView result;
        private TextView opretion;
        private Button btnOpretion;
        private LinearLayout layOpretion;
        private ImageView img1;
        private ImageView img2;
        private ImageView img3;
        private ImageView img4;
        private ImageView img5;
        private ImageView img6;
        private ImageView img7;
        private ImageView img8;
        private ImageView img9;
    }

    private void withdraw(int id) {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    UIHelper.ToastMessage("撤单成功");
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        };
        ApiLottery.withdraw(id, callBack);
    }
}
