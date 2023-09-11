package sathchalo.com.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sathchalo.com.Message_Model;
import sathchalo.com.R;

public class Chat_Adapter extends RecyclerView.Adapter{

    ArrayList<Message_Model> message_models;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public Chat_Adapter(ArrayList<Message_Model> message_models, Context context) {
        this.message_models = message_models;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {

//        if(message_models.get(position).getuId().equals(firebaseAuth.getInstance().getUid())){
//            return SENDER_VIEW_TYPE;
//        }
//        else {
//            return RECEIVER_VIEW_TYPE;
//        }

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMsg, receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);


            receiverMsg = itemView.findViewById(R.id.receiver);
            receiverTime=  itemView.findViewById(R.id.receiverTime);

        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg, senderTime;


        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.sender);
            senderTime=  itemView.findViewById(R.id.senderTime);
        }
    }

}
