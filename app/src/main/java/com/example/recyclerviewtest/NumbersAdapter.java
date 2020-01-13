package com.example.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//ViewGroup - компонент-контейнер, RV наследуется от него
public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>{
    public static int viewHolderCount;
    private int numberItems; //общее кол-во элементов в списке
    private Context parent;
    //через Context принимаем MainActivity
    public NumbersAdapter(int numberOfItems, Context parent) { //конструктор
        numberItems = numberOfItems;
        viewHolderCount = 0;

        this.parent = parent;
    }

    @NonNull
    @Override //используется, когда нужно создать новый холдер
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.number_list_item; //tv_number_item из xml поместили сюда

        LayoutInflater inflater = LayoutInflater.from(context);
        //inflater переводит xml представления в java-объект
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText("ViewHolder Index " + viewHolderCount);

        viewHolderCount++; //после создания первого VH=0 новый ++

        return viewHolder;
    }

    @Override //вызывается, когда созданные VH используются заново
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position); //здесь можно брать данные из внешних источников
    }

    @Override//возвращает кол-во элементов в списке
    public int getItemCount() {
        return numberItems;
    }

    //Обертка элемента списка
    class NumberViewHolder extends RecyclerView.ViewHolder{

        TextView listItemNumberView; //layout Элемент списка
        TextView viewHolderIndex; //layout id списка

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);
            //listener следит за нажатиями
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //показываем Toast
                    //Application и Activity наследуются от Context - предоставляет доступ к базовым функциям приложения
                    int positionIndex = getAdapterPosition();

                    Toast toast = Toast.makeText(parent, "Element " + positionIndex +
                            " was clicked", Toast.LENGTH_SHORT);

                    toast.show();
                }
            });
        }

        void bind(int listIndex) { //метод используется заново, передаем новое число
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}
