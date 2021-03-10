package Utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apptemalibre.Juego;
import com.example.apptemalibre.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    List<Juego> listJuegos;
    private View.OnClickListener listener;

    public RecyclerViewAdapter(Context context, List<Juego> juegos) {
        this.context = context;
        this.listJuegos = juegos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item, null);
        contentView.setOnClickListener(this);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Juego item = listJuegos.get(position);
        Holder Holder = (Holder) holder;
        Holder.juegoPhoto.setImageResource(item.getImgId());
        Holder.juegoName.setText(item.getNombre());
    }

    @Override
    public int getItemCount() {
        return listJuegos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public static class Holder extends RecyclerView.ViewHolder{
        TextView juegoName;
        ImageView juegoPhoto;

        public Holder(View itemView) {
            super(itemView);

            juegoName = (TextView) itemView.findViewById(R.id.textView);
            juegoPhoto = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }



}
