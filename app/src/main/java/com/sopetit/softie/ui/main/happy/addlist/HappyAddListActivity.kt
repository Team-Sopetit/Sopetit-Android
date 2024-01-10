package com.sopetit.softie.ui.main.happy.addlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddListBinding
import com.sopetit.softie.util.HorizontalChipItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration

class HappyAddListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHappyAddListBinding
    private lateinit var itemDeco: RecyclerView.ItemDecoration
    private lateinit var chipDeco: RecyclerView.ItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val happyAddListChipContentAdapter = HappyAddListChipContentAdapter(this)
        binding.rvHappyAddListChip.adapter = happyAddListChipContentAdapter
        happyAddListChipContentAdapter.setHomeContentList(mockHappyChipList)

        val happyAddListContentAdapter = HappyAddListContentAdapter(this)
        binding.rvHappyAddList.adapter = happyAddListContentAdapter
        happyAddListContentAdapter.setHomeContentList(mockHappyContentList)

        itemDeco = VerticalItemDecoration(applicationContext)
        binding.rvHappyAddList.addItemDecoration(itemDeco)
        chipDeco = HorizontalChipItemDecoration(applicationContext)
        binding.rvHappyAddListChip.addItemDecoration(chipDeco)
    }

    private val mockHappyChipList = listOf<HappyAddListChipContent>(
        HappyAddListChipContent(
            themeId = 1,
            name = "전체",
        ),
        HappyAddListChipContent(
            themeId = 2,
            name = "관계쌓기",
        ),
        HappyAddListChipContent(
            themeId = 3,
            name = "한 걸음 성장",
        ),
        HappyAddListChipContent(
            themeId = 4,
            name = "잘 쉬어가기",
        ),
        HappyAddListChipContent(
            themeId = 5,
            name = "새로운 나",
        ),
        HappyAddListChipContent(
            themeId = 6,
            name = "마음 챙김",
        ),
    )


    private val mockHappyContentList = listOf<HappyAddListContent>(
        HappyAddListContent(
            routineId = 1,
            title = "관계쌓기",
            content = "성숙한 사랑을 만나기 위한",
            imageUrl = R.drawable.ic_happy_red,
        ),
        HappyAddListContent(
            routineId = 2,
            title = "관계쌓기",
            content = "진정성 있는 관계를 만드는",
            imageUrl = R.drawable.ic_happy_red,
        ),
        HappyAddListContent(
            routineId = 3,
            title = "한 걸음 성장",
            content = "나를 알고 진짜 목표를 세우는",
            imageUrl = R.drawable.ic_happy_orange,
        ),
        HappyAddListContent(
            routineId = 4,
            title = "한 걸음 성장",
            content = "좋아하는, 잘하는 일을 찾아 가는",
            imageUrl = R.drawable.ic_happy_orange,
        ),
        HappyAddListContent(
            routineId = 5,
            title = "잘 쉬어가기",
            content = "데이터가 아직 없습니다",
            imageUrl = R.drawable.ic_happy_green,
        ),
        HappyAddListContent(
            routineId = 6,
            title = "새로운 나",
            content = "나를 알고 진짜 목표를 세우는",
            imageUrl = R.drawable.ic_happy_blue,
        ),
        HappyAddListContent(
            routineId = 7,
            title = "마음 챙김",
            content = "데이터가 아직 없습니다",
            imageUrl = R.drawable.ic_happy_purple,
        ),
    )
}
