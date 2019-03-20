package com.example.test.paras.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.test.paras.R;
import com.example.test.paras.databinding.TransactionListItemBinding;
import com.example.test.paras.service.model.TransactionResponse;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {

    List<TransactionResponse> transactionResponseList;
    public TransactionListAdapter(List< TransactionResponse> transactionResponseList) {
        this.transactionResponseList = transactionResponseList;
    }


    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TransactionListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.transaction_list_item,
                        parent, false);
        return new TransactionViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.binding.setTransaction(transactionResponseList.get(position));
        holder.binding.executePendingBindings();
    }
    @Override
    public int getItemCount() {
        return transactionResponseList == null ? 0 : transactionResponseList.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        final TransactionListItemBinding binding;
        public TransactionViewHolder(TransactionListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
