package com.sopetit.softie.util.binding

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopetit.softie.util.OriginalBottomSheet

abstract class BindingBottomSheet : BottomSheetDialogFragment() {

    protected var _binding: ViewBinding? = null
    protected var image: String? = null
    protected var title: String? = null
    protected var content: String? = null
    protected var isContentVisible: Boolean? = null
    protected var contentColor: Int? = null
    protected var backBtnContent: String? = null
    protected var doBtnContent: String? = null
    protected var doBtnColor: Int? = null
    protected lateinit var backBtnAction: () -> Unit
    protected lateinit var doBtnAction: () -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setImage()
        setTitle()
        setContent()
        setContentVisible()
        setContentColor()
        setBackBtnContent()
        setDoBtnContent()
        setDoBtnColor()
        setBackBtnClick { backBtnAction(); dismiss() }
        setDoBtnClick { doBtnAction(); dismiss() }
    }

    abstract fun setImage()
    abstract fun setTitle()
    abstract fun setContent()
    abstract fun setContentVisible()
    abstract fun setContentColor()
    abstract fun setBackBtnContent()
    abstract fun setDoBtnContent()
    abstract fun setDoBtnColor()

    abstract fun setBackBtnClick(action: () -> Unit)
    abstract fun setDoBtnClick(action: () -> Unit)

    class Builder() {
        fun build(
            image: String,
            title: String,
            content: String,
            isContentVisible: Boolean,
            contentColor: Int,
            backBtnContent: String,
            doBtnContent: String,
            doBtnColor: Int,
            backBtnAction: () -> Unit,
            doBtnAction: () -> Unit
        ): BindingBottomSheet {
            val bottomSheet = OriginalBottomSheet()
            return bottomSheet.apply {
                this.image = image
                this.title = title
                this.content = content
                this.isContentVisible = isContentVisible
                this.contentColor = contentColor
                this.backBtnContent = backBtnContent
                this.doBtnContent = doBtnContent
                this.doBtnColor = doBtnColor
                this.backBtnAction = backBtnAction
                this.doBtnAction = doBtnAction
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
