package nl.hva.task02

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import nl.hva.task02.databinding.FragmentPortalsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private var _binding: FragmentPortalsBinding? = null
    private val binding get() = _binding!!
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvPortals.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding.rvPortals.adapter = portalAdapter

        portalAdapter.onItemClicked = {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(it.url))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyItemInserted(portals.indexOf(it))
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal title or url!")
        }
    }
}