package com.example.administrator.paging.network

import java.io.Serializable

/**
 * <pre>
 *
 * @author   :   Alex
 * @e_mail   :   18238818283@sina.cn
 * @time     :   2018/01/18
 * @desc     :
 * @version  :   V 1.0.9
</pre> */

class Bean : Serializable {
    /**
     * date : 20180118
     * stories : [{"images":["https://pic1.zhimg.com/v2-38c5e0b5396c3222cec14c6bab7c0448.jpg"],"type":0,"id":9666603,"ga_prefix":"011812","title":"大误 · 同学，你也开发火星啊？"},{"images":["https://pic3.zhimg.com/v2-4c05bb7594c51a53c15b3b21c833f5ca.jpg"],"type":0,"id":9666709,"ga_prefix":"011811","title":"拿着屏幕玩手柄，任天堂把「硬盒」游戏玩出新高度"},{"images":["https://pic4.zhimg.com/v2-6eee2eb37ca31181289dc5af0add834f.jpg"],"type":0,"id":9666381,"ga_prefix":"011810","title":"都是拍片子，X 光、CT、B 超、核磁共振......有什么区别？"},{"images":["https://pic3.zhimg.com/v2-6412410a5a2b264601e5f4829579e4c6.jpg"],"type":0,"id":9666161,"ga_prefix":"011809","title":"一打开窗全是「墓地」，买到这种房，恐怕告开发商也难赢"},{"images":["https://pic4.zhimg.com/v2-f61287a33049c3240da3e18e83baeaaf.jpg"],"type":0,"id":9666463,"ga_prefix":"011808","title":"为什么火车硬座不像公交车那样，非要人面对面坐？"},{"title":"生活在硅谷，我来讲讲这里的日常是怎样的","ga_prefix":"011807","images":["https://pic2.zhimg.com/v2-0999dc7eab7461a8542611d127fb42f9.jpg"],"multipic":true,"type":0,"id":9666437},{"images":["https://pic1.zhimg.com/v2-1fb5c85cb7dcfaf55b20b5300f901154.jpg"],"type":0,"id":9666634,"ga_prefix":"011807","title":"- 有哪些外行觉得很蠢，实际设计很精妙的东西？\r\n- 验证码"},{"images":["https://pic3.zhimg.com/v2-66b0c47b3d1b27c1eb5ff937b27e6d22.jpg"],"type":0,"id":9666616,"ga_prefix":"011807","title":"深访「币圈」：享受过一夜暴富，你就再也忘不掉那种捷径"},{"images":["https://pic1.zhimg.com/v2-ebc6042968339b381cf3b7d5c9e63224.jpg"],"type":0,"id":9666642,"ga_prefix":"011806","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-1832f63fa903fda40e7ef83be6d45cce.jpg","type":0,"id":9666709,"ga_prefix":"011811","title":"拿着屏幕玩手柄，任天堂把「硬盒」游戏玩出新高度"},{"image":"https://pic1.zhimg.com/v2-08cd73a1e2bf42a19d6d634a4f422de8.jpg","type":0,"id":9666616,"ga_prefix":"011807","title":"深访「币圈」：享受过一夜暴富，你就再也忘不掉那种捷径"},{"image":"https://pic4.zhimg.com/v2-898fb75c59478e8665b0473328c4d03f.jpg","type":0,"id":9666463,"ga_prefix":"011808","title":"为什么火车硬座不像公交车那样，非要人面对面坐？"},{"image":"https://pic2.zhimg.com/v2-d5d35a8d4b77cd9535575b9cb91f360d.jpg","type":0,"id":9666437,"ga_prefix":"011807","title":"生活在硅谷，我来讲讲这里的日常是怎样的"},{"image":"https://pic4.zhimg.com/v2-1e173d65bfa662cc370e35ce8578458f.jpg","type":0,"id":9666161,"ga_prefix":"011809","title":"一打开窗全是「墓地」，买到这种房，恐怕告开发商也难赢"}]
     */
    var date: String? = null
    var stories: List<StoriesBean>? = null
    var top_stories: List<StoriesBean>? = null

    class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-38c5e0b5396c3222cec14c6bab7c0448.jpg"]
         * type : 0
         * id : 9666603
         * ga_prefix : 011812
         * title : 大误 · 同学，你也开发火星啊？
         * multipic : true
         */

        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var isMultipic: Boolean = false
        var images: List<String>? = null
    }


    override fun toString(): String {
        return "Bean{" +
                "date='" + date + '\''.toString() +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}'.toString()
    }

    companion object {
        private const val serialVersionUID: Long = 1000000
    }
}
