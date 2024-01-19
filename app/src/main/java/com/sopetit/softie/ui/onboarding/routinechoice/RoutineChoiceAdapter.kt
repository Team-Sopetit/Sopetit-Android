package com.sopetit.softie.ui.onboarding.routinechoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemOnboardingChoiceRoutineBinding
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.ui.onboarding.routinechoice.RoutineChoiceFragment.Companion.MAXIMUM_ROUTINE_SELECTION
import com.sopetit.softie.util.ItemDiffCallback

class RoutineChoiceAdapter : ListAdapter<Routine, RoutineChoiceAdapter.RoutineChoiceViewHolder>(
    ItemDiffCallback<Routine>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.routineId == new.routineId }
    )
) {

    private var onItemClickListener: ((Routine) -> Unit)? = null
    var selectedRoutineArray = arrayListOf<Int>()

    private var _isRoutineNoticeVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRoutineNoticeVisible: LiveData<Boolean>
        get() = _isRoutineNoticeVisible

    var selectPosition = ArrayList<Int>()

    inner class RoutineChoiceViewHolder(private val binding: ItemOnboardingChoiceRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Routine) {
            binding.tvRoutineContent.text = data.content
            binding.root.setOnClickListener {
                routineSelection(binding, data)
                onItemClickListener?.let { it(data) }
            }
        }
    }

    fun setOnRoutineClickListener(listener: (Routine) -> Unit) {
        onItemClickListener = listener
    }

    private fun routineSelection(binding: ItemOnboardingChoiceRoutineBinding, routine: Routine) {
        val isRoutineSelected: Boolean = selectedRoutineArray.contains(routine.routineId)

        if (selectedRoutineArray.size == MAXIMUM_ROUTINE_SELECTION) {
            if (isRoutineSelected) {
                removeRoutineItem(
                    selectedRoutineArray,
                    selectedRoutineArray.indexOf(routine.routineId),
                    binding
                )
                setNoticeVisible(false)
            } else {
                setNoticeVisible(true)
            }
        } else {
            if (isRoutineSelected) {
                removeRoutineItem(
                    selectedRoutineArray,
                    selectedRoutineArray.indexOf(routine.routineId),
                    binding
                )
            } else {
                selectedRoutineArray.add(routine.routineId)
                changeRoutineBackground(binding, true)
            }
            setNoticeVisible(false)
        }
    }

    private fun setNoticeVisible(isVisible: Boolean) {
        _isRoutineNoticeVisible.value = isVisible
    }

    private fun removeRoutineItem(
        routineArray: ArrayList<Int>,
        selectedIndex: Int,
        binding: ItemOnboardingChoiceRoutineBinding
    ) {
        routineArray.removeAt(selectedIndex)
        changeRoutineBackground(binding, false)
    }

    private fun changeRoutineBackground(
        binding: ItemOnboardingChoiceRoutineBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                setContent(
                    R.drawable.shape_gray100_fill_gray400_stroke_99_rect,
                    R.color.gray700,
                    binding
                )
            }

            false -> {
                setContent(
                    R.drawable.shape_white_fill_gray000_stroke_99_rect,
                    R.color.gray400,
                    binding
                )
            }
        }
    }

    private fun setContent(
        background: Int,
        color: Int,
        binding: ItemOnboardingChoiceRoutineBinding
    ) {
        with(binding.tvRoutineContent) {
            setBackgroundResource(background)
            setTextColor(ContextCompat.getColor(binding.root.context, color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineChoiceViewHolder {
        val binding = ItemOnboardingChoiceRoutineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoutineChoiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutineChoiceViewHolder, position: Int) {

//        val binding: ItemOnboardingChoiceRoutineBinding =
//            ItemOnboardingChoiceRoutineBinding.bind(holder.itemView)
//        if (selectPosition.contains(position)) {
//            binding.tvRoutineContent.setBackgroundResource(R.drawable.shape_gray100_fill_gray400_stroke_99_rect)
//        } else {
//            binding.tvRoutineContent.setBackgroundResource(R.drawable.shape_white_fill_gray000_stroke_99_rect)
//        }
//
//        binding.tvRoutineContent.setOnClickListener {
//
//            if (selectedRoutineArray.size == MAXIMUM_ROUTINE_SELECTION) {
//                if (selectPosition.contains(position)) {
//                    selectPosition.remove(position)
//                    setNoticeVisible(false)
//                } else {
//                    setNoticeVisible(true)
//                }
//            } else {
//                if (selectPosition.contains(position)) {
//                    selectPosition.remove(position)
//                } else {
//                    selectedRoutineArray.add(position)
//                }
//                setNoticeVisible(false)
//            }
//
//            notifyItemChanged(position)
//        }

        holder.onBind(currentList[position])
    }
}
