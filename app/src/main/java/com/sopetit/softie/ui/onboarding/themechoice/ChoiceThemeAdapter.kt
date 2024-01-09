package com.sopetit.softie.ui.onboarding.themechoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemOnboardingChoiceThemeBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.util.ItemDiffCallback

class ChoiceThemeAdapter :
    ListAdapter<Theme, ChoiceThemeAdapter.ChoiceThemeViewHolder>(ItemDiffCallback<Theme>(
        onItemsTheSame = { old, new -> old.themeId == new.themeId },
        onContentsTheSame = { old, new -> old == new }
    )) {

    class ChoiceThemeViewHolder(private val binding: ItemOnboardingChoiceThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvThemeName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceThemeViewHolder {
        val binding = ItemOnboardingChoiceThemeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChoiceThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChoiceThemeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
