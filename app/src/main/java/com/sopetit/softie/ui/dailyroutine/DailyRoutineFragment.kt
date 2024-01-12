package com.sopetit.softie.ui.dailyroutine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.util.binding.BindingFragment

class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {

    private val viewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initSetDailyRoutineContent()
        initSetDeleteView()
    }

    private fun initSetDailyRoutineContent() {
        viewModel.mockDailyRoutineList.observe(viewLifecycleOwner) { dailyRoutineList ->
            with(binding) {
                tvDailyRoutineAddNameFirst.text = dailyRoutineList[0].content
                tvDailyRoutineIngFirst.text = "${dailyRoutineList[0].achieveCount}번째 달성 중"
                btnDailyRoutineYetFinFirst.setBackgroundResource(if (dailyRoutineList[0].isAchieve) R.drawable.shape_main1_fill_10_rect else R.drawable.shape_gray100_fill_10_rect)
            }
            with(binding) {
                tvDailyRoutineIngSecond.text = dailyRoutineList[1].content
                tvDailyRoutineIngSecond.text = "${dailyRoutineList[1].achieveCount}번째 달성 중"
                btnDailyRoutineYetFinSecond.setBackgroundResource(if (dailyRoutineList[1].isAchieve) R.drawable.shape_main1_fill_10_rect else R.drawable.shape_gray100_fill_10_rect)
            }
            with(binding) {
                tvDailyRoutineIngThird.text = dailyRoutineList[2].content
                tvDailyRoutineIngThird.text = "${dailyRoutineList[2].achieveCount}번째 달성 중"
                btnDailyRoutineYetFinThird.setBackgroundResource(if (dailyRoutineList[2].isAchieve) R.drawable.shape_main1_fill_10_rect else R.drawable.shape_gray100_fill_10_rect)
            }
        }
    }

    private fun initSetDeleteView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            if (!isDeleteView) {
                binding.tvDailyRoutineEdit.setOnClickListener {
                    viewModel.setDeleteView(true)
                }
            }
        }

        initSetBackOriginalView()
    }

    private fun initSetBackOriginalView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            if (isDeleteView) {
                binding.tvDailyRoutineEdit.setOnClickListener {
                    viewModel.setDeleteView(false)
                }
            }
        }
    }
}
