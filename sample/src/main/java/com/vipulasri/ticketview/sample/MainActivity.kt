package com.vipulasri.ticketview.sample

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ImageView
import com.thebluealliance.spectrum.SpectrumDialog
import com.vipulasri.ticketview.TicketView
import kotlinx.android.synthetic.main.bottomsheet_ticket_attributes.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_background_options.*
import kotlinx.android.synthetic.main.item_border_options.*
import kotlinx.android.synthetic.main.item_corner_options.*
import kotlinx.android.synthetic.main.item_divider_options.*
import kotlinx.android.synthetic.main.item_scallop_options.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initOptionsBottomSheet()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_example -> {
                startActivity(Intent(this, ExampleActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initOptionsBottomSheet() {
        val behavior = BottomSheetBehavior.from<View>(bottomSheet)

        view_options_header.setOnClickListener({
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
        image_background_shadow_color.background.setColorFilter(ticketView.shadowColor, PorterDuff.Mode.SRC_ATOP)
        image_border_color.background.setColorFilter(ticketView.borderColor, PorterDuff.Mode.SRC_ATOP)
        image_divider_color.background.setColorFilter(ticketView.dividerColor, PorterDuff.Mode.SRC_ATOP)

        when(ticketView.orientation) {
            TicketView.Orientation.HORIZONTAL -> radioButton_horizontal.isChecked = true
            TicketView.Orientation.VERTICAL -> radioButton_vertical.isChecked = true
        }

        radioGroup_orientation.setOnCheckedChangeListener({ group, checkedId ->
            when(checkedId) {
                R.id.radioButton_horizontal -> ticketView.orientation = TicketView.Orientation.HORIZONTAL
                R.id.radioButton_vertical -> ticketView.orientation = TicketView.Orientation.VERTICAL
                else -> {
                    ticketView.orientation = TicketView.Orientation.HORIZONTAL
                }
            }
        })

        //background properties

        image_background_color.setOnClickListener({
            showColorPicker(ticketView.backgroundColor, image_background_color)
        })

        image_background_shadow_color.setOnClickListener({
            showColorPicker(ticketView.shadowColor, image_background_shadow_color)
        })

        seekBar_elevation.setOnProgressChangeListener(progressChangeListener)

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
        checkbox_show_border.setOnCheckedChangeListener({ compoundButton, b ->
            ticketView.isShowBorder = b
        })

        seekBar_border_width.progress = Utils.pxToDp(ticketView.borderWidth.toFloat(), this)
        seekBar_border_width.setOnProgressChangeListener(progressChangeListener)

        image_border_color.setOnClickListener({
            showColorPicker(ticketView.borderColor, image_border_color)
        })

        //divider properties

        ticketView.isShowDivider = true
        checkbox_show_divider.isChecked = true
        checkbox_show_divider.setOnCheckedChangeListener({ compoundButton, b ->
            ticketView.isShowDivider = b
        })

        image_divider_color.setOnClickListener({
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

        //corner properties

        spinner_corner_type.setSelection(0)
        spinner_corner_type.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Normal" -> ticketView.cornerType = TicketView.CornerType.NORMAL
                    "Rounded" -> ticketView.cornerType = TicketView.CornerType.ROUNDED
                    "Scallop" -> ticketView.cornerType = TicketView.CornerType.SCALLOP
                    else -> {
                        ticketView.cornerType = TicketView.CornerType.NORMAL
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        seekBar_corner_radius.progress = Utils.pxToDp(ticketView.cornerRadius.toFloat(), this)
        seekBar_corner_radius.setOnProgressChangeListener(progressChangeListener)

    }

    private fun showColorPicker(selectedColor : Int, colorView: ImageView) {
        SpectrumDialog.Builder(this)
                .setColors(R.array.colors)
                .setSelectedColor(selectedColor)
                .setDismissOnColorSelected(true)
                .setOutlineWidth(1)
                .setOnColorSelectedListener({ positiveResult, color ->
                    if (positiveResult) {
                        colorView.background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

                        when(colorView.id) {
                            R.id.image_border_color -> ticketView.borderColor = color
                            R.id.image_divider_color -> ticketView.dividerColor = color
                            R.id.image_background_color -> ticketView.backgroundColor = color
                            R.id.image_background_shadow_color -> ticketView.shadowColor = color
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
            Log.d("TAG", "->"+discreteSeekBar.id)
            when(discreteSeekBar.id) {
                R.id.seekBar_elevation -> ticketView.setTicketElevation(valueInDp.toFloat())
                R.id.seekBar_border_width -> ticketView.borderWidth = valueInDp
                R.id.seekBar_scallop_radius -> ticketView.scallopRadius = valueInDp
                R.id.seekBar_divider_width -> ticketView.dividerWidth = valueInDp
                R.id.seekBar_divider_dash_length -> ticketView.dividerDashLength = valueInDp
                R.id.seekBar_divider_dash_gap -> ticketView.dividerDashGap = valueInDp
                R.id.seekBar_corner_radius -> ticketView.cornerRadius = valueInDp
            }
        }

        override fun onStartTrackingTouch(discreteSeekBar: DiscreteSeekBar) {

        }

        override fun onStopTrackingTouch(discreteSeekBar: DiscreteSeekBar) {

        }
    }
}
