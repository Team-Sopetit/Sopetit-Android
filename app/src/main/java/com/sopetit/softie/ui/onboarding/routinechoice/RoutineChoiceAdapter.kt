package com.sopetit.softie.ui.onboarding.routinechoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

    inner class RoutineChoiceViewHolder(val binding: ItemOnboardingChoiceRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Routine, onClickListener: View.OnClickListener) {
            with(binding) {
                tvRoutineContent.text = data.content
                root.setOnClickListener(onClickListener)
            }
        }
    }

    fun setOnRoutineClickListener(listener: (Routine) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return position
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
        binding.tvRoutineContent.isActivated = selected
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
        holder.apply {
            onBind(currentList[position], View.OnClickListener {
                routineSelection(binding, currentList[position])
                onItemClickListener?.let { it(currentList[position]) }
            })
        }
    }
}
