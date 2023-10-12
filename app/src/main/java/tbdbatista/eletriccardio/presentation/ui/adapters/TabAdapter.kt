package tbdbatista.eletriccardio.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tbdbatista.eletriccardio.presentation.ui.CarroFragment
import tbdbatista.eletriccardio.presentation.ui.FavoritoFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CarroFragment()
            else -> FavoritoFragment()
        }
    }


}