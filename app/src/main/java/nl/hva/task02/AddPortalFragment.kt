package nl.hva.task02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import nl.hva.task02.databinding.FragmentAddPortalBinding

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

class AddPortalFragment : Fragment() {

    private var _binding: FragmentAddPortalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPortalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onAddPortal() {
        val title = binding.etTitle.text.toString()
        val url = binding.etUrl.text.toString()

        if (title.isNotBlank() && url.isNotBlank()) {
            //set the data as fragmentResult, we are listening for REQ_PORTAL_KEY in PortalsFragment!
            val portal = Portal(title, url)
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))

            //"pop" the backstack, this means we destroy
            //this fragment and go back to the PortalsFragment
            findNavController().popBackStack()
        } else {
            Toast.makeText(
                activity,
                getString(R.string.invalid_portal), Toast.LENGTH_SHORT
            ).show()
        }
    }
}