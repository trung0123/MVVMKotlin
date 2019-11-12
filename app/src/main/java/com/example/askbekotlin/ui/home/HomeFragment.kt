package com.example.askbekotlin.ui.home


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.askbekotlin.R
import com.example.askbekotlin.adapter.ImitationLoopPagerAdapter
import com.example.askbekotlin.adapter.LessonListAdapter
import com.example.askbekotlin.adapter.RecommendLessonAdapter
import com.example.askbekotlin.databinding.FragmentHomeBinding
import com.example.askbekotlin.ui.base.BaseVMFragment
import com.example.askbekotlin.utils.Utils
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip
import kotlinx.android.synthetic.main.layout_reload.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseVMFragment<HomeViewModel>(), View.OnClickListener {

    companion object {
        val TAG = HomeFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentHomeBinding

    override fun providerVMClass(): Class<HomeViewModel>? = HomeViewModel::class.java

    private val recommendLessonAdapter = RecommendLessonAdapter()

    private val overallRankLessonAdapter = RecommendLessonAdapter()

    private val newestLessonAdapter = RecommendLessonAdapter()

    private val weeklyRankLessonAdapter = RecommendLessonAdapter()

    private var firstLoad: Boolean = false

    private var mAdapter: ImitationLoopPagerAdapter? = null

    private var cateId = "-1"

    private var bcate: String? = null

    private var nextLessonId: String? = null

    private var nextLessonUserId: String? = null

    private lateinit var tooltipLessonCreation: SimpleTooltip

    private lateinit var tooltipLessonTeach: SimpleTooltip

    private lateinit var tvTitle: TextView

    private lateinit var imgLogoHome: ImageView

    private lateinit var tvTotalNotification: TextView

    private lateinit var tvTotalMessage: TextView

    private var loading = true
    private var pastVisibleItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var page = 1
    private val limit = 10
    private var isContLoadMore = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun initData() {
        setEvents()
        initRecyclerView()

        binding.viewModel = mViewModel
        binding.cateId = cateId
        binding.lifecycleOwner = this
        mViewModel.loadHome()
        initLoadMoreWeeklyRankLessonCate()
    }

    private fun setEvents() {
        binding.llHomeNextLesson.setOnClickListener(this)
        binding.llHomePaymentUser.setOnClickListener(this)
        binding.llHomeLessonCreation.setOnClickListener(this)
        binding.llHomeLessonTeach.setOnClickListener(this)
        binding.btnHomeConfirmationBook.setOnClickListener(this)
        binding.btnHomeMoreRecommendLesson.setOnClickListener(this)
        binding.btnHomeMorePopularLesson.setOnClickListener(this)
        binding.btnHomeMoreNewLesson.setOnClickListener(this)
        binding.btnHomeMoreRankLesson.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        firstLoad = true
        mAdapter = ImitationLoopPagerAdapter()
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) {
                if (!firstLoad) {
                    cateId = mAdapter?.getValueAt(position)?.id!!
                    binding.cateId = cateId
                    if (cateId == "-1") {
                        mViewModel.loadHome()
                    } else {
                        bcate = cateId
                        loading = true
                        isContLoadMore = true
                        page = 1
                        mViewModel.loadHomeByCategory(cateId)
                    }
                }
                firstLoad = false
            }
        })

        binding.viewPager.adapter = mAdapter
        binding.recyclerTabLayout.setUpWithViewPager(binding.viewPager)

        binding.rvHomeRecommendLesson.adapter = recommendLessonAdapter
        binding.rvHomeRecommendLesson.addItemDecoration(
            DividerItemDecoration(
                getBaseActivity(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvHomeRecommendLesson.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeOverallRankLesson.adapter = overallRankLessonAdapter
        binding.rvHomeOverallRankLesson.addItemDecoration(
            DividerItemDecoration(
                getBaseActivity(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvHomeOverallRankLesson.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeNewestLesson.adapter = newestLessonAdapter
        binding.rvHomeNewestLesson.addItemDecoration(
            DividerItemDecoration(
                getBaseActivity(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvHomeNewestLesson.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun startObserve() {
        super.startObserve()

        mViewModel.apply {
            mData.observe(this@HomeFragment, Observer {

            })

            mDataTotal.observe(this@HomeFragment, Observer {
                val totalNotify = it.newsTotal + it.lessonTotal
                val totalMess = it.dmTotal + it.callTotal
                setupBadge(totalNotify, totalMess)
            })

            mError.observe(this@HomeFragment, Observer {
                handleApiError(it)
            })

            mProgress.observe(this@HomeFragment, Observer {
                if (it) {
                    pb_loading.visibility = View.VISIBLE
                    binding.homeContent.visibility = View.GONE
                } else {
                    pb_loading.visibility = View.GONE
                    binding.homeContent.visibility = View.VISIBLE
                }
            })

            mWeeklyLoadMoreRankLesson.observe(this@HomeFragment, Observer {
                binding.pbRvLoading.visibility = View.GONE
                loading = true
                if (it.size < limit) {
                    stopLoadMore()
                }
                val adapter = binding.rvHomeWeeklyRankLesson.adapter as LessonListAdapter
                adapter.addLessons(it)
            })
        }
    }

    private fun stopLoadMore() {
        isContLoadMore = false
        binding.pbRvLoading.visibility = View.GONE
    }

    private fun initLoadMoreWeeklyRankLessonCate() {
        binding.scvHome.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                Log.i(TAG, "BOTTOM SCROLL")
                if (!isContLoadMore) {
                    return@OnScrollChangeListener
                }
                if (binding.rvHomeWeeklyRankLesson.adapter is LessonListAdapter) {
                    val adapter = binding.rvHomeWeeklyRankLesson.adapter as LessonListAdapter
                    if (adapter.itemCount % 10 != 0) {
                        Log.d("Weekly Rank Lesson", "onScrolled: " + adapter.itemCount)
                        binding.pbRvLoading.visibility = View.GONE
                        return@OnScrollChangeListener
                    }
                }

                if (binding.rvHomeWeeklyRankLesson.layoutManager is GridLayoutManager) {
                    val gridLayoutManager =
                        binding.rvHomeWeeklyRankLesson.layoutManager as GridLayoutManager
                    visibleItemCount = gridLayoutManager.childCount
                    totalItemCount = gridLayoutManager.itemCount
                    pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()

                    if (loading && isContLoadMore) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            binding.pbRvLoading.visibility = View.VISIBLE
                            loading = false
                            mViewModel.getListWeeklyRankingLesson(++page, bcate!!, limit)
                        }
                    }
                }
            }
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_home_lesson_creation -> showMenuLessonCreation(v)
            R.id.ll_home_lesson_teach -> showMenuLessonTeach(v)
        }
    }

    private fun showMenuLessonCreation(view: View) {
        tooltipLessonCreation = SimpleTooltip.Builder(context)
            .anchorView(view)
            .gravity(Gravity.BOTTOM)
            .dismissOnOutsideTouch(true)
            .dismissOnInsideTouch(false)
            .modal(true)
            .arrowColor(ContextCompat.getColor(getBaseActivity(), R.color.colorPrimary))
            .contentView(R.layout.menu_lesson_creation)
            .focusable(true)
            .build()
        tooltipLessonCreation.show()
        val llTTSReservationPay =
            tooltipLessonCreation.findViewById<LinearLayout>(R.id.ll_menu_tooltip_s_reservation_pay)
        val llTTPurchaseHistory =
            tooltipLessonCreation.findViewById<LinearLayout>(R.id.ll_menu_tooltip_s_purchase_cancel_history)
        val llTTBrowsingHistory =
            tooltipLessonCreation.findViewById<LinearLayout>(R.id.ll_menu_tooltip_s_browsing_history)
        val llTTDirectMessage =
            tooltipLessonCreation.findViewById<LinearLayout>(R.id.ll_menu_tooltip_s_direct_message)
        val llTTLessonDataHistory =
            tooltipLessonCreation.findViewById<LinearLayout>(R.id.ll_menu_tooltip_s_lesson_data_history)

        llTTSReservationPay.setOnClickListener(this)
        llTTPurchaseHistory.setOnClickListener(this)
        llTTBrowsingHistory.setOnClickListener(this)
        llTTDirectMessage.setOnClickListener(this)
        llTTLessonDataHistory.setOnClickListener(this)
    }

    private fun showMenuLessonTeach(v: View) {
        tooltipLessonTeach = SimpleTooltip.Builder(context)
            .anchorView(v)
            .gravity(Gravity.BOTTOM)
            .dismissOnOutsideTouch(true)
            .dismissOnInsideTouch(false)
            .modal(true)
            .arrowColor(ContextCompat.getColor(getBaseActivity(), R.color.colorPrimary))
            .contentView(R.layout.menu_lesson_teach)
            .focusable(true)
            .build()
        tooltipLessonTeach.show()

        val llTTTReservationPay =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_reservation_pay)
        val llTTSaleHistory =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_sales_cancel_history)
        val llTTLessonCreation =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_lesson_creation)
        val llTTMyLesson =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_my_lesson)
        val llTTWithdrawal =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_withdrawal)
        val llTDirectMess =
            tooltipLessonTeach.findViewById<LinearLayout>(R.id.ll_menu_tooltip_t_direct_message)

        llTTTReservationPay.setOnClickListener(this)
        llTTSaleHistory.setOnClickListener(this)
        llTTLessonCreation.setOnClickListener(this)
        llTTMyLesson.setOnClickListener(this)
        llTTWithdrawal.setOnClickListener(this)
        llTDirectMess.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
        setHasOptionsMenu(true)
    }

    private fun setupActionBar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.mainToolBar)
        toolbar?.let {
            tvTitle = it.findViewById(R.id.tv_abs_title)
            tvTitle.visibility = View.GONE
            imgLogoHome = it.findViewById(R.id.img_logo_home)
            imgLogoHome.visibility = View.VISIBLE
            Utils.setLayoutParams(getBaseActivity(), tvTitle, "", 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notification_menu, menu)

        val itemNotification = menu.findItem(R.id.menu_notification)
        val viewNotification = itemNotification.actionView
        tvTotalNotification = viewNotification.findViewById(R.id.cart_badge)
        tvTotalNotification.visibility = View.GONE

        viewNotification.setOnClickListener { v -> onOptionsItemSelected(itemNotification) }

        val itemMessage = menu.findItem(R.id.menu_message)
        val viewMessage = itemMessage.actionView
        tvTotalMessage = viewMessage.findViewById(R.id.cart_badge)
        tvTotalMessage.visibility = View.GONE

        viewMessage.setOnClickListener { v -> onOptionsItemSelected(itemMessage) }

        mViewModel.getNumOfNotifies("dm", "call", "news", "lesson")
    }

    private fun setupBadge(totalNotify: Int, totalMess: Int) {
        if (totalNotify == 0) {
            if (tvTotalNotification.visibility != View.GONE) {
                tvTotalNotification.visibility = View.GONE
            }
        } else {
            if (totalNotify > 99) {
                tvTotalNotification.text = "99+"
            } else {
                tvTotalNotification.text = totalNotify.toString()
            }
            if (tvTotalNotification.visibility != View.VISIBLE) {
                tvTotalNotification.visibility = View.VISIBLE
            }
        }

        if (totalMess == 0) {
            if (tvTotalMessage.visibility != View.GONE) {
                tvTotalMessage.visibility = View.GONE
            }
        } else {
            if (totalMess > 99) {
                tvTotalMessage.text = "99+"
            } else {
                tvTotalMessage.text = totalMess.toString()
            }
            if (tvTotalMessage.visibility != View.VISIBLE) {
                tvTotalMessage.visibility = View.VISIBLE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        imgLogoHome.visibility = View.GONE
        tvTitle.visibility = View.VISIBLE
        super.onStop()
    }

    override fun onDestroyView() {
        imgLogoHome.visibility = View.GONE
        tvTitle.visibility = View.VISIBLE
        super.onDestroyView()
    }

}

