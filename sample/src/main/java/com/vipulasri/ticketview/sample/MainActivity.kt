package com.vipulasri.ticketview.sample

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vipulasri.ticketview.TicketView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.thebluealliance.spectrum.SpectrumDialog
import android.graphics.PorterDuff
import android.support.annotation.NonNull
import android.util.Log
import android.widget.AdapterView
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_border_options.*
import kotlinx.android.synthetic.main.item_divider_options.*
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.item_scallop_options.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.bottomsheet_ticket_attributes.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initOptionsBottomSheet()
    }

    private fun initOptionsBottomSheet() {
        val behavior = BottomSheetBehavior.from<View>(bottomSheet)

        view_options_header.setOnClickListener(View.OnClickListener {
            if (behavior!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })

        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> image_toggle.setImageResource(R.drawable.ic_expand_less_black_24dp)
                    BottomSheetBehavior.STATE_EXPANDED -> image_toggle.setImageResource(R.drawable.ic_expand_more_black_24dp)
                    else -> {
                        image_toggle.setImageResource(R.drawable.ic_expand_less_black_24dp)
                    }
                }
            }

            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {

            }
        })

        image_background_color.background.setColorFilter(ticketView.backgroundColor, PorterDuff.Mode.SRC_ATOP)
        image_border_color.background.setColorFilter(ticketView.borderColor, PorterDuff.Mode.SRC_ATOP)
        image_divider_color.background.setColorFilter(ticketView.dividerColor, PorterDuff.Mode.SRC_ATOP)

        radioGroup_orientation.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButton_horizontal -> ticketView.orientation = TicketView.Orientation.HORIZONTAL
                R.id.radioButton_vertical -> ticketView.orientation = TicketView.Orientation.VERTICAL
                else -> {
                    ticketView.orientation = TicketView.Orientation.HORIZONTAL
                }
            }
        })

        //background properties

        image_background_color.setOnClickListener(View.OnClickListener {
            showColorPicker(ticketView.backgroundColor, image_background_color)
        })

        //scallop properties

        seekBar_scallop_radius.progress = Utils.pxToDp(ticketView.scallopRadius.toFloat(), this)
        seekBar_scallop_radius.setOnProgressChangeListener(progressChangeListener)

        spinner_scallop_position.setSelection(4)
        spinner_scallop_position.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                ticketView.scallopPositionPercent = selectedItem.toFloat()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        //border properties

        ticketView.isShowBorder = false
        checkbox_show_border.isChecked = false
        checkbox_show_border.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            ticketView.isShowBorder = b
        })

        seekBar_border_width.progress = Utils.pxToDp(ticketView.borderWidth.toFloat(), this)
        seekBar_border_width.setOnProgressChangeListener(progressChangeListener)

        image_border_color.setOnClickListener(View.OnClickListener {
            showColorPicker(ticketView.borderColor, image_border_color)
        })

        //divider properties

        ticketView.isShowDivider = true
        checkbox_show_divider.isChecked = true
        checkbox_show_divider.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            ticketView.isShowDivider = b
        })

        image_divider_color.setOnClickListener(View.OnClickListener {
            showColorPicker(ticketView.dividerColor, image_divider_color)
        })

        spinner_divider_type.setSelection(1)
        spinner_divider_type.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Normal" -> ticketView.dividerType = TicketView.DividerType.NORMAL
                    "Dashed" -> ticketView.dividerType = TicketView.DividerType.DASH
                    else -> {
                        ticketView.dividerType = TicketView.DividerType.NORMAL
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        seekBar_divider_width.progress = Utils.pxToDp(ticketView.dividerWidth.toFloat(), this)
        seekBar_divider_width.setOnProgressChangeListener(progressChangeListener)

        seekBar_divider_dash_length.progress = Utils.pxToDp(ticketView.dividerDashLength.toFloat(), this)
        seekBar_divider_dash_length.setOnProgressChangeListener(progressChangeListener)

        seekBar_divider_dash_gap.progress = Utils.pxToDp(ticketView.dividerDashGap.toFloat(), this)
        seekBar_divider_dash_gap.setOnProgressChangeListener(progressChangeListener)

    }

    private fun showColorPicker(selectedColor : Int, colorView: ImageView) {
        SpectrumDialog.Builder(this)
                .setColors(R.array.colors)
                .setSelectedColor(selectedColor)
                .setDismissOnColorSelected(true)
                .setOutlineWidth(1)
                .setOnColorSelectedListener(SpectrumDialog.OnColorSelectedListener { positiveResult, color ->
                    if (positiveResult) {
                        colorView.background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

                        when(colorView.id) {
                            R.id.image_border_color -> ticketView.borderColor = color
                            R.id.image_divider_color -> ticketView.dividerColor = color
                            R.id.image_background_color -> ticketView.backgroundColor = color
                            else -> {
                                //do nothing
                            }
                        }

                    }
                }).build().show(supportFragmentManager, "ColorPicker")
    }

    private var progressChangeListener: DiscreteSeekBar.OnProgressChangeListener = object : DiscreteSeekBar.OnProgressChangeListener {

        override fun onProgressChanged(discreteSeekBar: DiscreteSeekBar, value: Int, b: Boolean) {

            val valueInDp = Utils.dpToPx(value.toFloat(), this@MainActivity)
            Log.e("TAG", "->"+discreteSeekBar.id)
            when(discreteSeekBar.id) {
                R.id.seekBar_border_width -> ticketView.borderWidth = valueInDp
                R.id.seekBar_scallop_radius -> ticketView.scallopRadius = valueInDp
                R.id.seekBar_divider_width -> ticketView.dividerWidth = valueInDp
                R.id.seekBar_divider_dash_length -> ticketView.dividerDashLength = valueInDp
                R.id.seekBar_divider_dash_gap -> ticketView.dividerDashGap = valueInDp
            }
        }

        override fun onStartTrackingTouch(discreteSeekBar: DiscreteSeekBar) {

        }

        override fun onStopTrackingTouch(discreteSeekBar: DiscreteSeekBar) {

        }
    }
}
