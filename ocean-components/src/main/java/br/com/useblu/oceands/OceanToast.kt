package br.com.useblu.oceands

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import br.com.useblu.oceands.databinding.OceanToastBinding


class OceanToast(
        private val activity: Activity
) {

    private lateinit var binding: OceanToastBinding
    private var message: String? = null
    private var type: OceanToastType? = null

    fun show(): OceanToast {

        binding = OceanToastBinding.inflate(activity.layoutInflater)

        this.message?.let {
            binding.message.text = it
        }

        type?.let {
            when (type) {
                is OceanToastType.Information -> {
                    binding.icon.setImageResource(R.drawable.icon_information)
                }
                is OceanToastType.Error -> {
                    binding.icon.setImageResource(R.drawable.icon_error)
                }
                is OceanToastType.Success -> {
                    binding.icon.setImageResource(R.drawable.icon_success)
                }
                is OceanToastType.Warning -> {
                    binding.icon.setImageResource(R.drawable.icon_warning)
                }
            }
        }

        with(Toast(activity.applicationContext)) {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.CENTER_VERTICAL, 0, 580)
            view = binding.root
            show()
        }

        return this
    }

    fun withMessage(message: String): OceanToast {
        this.message = message
        return this
    }

    fun withMessage(message: Int): OceanToast {
        this.message = activity.getString(message)
        return this
    }

    fun withType(type: OceanToastType): OceanToast {
        this.type = type
        return this
    }

    sealed class OceanToastType {
        object Information : OceanToastType()
        object Error : OceanToastType()
        object Success : OceanToastType()
        object Warning : OceanToastType()
    }
}