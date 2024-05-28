package com.sopetit.softie.ui.happyroutine.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyDeleteBinding
import com.sopetit.softie.ui.happyroutine.HappyMyRoutineFragment
import com.sopetit.softie.ui.happyroutine.HappyMyRoutineViewModel
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.CustomSnackbar
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyDeleteFragment :
    BindingFragment<FragmentHappyDeleteBinding>(R.layout.fragment_happy_delete) {

    private val viewModel by viewModels<HappyMyRoutineViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val happyProgress = viewModel.getHappyProgress()

        setCardBinding(happyProgress)
        setCardImageClickListener()
        setCancelEnter()
        startHappyDeleteBottomSheet()
    }

    private fun setCardBinding(happyProgress: Unit) {
        viewModel.happyProgressResponse.observe(viewLifecycleOwner) { happyProgress ->
            happyProgress?.let {
                with(binding) {
                    tvHappyDeleteSubtitle.text = happyProgress.title
                    ivHappyDeleteCardFront.load(happyProgress.contentImageUrl) {
                        placeholder(R.drawable.ic_bear_base)
                        error(R.drawable.ic_bear_base)
                    }
                    tvHappyDeleteCardFrontTitle.text = happyProgress.content
                    tvHappyDeleteCardBackTitle.text = happyProgress.content
                    tvHappyDeleteCardBackContent.text = happyProgress.detailContent
                    tvHappyDeleteCardBackTime.text = happyProgress.timeTaken
                    tvHappyDeleteCardBackPlace.text = happyProgress.place
                }
            }
        }
    }

    private fun setCardImageClickListener() {
        with(binding) {
            clHappyDeleteCardFront.setSingleOnClickListener {
                setCardImageFlip(clHappyDeleteCardFront, clHappyDeleteCardBack)
            }
            clHappyDeleteCardBack.setSingleOnClickListener {
                setCardImageFlip(clHappyDeleteCardBack, clHappyDeleteCardFront)
            }
        }
    }

    private fun setCancelEnter() {
        binding.tvHappyDeleteDestroy.setSingleOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setCardImageFlip(viewFront: View, viewToBack: View) {
        val isVisible = viewFront.visibility == View.VISIBLE
        if (isVisible) {
            viewFront.visibility = View.INVISIBLE
            viewToBack.visibility = View.VISIBLE
        } else {
            viewFront.visibility = View.VISIBLE
            viewToBack.visibility = View.INVISIBLE
        }
    }

    private fun startHappyDeleteBottomSheet() {
        binding.btnHappyDeleteClear.setSingleOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = true,
                imageDrawable = R.drawable.ic_bear_face_crying,
                imageUri = "",
                title = getString(R.string.happy_delete_bottom_sheet_title),
                content = "",
                isContentVisible = false,
                contentColor = R.color.gray500,
                backBtnContent = getString(R.string.happy_delete_bottom_sheet_back_btn),
                doBtnContent = getString(R.string.happy_delete_bottom_sheet_do_btn),
                doBtnColor = R.drawable.shape_red_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    viewModel.happyProgressResponse.observe(viewLifecycleOwner) { happyProgress ->
                        happyProgress?.let {
                            viewModel.deleteHappyProgress(happyProgress.routineId)
                        }
                        customHappyDeleteSnackBar()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.fcv_main, HappyMyRoutineFragment())
                            .commit()
                    }
                }
            ).show(parentFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
        }
    }

    private fun customHappyDeleteSnackBar() {
        val customSnackbar = CustomSnackbar.make(
            (binding.root.rootView),
            getString(R.string.happy_routine_delete_snack_bar),
            (requireActivity() as MainActivity).findViewById(R.id.bottom_navigation_home)
        )
        customSnackbar.show(1000)
    }
}
