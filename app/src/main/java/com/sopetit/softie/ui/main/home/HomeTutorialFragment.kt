package com.sopetit.softie.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHomeTutorialBottomsheetBinding
import com.sopetit.softie.domain.entity.Tutorial
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.FINISH
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.REQUEST_KEY
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.RESULT_KEY
import java.lang.reflect.Field

class HomeTutorialFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentHomeTutorialBottomsheetBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewPager: ViewPager2
    private var currentIndex = 0
    private val pageCallback by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentIndex = position
                updateTitle()
                updateIndicators()
            }
        }
    }
    private lateinit var homeTutorialAdapter: HomeTutorialAdapter

    override fun onStart() {
        super.onStart()
        preventHorizontalSliding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTutorialBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtnAction()
        setupAdapter()
        setupIndicators()
        updateIndicators()
        setBottomSheetCallback()
    }

    private fun setBtnAction() {
        binding.btnNext.setOnClickListener {
            if (currentIndex == TUTORIAL_COTTON) {
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to FINISH))
                dismiss()
            } else {
                viewPager.currentItem = currentIndex + 1
            }
        }
    }

    private fun preventHorizontalSliding() {
        if (dialog != null) {
            val touchSideView =
                dialog!!.window?.decorView?.findViewById<View>(com.google.android.material.R.id.touch_outside)
            touchSideView?.setOnClickListener { null }
        }
    }

    private fun setupAdapter() {
        homeTutorialAdapter = HomeTutorialAdapter()
        viewPager = binding.vpTutorial
        with(binding.vpTutorial) {
            adapter = homeTutorialAdapter
            registerOnPageChangeCallback(pageCallback)
        }
        homeTutorialAdapter.submitList(
            listOf(
                Tutorial(TUTORIAL_DAILY, R.drawable.tutorial_daily),
                Tutorial(TUTORIAL_CHALLENGE, R.drawable.tutorial_challenge),
                Tutorial(TUTORIAL_COTTON, R.drawable.tutorial_cotton)
            )
        )
    }

    private fun setupIndicators() {
        val indicators = Array(homeTutorialAdapter.itemCount) {
            ImageView(requireContext()).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_indicator_seleted
                    )
                )
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(
                        INDICATOR_HORIZONTAL_MARGIN,
                        INDICATOR_VERTICAL_MARGIN,
                        INDICATOR_HORIZONTAL_MARGIN,
                        INDICATOR_VERTICAL_MARGIN
                    )
                }
            }
        }
        indicators.forEach { indicator ->
            binding.llTutorialIndicator.addView(indicator)
        }
    }

    private fun updateTitle() {
        binding.tvTutorialTitle.text = when (currentIndex) {
            TUTORIAL_COTTON -> getString(R.string.home_tutorial_title_cotton)
            else -> getString(R.string.home_tutorial_title_routine)
        }

        binding.btnNext.text = when (currentIndex) {
            TUTORIAL_COTTON -> getString(R.string.home_tutorial_btn_start)
            else -> getString(R.string.home_tutorial_btn_next)
        }
    }

    private fun updateIndicators() {
        for (i in 0 until binding.llTutorialIndicator.childCount) {
            val imageView = binding.llTutorialIndicator.getChildAt(i) as ImageView
            if (i == currentIndex) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_indicator_seleted)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_indicator_unselected)
                )
            }
        }
    }

    private fun setBottomSheetCallback() {
        dialog?.let { dialog ->
            try {
                val behaviorField: Field = dialog.javaClass.getDeclaredField("behavior")
                behaviorField.isAccessible = true
                val behavior = behaviorField.get(dialog) as BottomSheetBehavior<*>
                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // Do nothing
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewPager.unregisterOnPageChangeCallback(pageCallback)
    }

    companion object {
        const val INDICATOR_VERTICAL_MARGIN = 0
        const val INDICATOR_HORIZONTAL_MARGIN = 8
        const val TUTORIAL_DAILY = 0
        const val TUTORIAL_CHALLENGE = 1
        const val TUTORIAL_COTTON = 2
    }
}
