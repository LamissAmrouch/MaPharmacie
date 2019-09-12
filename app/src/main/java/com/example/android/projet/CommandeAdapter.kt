package com.example.android.projet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.android.projet.entities.Commande

class CommandeAdapter(val ctx: Context, val data:List<Commande>): BaseAdapter() {

        override fun getItem(p0: Int)= data.get(p0)

        override fun getItemId(p0: Int) = data.get(p0).hashCode().toLong()

        override fun getCount() = data.size

        override fun getView(i: Int, p0: View?, parent: ViewGroup?): View { //position , premier element devenant invisible
            var view = p0
            var holder:ViewHolder
            if (view == null) {
                view = LayoutInflater.from(ctx).inflate(R.layout.layout_list_commande,parent,false)
                val textView = view?.findViewById(R.id.nom_commande) as TextView
                val textView2 = view?.findViewById(R.id.date_commande) as TextView
                val textView3 = view?.findViewById(R.id.etat_commande) as TextView
                holder = ViewHolder(textView,textView2,textView3)
                view?.setTag(holder)
            }
            else {
                holder = view.tag as ViewHolder
            }

            holder.textView.setText("Commande N°"+data.get(i).idC)
            holder.textView2.setText(data.get(i).date_envoi)
            holder.textView3.setText("Etat: "+data.get(i).etat_traitement)

            return view
        }

        private class ViewHolder(val textView: TextView, val textView2: TextView, val textView3: TextView)


}