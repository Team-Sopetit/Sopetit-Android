package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.MakerCard
import com.sopetit.softie.domain.entity.RoutineTheme
import javax.inject.Inject

class MockAddRoutineRepository @Inject constructor() : AddRoutineRepository {
    override suspend fun getMakerCard(): Result<List<MakerCard>> {
        return Result.success(
            listOf(
                MakerCard(
                    artistId = 1,
                    artistImageUrl = "https://img.etoday.co.kr/pto_db/2024/04/600/20240426132100_2017673_647_863.jpeg",
                    subTitle = "하이브의 주인은 누구?",
                    title = "뉴진스가 돌아왔다",
                    hashtag = listOf(
                        MakerCard.Hashtag(hashtagId = 1, content = "#민희진"),
                        MakerCard.Hashtag(hashtagId = 2, content = "#방시혁"),
                        MakerCard.Hashtag(hashtagId = 2, content = "#하이브")
                    )
                ),
                MakerCard(
                    artistId = 2,
                    artistImageUrl = "https://cdn.hankyung.com/photo/202211/BF.23427209.1.jpg",
                    subTitle = "더이상 웹툰작가로는 못산다",
                    title = "침착맨의 시크릿",
                    hashtag = listOf(
                        MakerCard.Hashtag(hashtagId = 3, content = "#침착침착"),
                        MakerCard.Hashtag(hashtagId = 4, content = "#이말년")
                    )
                ),
                MakerCard(
                    artistId = 3,
                    artistImageUrl = "https://res.heraldm.com/content/image/2024/05/06/20240506050023_0.jpg",
                    subTitle = "선재업고 얼마나 뛸 수 있어?",
                    title = "변우석의 성공담",
                    hashtag = listOf(
                        MakerCard.Hashtag(hashtagId = 5, content = "#류선재"),
                        MakerCard.Hashtag(hashtagId = 6, content = "#드라마")
                    )
                )
            )
        )
    }

    override suspend fun getRoutineTheme(): Result<RoutineTheme> {
        return Result.success(
            RoutineTheme(
                themes = listOf(
                    RoutineTheme.Themes(
                        themeId = 1,
                        modifier = "사람들과 어울리는",
                        name = "관계 쌓기",
                        description = "설명"
                    ),
                    RoutineTheme.Themes(
                        themeId = 2,
                        modifier = "나를 돌보는",
                        name = "마음 챙김",
                        description = "설명2"
                    ),
                    RoutineTheme.Themes(
                        themeId = 3,
                        modifier = "경제적으로 살아가는",
                        name = "통통한 통장",
                        description = "설명2"
                    ),
                    RoutineTheme.Themes(
                        themeId = 4,
                        modifier = "하루를 상쾌하게",
                        name = "산뜻한 일상",
                        description = "설명2"
                    ),
                    RoutineTheme.Themes(
                        themeId = 5,
                        modifier = "더 나은 나를 위해",
                        name = "한 걸음 성장",
                        description = "설명2"
                    ),
                    RoutineTheme.Themes(
                        themeId = 6,
                        modifier = "튼튼하게 건강하게",
                        name = "건강한 몸",
                        description = "설명2"
                    ),
                    RoutineTheme.Themes(
                        themeId = 7,
                        modifier = "튼튼하게 건강하게",
                        name = "나와 친해지기",
                        description = "설명2"
                    )
                )
            )
        )
    }
}
