package com.github.OctupusTea.Accounting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private List<String> mData;

	MyAdapter(List<String> data) {
		mData = data;
	}

	// 刪除項目
	public void removeItem(int position){
		mData.remove(position);
		notifyItemRemoved(position);
	}

	// 建立ViewHolder
	class ViewHolder extends RecyclerView.ViewHolder{
		// 宣告元件
		private TextView txtItem;
		private Button btnRemove;

		ViewHolder(View itemView) {
			super(itemView);
			txtItem = (TextView) itemView.findViewById(R.id.txtItem);
			btnRemove = (Button) itemView.findViewById(R.id.btnRemove);

			// 點擊項目中的Button時
			btnRemove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					removeItem(getPosition());
				}
			});
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// 連結項目布局檔list_item
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// 設置txtItem要顯示的內容
		holder.txtItem.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}
}
