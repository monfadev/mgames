package com.example.mgames.ui

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mgames.R
import com.example.mgames.databinding.ActivityMainBinding
import com.example.mgames.databinding.ItemGameBinding
import com.example.mgames.domain.GameItem

class GameAdapter : RecyclerView.Adapter<GameAdapter.MyGameViewHolder>() {

    private var games: List<GameItem> = ArrayList()

    class MyGameViewHolder(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGameViewHolder {
        return MyGameViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: MyGameViewHolder, position: Int) {
        val game = games[position]

        holder.binding.apply {
            mImage.load(game.thumbnail) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image)
                crossfade(true)
                crossfade(400)
            }
            mTitle.text = game.title
        }
    }

    fun setGames(games: List<GameItem>) {
        this.games = games
    }
}