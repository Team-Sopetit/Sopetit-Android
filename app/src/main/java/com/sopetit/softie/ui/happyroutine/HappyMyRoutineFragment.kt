package com.sopetit.softie.ui.happyroutine

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyMyRoutineBinding
import com.sopetit.softie.ui.happyroutine.complete.HappyRoutineCompleteActivity
import com.sopetit.softie.ui.happyroutine.delete.HappyDeleteFragment
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity
import com.sopetit.softie.util.CustomSnackbar
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyMyRoutineFragment :
    BindingFragment<FragmentHappyMyRoutineBinding>(R.layout.fragment_happy_my_routine) {

    private val viewModel by viewModels<HappyMyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColor(R.color.background)

        initSetBearFace()
        setMyCardEnter()
        setCardEnter()
        setEditEnter()
        setClearEnter()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getHappyProgress()

        val sharedPreferences =
            requireActivity().getSharedPreferences("HappyFirstAdd", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isFirstAdded", false)) {
            val customSnackbar = CustomSnackbar.make(
                binding.root.rootView,
                getString(R.string.happy_routine_add_snack_bar)
            )
            customSnackbar.setMargin(
                0,
                0,
                0,
                resources.getDimensionPixelSize(R.dimen.snackbar_add_margin_bottom)
            )
            customSnackbar.show()
            sharedPreferences.edit().remove("isFirstAdded").apply()
        }
    }

    private fun initSetBearFace() {
        viewModel.setDollFace()
        viewModel.bearFace.observe(viewLifecycleOwner) { bearFace ->
            binding.ivHappyRoutineCharacter.load(bearFace) {
                placeholder(R.drawable.ic_loading_bear)
                error(R.drawable.ic_loading_bear)
            }
        }
    }

    private fun setMyCardEnter() {
        binding.ivHappyRoutineEmptyCard.setOnClickListener {
            val intent = Intent(requireContext(), HappyAddListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCardEnter() {
        with(binding) {
            clHappyProgressCardFront.setSingleOnClickListener {
                setCardFlip(clHappyProgressCardFront, clHappyProgressCardBack)
            }
            clHappyProgressCardBack.setSingleOnClickListener {
                setCardFlip(clHappyProgressCardBack, clHappyProgressCardFront)
            }
        }
    }

    private fun setEditEnter() {
        binding.tvHappyProgressEdit.setSingleOnClickListener {
            val happyDeleteFragment = HappyDeleteFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, happyDeleteFragment)
                .addToBackStack(null)
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

    private fun initHappyRoutineCompleteBottomSheet(icon: String) {
        BindingBottomSheet.Builder().build(
            isDrawable = false,
            imageDrawable = 0,
            imageUri = icon,
            title = getString(R.string.happy_progress_bottom_sheet_title),
            content = getString(R.string.happy_progress_bottom_sheet_content),
            isContentVisible = true,
            contentColor = R.color.gray400,
            backBtnContent = getString(R.string.happy_progress_sheet_back_btn),
            doBtnContent = getString(R.string.happy_progress_sheet_do_btn),
            doBtnColor = R.drawable.shape_main1_fill_12_rect,
            backBtnAction = {},
            doBtnAction = {
                startHappyRoutineCompleteActivity()
                viewModel.happyProgressResponse.value?.let { viewModel.patchAchieveHappyRoutine(it.routineId) }
                startHappyRoutineCompleteActivity()
            }
        ).show(parentFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun startHappyRoutineCompleteActivity() {
        val intentToCompleteActivity =
            Intent(requireActivity(), HappyRoutineCompleteActivity::class.java)
        startActivity(intentToCompleteActivity)
    }

    private fun setClearEnter() {
        binding.btnHappyProgressClear.setOnClickListener {
            viewModel.happyProgressResponse.value?.iconImageUrl?.let { url ->
                initHappyRoutineCompleteBottomSheet(
                    url
                )
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_HAPPY_DETAIL = 231217
    }
}
