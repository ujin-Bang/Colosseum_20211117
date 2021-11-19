package com.neppplus.colosseum_20211117.datas

import org.json.JSONObject

class SideData {

    var id = 0
    var title = ""
    var voteCount = 0

    companion object {

        fun getSideDataFromJson( jsonObj: JSONObject ) : SideData {

            val sideData = SideData()

//            기본값 -> jsonObj 를 이용해서 파싱된 값으로 대입.
            sideData.id = jsonObj.getInt("id")
            sideData.title = jsonObj.getString("title")
            sideData.voteCount =  jsonObj.getInt("vote_count")


            return  sideData

        }

    }

}