package com.sopetit.softie.ui.onboarding.themechoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemOnboardingChoiceThemeBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.ui.onboarding.OnboardingActivity.Companion.MAXIMUM_THEME_SELECTION
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.toast

class ChoiceThemeAdapter :
    ListAdapter<Theme, ChoiceThemeAdapter.ChoiceThemeViewHolder>(
        ItemDiffCallback<Theme>(
            onItemsTheSame = { old, new -> old.themeId == new.themeId },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    private var onItemClickListener: ((Theme) -> Unit)? = null
    var selectedThemeArray = arrayListOf<Int>()

    inner class ChoiceThemeViewHolder(private val binding: ItemOnboardingChoiceThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.item = data
            // TODO 이미지 관련 재작업 예정 (동일 icon 재사용 위해 이전 pr 머지 후 작업)
//            binding.ivThemeIcon.setCoilImage(data.iconImageUrl)
            binding.root.setOnClickListener {
                themeSelection(binding, data)
                onItemClickListener?.let { it(data) }
            }
        }
    }

    fun setOnThemeClickListener(listener: (Theme) -> Unit) {
        onItemClickListener = listener
    }

    private fun themeSelection(binding: ItemOnboardingChoiceThemeBinding, theme: Theme) {
        val isThemeSelected: Boolean = selectedThemeArray.contains(theme.themeId)

        if (selectedThemeArray.size == MAXIMUM_THEME_SELECTION) {
            if (isThemeSelected) {
                removeThemeItem(
                    selectedThemeArray,
                    selectedThemeArray.indexOf(theme.themeId),
                    binding
                )
            } else {
                binding.root.context.toast("테마는 최대 3개 선택 가능해요")
            }
        } else {
            if (isThemeSelected) {
                removeThemeItem(
                    selectedThemeArray,
                    selectedThemeArray.indexOf(theme.themeId),
                    binding
                )
            } else {
                selectedThemeArray.add(theme.themeId)
                changeThemeBackground(binding, true)
            }
        }
    }

    private fun removeThemeItem(
        themeArray: ArrayList<Int>,
        selectedIndex: Int,
        binding: ItemOnboardingChoiceThemeBinding
    ) {
        themeArray.removeAt(selectedIndex)
        changeThemeBackground(binding, false)
    }

    private fun changeThemeBackground(
        binding: ItemOnboardingChoiceThemeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.ivThemeBackground.setBackgroundResource(R.drawable.shape_gray200_fill_gray500_stroke_100_circle)
            }

            false -> {
                binding.ivThemeBackground.setBackgroundResource(R.drawable.shape_gray0_fill_gray200_stroke_100_circle)
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
