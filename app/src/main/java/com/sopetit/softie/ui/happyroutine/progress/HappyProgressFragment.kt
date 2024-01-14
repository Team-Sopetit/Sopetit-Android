package com.sopetit.softie.ui.happyroutine.progress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyProgressBinding
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.ui.main.home.HomeFragment
import com.sopetit.softie.util.binding.BindingFragment

class HappyProgressFragment :
    BindingFragment<FragmentHappyProgressBinding>(R.layout.fragment_happy_progress) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HappyProgressViewModel by activityViewModels()
        val happyProgress = viewModel.mockHappyProgress

        setCardBinding(happyProgress)
        setCardEnter()
        setEditEnter()
        setClearEnter()
    }

    private fun setCardBinding(happyProgress: HappyProgress) {
        with(binding) {
            tvHappyProgressSubtitle.text = happyProgress.title
            ivHappyProgressCardFront.setImageResource(happyProgress.imageUrl)
            tvHappyProgressCardFrontTitle.text = happyProgress.content
            tvHappyProgressCardBackTitle.text = happyProgress.detailTitle
            tvHappyProgressCardBackContent.text = happyProgress.detailContent
            tvHappyProgressCardBackTime.text = happyProgress.detailTime
            tvHappyProgressCardBackPlace.text = happyProgress.detailPlace
        }
    }

    private fun setCardEnter() {
        with(binding) {
            clHappyProgressCardFront.setOnClickListener {
                setCardFlip(clHappyProgressCardFront, clHappyProgressCardBack)
            }
            clHappyProgressCardBack.setOnClickListener {
                setCardFlip(clHappyProgressCardBack, clHappyProgressCardFront)
            }
        }
    }

    private fun setEditEnter() {
        binding.tvHappyProgressEdit.setOnClickListener {
            val homeFragment = HomeFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, homeFragment)
                .commit()
        }
    }

    private fun setCardFlip(viewFront: View, viewToBack: View) {
        val isVisible = viewFront.visibility == View.VISIBLE
        if (isVisible) {
            viewFront.visibility = View.INVISIBLE
            viewToBack.visibility = View.VISIBLE
        } else {
            viewFront.visibility = View.VISIBLE
            viewToBack.visibility = View.INVISIBLE
        }
    }

    private fun setClearEnter() {
        binding.btnHappyProgressClear.setOnClickListener {
            val homeFragment = HomeFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, homeFragment)
                .commit()
        }
    }
}
