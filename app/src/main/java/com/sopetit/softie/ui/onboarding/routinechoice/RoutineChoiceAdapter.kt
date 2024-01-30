package com.sopetit.softie.ui.onboarding.routinechoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.selection.SelectionTracker
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

//    init {
//        setHasStableIds(true)
//    }

    private lateinit var selectionTracker: SelectionTracker<Long>

    private var onItemClickListener: ((Routine) -> Unit)? = null
    var selectedRoutineArray = arrayListOf<Int>()

    private var _isRoutineNoticeVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRoutineNoticeVisible: LiveData<Boolean>
        get() = _isRoutineNoticeVisible

//    override fun getItemId(position: Int): Long {
////        return getItem(position).routineId.toLong()
////        return position.toLong()
//        return currentList[position].routineId.toLong()
//    }

//    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
//        this.selectionTracker = selectionTracker
//    }

    inner class RoutineChoiceViewHolder(private val binding: ItemOnboardingChoiceRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Routine) {
            binding.tvRoutineContent.text = data.content
            binding.root.setOnClickListener {
                routineSelection(binding, data)
                onItemClickListener?.let { it(data) }
            }


//            binding.tvRoutineContent.isActivated = selectionTracker.isSelected(itemId)
//            Timber.d("routine choice -> 테스트테스트테스트 : ${binding.tvRoutineContent.isActivated}")
//            binding.root.setOnClickListener {
//                binding.tvRoutineContent.isActivated = true
//                selectionTracker.select(itemPosition.toLong())
//            }

//            binding.tvRoutineContent.isActivated = isSelected
//            Timber.d("routine choice -> 테스트테스트테스트 : ${binding.tvRoutineContent.isActivated}")
        }

//        fun getItemDetails(viewHolder: RecyclerView.ViewHolder?): ItemDetailsLookup.ItemDetails<Long> {
//            return object : ItemDetailsLookup.ItemDetails<Long>() {
//                override fun getSelectionKey(): Long? {
////                    return viewHolder?.itemId
//                    return itemId
//                }
//
//                override fun getPosition(): Int {
//                    if (viewHolder == null) {
//                        return RecyclerView.NO_POSITION
//                    }
//                    return viewHolder.absoluteAdapterPosition
//                }
//            }
//        }
    }

//    class SelectionKeyProvider(private val recyclerView: RecyclerView) :
//        ItemKeyProvider<Long>(SCOPE_MAPPED) {
//        override fun getKey(position: Int): Long {
//            val holder = recyclerView.findViewHolderForAdapterPosition(position)
//            return holder?.itemId ?: throw IllegalStateException("No Holder")
//        }
//
//        override fun getPosition(key: Long): Int {
//            val holder = recyclerView.findViewHolderForItemId(key)
//            return if (holder is RoutineChoiceViewHolder) {
//                holder.absoluteAdapterPosition
//            } else {
//                RecyclerView.NO_POSITION
//            }
//        }
//    }

//    class SelectionDetailsLookup(private val recyclerView: RecyclerView) :
//        ItemDetailsLookup<Long>() {
//        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
//            val view = recyclerView.findChildViewUnder(e.x, e.y) ?: return null
//
//            val holder = recyclerView.getChildViewHolder(view)
//            return if (holder is RoutineChoiceViewHolder) {
//                object : ItemDetails<Long>() {
//                    override fun getPosition(): Int {
//                        return holder.absoluteAdapterPosition
//                    }
//
//                    override fun getSelectionKey(): Long {
//                        return holder.itemId
//                    }
//                }
//            } else {
//                null
//            }
//        }
//    }

//    class SelectionDetailsLookup(private val recyclerView: RecyclerView) :
//        ItemDetailsLookup<Long>() {
//
//        @Nullable
//        override fun getItemDetails(@NonNull motionEvent: MotionEvent): ItemDetails<Long>? {
//            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
//
//            if (view != null) {
//                return when (val viewHolder = recyclerView.getChildViewHolder(view)) {
//                    is RoutineChoiceViewHolder -> viewHolder.getItemDetails(
//                        viewHolder,
//                    )
//
//                    else -> null
//                }
//            }
//            return null
//        }
//    }

//    fun getSelectedRoutine(): Routine? {
//        val selectedItemId = selectionTracker.selection.firstOrNull()
//        return if (selectedItemId == null) {
//            null
//        } else {
//            currentList[selectedItemId.toInt()]
//        }
//    }

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
        holder.onBind(currentList[position])
//        holder.onBind(currentList[position], selectionTracker.isSelected(position.toLong()))
//        holder.onBind(currentList[position], position)
    }
}
