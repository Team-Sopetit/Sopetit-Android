package com.sopetit.softie.ui.onboarding.themechoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemOnboardingChoiceThemeBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.toast

class ChoiceThemeAdapter :
    ListAdapter<Theme, ChoiceThemeAdapter.ChoiceThemeViewHolder>(
        ItemDiffCallback<Theme>(
            onItemsTheSame = { old, new -> old.themeId == new.themeId },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    inner class ChoiceThemeViewHolder(private val binding: ItemOnboardingChoiceThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvThemeName.text = data.name
            binding.root.setOnClickListener {
                themeSelection(binding, data)
                onItemClickListener?.let { it(data) }
            }
        }
    }

    private var onItemClickListener: ((Theme) -> Unit)? = null
    fun setOnThemeClickListener(listener: (Theme) -> Unit) {
        onItemClickListener = listener
    }

    var selectedThemeArray = arrayListOf<Int>()

    private fun themeSelection(binding: ItemOnboardingChoiceThemeBinding, theme: Theme) {
        if (selectedThemeArray.size == 3) {
            if (selectedThemeArray.contains(theme.themeId)) {
                selectedThemeArray.removeAt(selectedThemeArray.indexOf(theme.themeId))
                changeThemeBackground(binding, false)
            } else {
                binding.root.context.toast("테마는 최대 3개 선택 가능해요")
            }
        } else {
            if (selectedThemeArray.contains(theme.themeId)) {
                selectedThemeArray.removeAt(selectedThemeArray.indexOf(theme.themeId))
                changeThemeBackground(binding, false)
            } else {
                selectedThemeArray.add(theme.themeId)
                changeThemeBackground(binding, true)
            }
        }
    }

    private fun changeThemeBackground(
        binding: ItemOnboardingChoiceThemeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.ivThemeBackground.setBackgroundResource(R.drawable.shape_gray100_filll_100_circle)
            }

            false -> {
                binding.ivThemeBackground.setBackgroundResource(R.drawable.shape_white_fill_100_circle)
            }
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
