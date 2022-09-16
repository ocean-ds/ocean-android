package br.com.useblu.oceands.components

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanBottomListSheetAdapter
import br.com.useblu.oceands.bindingadapters.setTextFromHtml
import br.com.useblu.oceands.databinding.OceanDialogBinding

class OceanSearchDialog(
    private val title: String?,
    private val hint: String?,
    private val adapter: OceanBottomListSheetAdapter,
) : DialogFragment() {

    private var _binding: OceanDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Ocean_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
            it.window?.setWindowAnimations(R.style.Ocean_Slide)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OceanDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupSearch()
        setupRecycleView()
    }

    private fun setupSearch() {
        binding.inputSearch.hint = hint

        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(text: Editable?) {
                val find = text.toString()
                val countFiltered = adapter.filter(find)

                if (countFiltered == 0) {
                    setTextFromHtml(
                        binding.textNotFoundResults,
                        getString(R.string.not_found_results, find)
                    )
                    binding.textNotFoundResults.visibility = View.VISIBLE
                    binding.recyclerViewItems.visibility = View.GONE
                } else {
                    binding.textNotFoundResults.visibility = View.GONE
                    binding.recyclerViewItems.visibility = View.VISIBLE
                }
            }
        })

        binding.textInputLayout.setEndIconOnClickListener {
            binding.inputSearch.setText("")
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
        binding.toolbar.title = title
        binding.toolbar.setOnMenuItemClickListener {
            dismiss()
            true
        }
    }

    private fun setupRecycleView() {
        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
