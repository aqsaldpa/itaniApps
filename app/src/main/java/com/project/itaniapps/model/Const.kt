package com.project.itaniapps.model

object Const {
    // Arraylist and return the Arraylist
    fun getNews(): ArrayList<News> {
        val NewsList = ArrayList<News>()
        val news = arrayListOf(
            News(
                "https://img.antaranews.com/cache/1200x800/2023/10/23/gaza-1.jpg.webp",
                "Internasional",
                "Menlu Yordania Sebut Israel Lakukan Kejahatan Perang di Gaza",
                "2023-11-05 22:00 WIB"
            ),
            News(
                "https://awsimages.detik.net.id/visual/2023/11/04/citra-satelit-menunjukkan-gambaran-jabalia-pasca-ledakan-1-november-2023_169.jpeg?w=715&q=90",
                "Perang",
                "Update Perang Gaza: Israel Menggila, Netanyahu Vs Erdogan",
                "2023-11-04 11:00 WIB"
            ),
            News(
                "https://awsimages.detik.net.id/visual/2023/10/26/cover-artikel-pemilu-2024_169.jpeg?w=715&q=90",
                "Pemilihan Presiden",
                "Survei Terbaru Capres & Cawapres 2024, Siapa Paling Unggul?",
                "2023-11-05 22:00 WIB"
            ),
            News(
                "https://awsimages.detik.net.id/visual/2023/11/05/israel-palestiniansindonesia-3_169.jpeg?w=800&q=90",
                "Nasional",
                "Potret Aksi Bela Palestina di Jakarta, Lawan Agresi Israel",
                "2023-11-05 14:00 WIB"
            ),
            News(
                "https://awsimages.detik.net.id/visual/2021/05/21/warga-palestina-bentrok-di-tepi-barat_169.jpeg?w=715&q=90",
                "Internasional",
                "Tepi Barat Memanas! Tentara Israel Tewaskan 5 Warga Palestina",
                "2023-11-04 16:00 WIB"
            ),
        )
        NewsList.addAll(news)
        return NewsList
    }
}